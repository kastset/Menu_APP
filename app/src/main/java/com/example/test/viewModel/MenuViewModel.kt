package com.example.test.viewModel

import com.example.test.data.DishesRepository

class MenuViewModel(private val dishesRepository: DishesRepository) : BaseDishViewModel(dishesRepository) {
    override val listOfDish = super.listOfDish
}
