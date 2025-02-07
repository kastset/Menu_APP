package com.example.test.data

import android.content.Context

interface AppContainer {
    val dishesRepository: DishesRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val dishesRepository: DishesRepository by lazy {
        OfflineDishesRepository(MenuDatabase.getDatabase(context).dishDao())
    }
}
