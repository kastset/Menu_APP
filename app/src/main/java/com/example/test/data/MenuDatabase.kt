package com.example.test.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test.model.Dish
import com.example.test.model.dishes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
            val existingDishes = dao.getAllDishesForUpdateDb()
            val newDish =
                dishes.filter { newDish ->
                    existingDishes.none { it.id == newDish.id }
                }

            if (newDish.isNotEmpty()) {
                dao.insertDishes(newDish)
            }
        }
    }
}
