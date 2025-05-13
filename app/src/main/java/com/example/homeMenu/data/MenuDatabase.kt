package com.example.homeMenu.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.homeMenu.model.Dish
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Database(entities = [Dish::class], version = 1, exportSchema = false)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun dishDao(): DishDao

    companion object {
        @Volatile
        private var Instance: MenuDatabase? = null

        fun getDatabase(context: Context): MenuDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MenuDatabase::class.java, "dish_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { bd ->
                        Instance = bd
                        CoroutineScope(Dispatchers.IO).launch {
                            prepopulateDatabase(bd.dishDao())
                        }
                    }
            }
        }

        private suspend fun prepopulateDatabase(dao: DishDao) {
            val db = Firebase.firestore
            val localDishes = dao.getAllDishesForUpdateDb().associateBy { it.id }

            try {
                val remoteSnapshot = db.collection("dishes").get().await()
                val remoteDishes = remoteSnapshot.toObjects(Dish::class.java)

                val mergedDishes =
                    remoteDishes.map { remoteDish ->
                        val localDish = localDishes[remoteDish.id]

                        remoteDish.copy(
                            rating = localDish?.rating ?: remoteDish.rating,
                            isFavorite = localDish?.isFavorite ?: remoteDish.isFavorite,
                        )
                    }

                dao.insertDishes(mergedDishes)
                Log.d("Firestore", "Room DB обновлена с сохранением rating и isFavorite")
            } catch (e: Exception) {
                Log.e("Firestore", "Ошибка при синхронизации с Firestore", e)
            }
        }
    }
}
