package com.example.homeMenu.data

import com.example.homeMenu.model.Dish
import kotlinx.coroutines.flow.Flow

interface DishesRepository {
    suspend fun updateDish(dish: Dish)

    suspend fun insertDish(dish: Dish)

    suspend fun deleteDish(dish: Dish)

    fun getAllDishesStream(): Flow<List<Dish>>

    fun getAllFavoriteDishesStream(): Flow<List<Dish>>

    fun getDishesByTypeStream(type: String): Flow<List<Dish>>

    fun getDish(id: Int): Flow<Dish?>

    fun searchDish(query: String): Flow<List<Dish>>

    fun searchFavoriteDish(query: String): Flow<List<Dish>>

    fun searchDishesByType(
        type: String,
        query: String,
    ): Flow<List<Dish>>
}
