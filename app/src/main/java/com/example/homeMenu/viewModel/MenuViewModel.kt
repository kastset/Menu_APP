package com.example.homeMenu.viewModel

import com.example.homeMenu.data.DishesRepository

class MenuViewModel(private val dishesRepository: DishesRepository) : BaseDishViewModel(dishesRepository) {
    override val listOfDish = super.listOfDish
}
