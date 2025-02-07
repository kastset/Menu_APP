package com.example.test.viewModel

import androidx.lifecycle.viewModelScope
import com.example.test.data.DishesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavoriteViewModel(private val dishesRepository: DishesRepository) : BaseDishViewModel(dishesRepository) {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    override val listOfDish: StateFlow<AllUiDishState> =
        searchText
            .flatMapLatest { query ->
                if (query.isBlank()) {
                    dishesRepository.getAllFavoriteDishesStream().map {
                        AllUiDishState(it)
                    }
                } else {
                    dishesRepository.searchFavoriteDish(query).map { AllUiDishState(it) }
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = AllUiDishState(),
            )
}
