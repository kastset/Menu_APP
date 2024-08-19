package com.example.test.ui.dish

import androidx.lifecycle.ViewModel
import com.example.test.model.Dish
import com.example.test.model.dishes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DishViewModel : ViewModel() {
    private val dishesMutFlow = MutableStateFlow<List<Dish>>(dishes)

    val dishesFlow: StateFlow<List<Dish>> = dishesMutFlow

    fun toggleFavorite(dishId: Int) {
        dishesMutFlow.value =
            dishesMutFlow.value.map { dish ->
                if (dish.id == dishId) {
                    dish.copy(isFavorite = !dish.isFavorite)
                } else {
                    dish
                }
            }
    }
}
