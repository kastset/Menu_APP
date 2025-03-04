package com.example.homeMenu.viewModel

import androidx.lifecycle.viewModelScope
import com.example.homeMenu.data.DishesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TypeListViewModel(private val dishesRepository: DishesRepository) : BaseDishViewModel(dishesRepository) {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    fun listOfDish(type: String): StateFlow<AllUiDishState> =
        searchText
            .flatMapLatest { query ->
                if (query.isBlank()) {
                    dishesRepository.getDishesByTypeStream(type).map {
                        AllUiDishState(it)
                    }
                } else {
                    dishesRepository.searchDishesByType(type, query).map { AllUiDishState(it) }
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = AllUiDishState(),
            )
}
