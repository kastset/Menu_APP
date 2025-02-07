package com.example.test.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.test.model.Dish
import kotlinx.coroutines.flow.Flow

@Dao
interface DishDao {
    //    @Delete
//    suspend fun delete(dish: Dish)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDishes(dishes: List<Dish>)

    @Update
    suspend fun update(dish: Dish)

    @Query("SELECT * from dishes")
    fun getAllDishes(): Flow<List<Dish>>

    @Query("SELECT * from dishes WHERE isFavorite = 1")
    fun getAllFavoriteDishes(): Flow<List<Dish>>

    @Query("SELECT * from dishes WHERE dishType = :dishType")
    fun getAllDishesByType(dishType: String): Flow<List<Dish>>

    @Query("SELECT * from dishes WHERE dishId = :dishId")
    fun getDish(dishId: Int): Flow<Dish>

    @Query("SELECT * FROM dishes WHERE dishName LIKE '%' || :query || '%'")
    fun searchDishes(query: String): Flow<List<Dish>>

    @Query("SELECT * FROM dishes WHERE isFavorite = 1 AND dishName LIKE '%' || :query || '%'")
    fun searchFavoriteDishes(query: String): Flow<List<Dish>>

    @Query("SELECT * FROM dishes WHERE dishType = :dishType AND dishName LIKE '%' || :query || '%'")
    fun searchDishesByType(
        dishType: String,
        query: String,
    ): Flow<List<Dish>>
}
