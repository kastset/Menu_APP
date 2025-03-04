package com.example.homeMenu.viewModel

import androidx.lifecycle.SavedStateHandle
import com.example.homeMenu.data.DishesRepository

class HomeViewModel(savedStateHandle: SavedStateHandle, private val dishesRepository: DishesRepository) : BaseDishViewModel(
    dishesRepository,
) {
    override val listOfDish = super.listOfDish
}
