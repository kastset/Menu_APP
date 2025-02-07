package com.example.test.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
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
                    .addCallback(DatabaseCallback(context))
                    .build()
                    .also { Instance = it }
            }
        }

        private class DatabaseCallback(private val context: Context) : RoomDatabase.Callback() {
            override fun onCreate(bd: SupportSQLiteDatabase) {
                super.onCreate(bd)
                CoroutineScope(Dispatchers.IO).launch {
                    prepopulateDatabase(context)
                }
            }
        }

        private suspend fun prepopulateDatabase(context: Context) {
            val dao = getDatabase(context).dishDao()
            dao.insertDishes(dishes)
        }
    }
}
