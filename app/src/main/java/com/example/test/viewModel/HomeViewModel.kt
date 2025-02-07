package com.example.test.viewModel

import androidx.lifecycle.SavedStateHandle
import com.example.test.data.DishesRepository

class HomeViewModel(savedStateHandle: SavedStateHandle, private val dishesRepository: DishesRepository) : BaseDishViewModel(
    dishesRepository,
) {
    override val listOfDish = super.listOfDish
}
