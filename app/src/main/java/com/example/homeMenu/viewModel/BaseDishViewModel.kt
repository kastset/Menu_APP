package com.example.homeMenu.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeMenu.data.DishesRepository
import com.example.homeMenu.model.Dish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

open class BaseDishViewModel(private val dishesRepository: DishesRepository) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    data class AllUiDishState(val dishList: List<Dish> = listOf())

    open val listOfDish: StateFlow<AllUiDishState> =
        searchText
            .flatMapLatest { query ->
                if (query.isBlank()) {
                    dishesRepository.getAllDishesStream().map {
                        AllUiDishState(it)
                    }
                } else {
                    dishesRepository.searchDish(query).map { AllUiDishState(it) }
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(BaseDishViewModel.TIMEOUT_MILLIS),
                initialValue = AllUiDishState(),
            )

    open fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    open fun clearSearchText() {
        _searchText.value = ""
    }

    open fun getDishById(id: Int): Flow<Dish?> {
        return dishesRepository.getDish(id)
    }

    open fun toggleFavorite(id: Int) {
        viewModelScope.launch {
            val d = getDishById(id).firstOrNull() ?: throw IllegalArgumentException("Dish with id $id not found")
            dishesRepository.updateDish(
                dish = d.copy(isFavorite = !d.isFavorite),
            )
        }
    }

    open fun toggleRating(
        id: Int,
        newRating: Int,
    ) {
        viewModelScope.launch {
            val dish = getDishById(id).firstOrNull() ?: throw IllegalArgumentException("Dish with id $id not found")
            dishesRepository.updateDish(dish = dish.copy(rating = newRating))
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
