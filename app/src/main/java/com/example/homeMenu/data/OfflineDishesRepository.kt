package com.example.homeMenu.data

import com.example.homeMenu.model.Dish
import kotlinx.coroutines.flow.Flow

class OfflineDishesRepository(private val dishDao: DishDao) : DishesRepository {
    override suspend fun updateDish(dish: Dish) = dishDao.update(dish)

    override suspend fun insertDish(dish: Dish) = dishDao.insertDish(dish)

    override suspend fun deleteDish(dish: Dish) = dishDao.delete(dish)

    override fun getAllDishesStream(): Flow<List<Dish>> = dishDao.getAllDishes()

    override fun getAllFavoriteDishesStream(): Flow<List<Dish>> = dishDao.getAllFavoriteDishes()

    override fun getDishesByTypeStream(type: String): Flow<List<Dish>> = dishDao.getAllDishesByType(type)

    override fun getDish(id: Int): Flow<Dish?> = dishDao.getDish(id)

    override fun searchDish(query: String): Flow<List<Dish>> = dishDao.searchDishes(query)

    override fun searchFavoriteDish(query: String): Flow<List<Dish>> = dishDao.searchFavoriteDishes(query)

    override fun searchDishesByType(
        type: String,
        query: String,
    ): Flow<List<Dish>> = dishDao.searchDishesByType(type, query)
}
