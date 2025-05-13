package com.example.homeMenu.viewModel

import com.example.homeMenu.data.DishesRepository

class HomeViewModel(private val dishesRepository: DishesRepository) : BaseDishViewModel(
    dishesRepository,
) {
    override val listOfDish = super.listOfDish
}
