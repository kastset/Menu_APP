package com.example.homeMenu.data

import android.content.Context

interface AppContainer {
    val dishesRepository: DishesRepository
    val authRepository: AuthRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val dishesRepository: DishesRepository by lazy {
        OfflineDishesRepository(MenuDatabase.getDatabase(context).dishDao())
    }
    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl()
    }
}
