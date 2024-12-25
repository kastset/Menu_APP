package com.example.test.ui.dish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.model.Dish
import com.example.test.model.dishes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class DishViewModel : ViewModel() {
    private val dishesMutFlow = MutableStateFlow<List<Dish>>(dishes)
    val dishesFlow: StateFlow<List<Dish>> = dishesMutFlow

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    val dishesFullList =
        searchText
            .combine(dishesMutFlow) { text, dishesList ->
                if (text.isBlank()) {
                    dishesList
                } else {
                    dishesList.filter {
                        it.doesSearchMatchQuery(text)
                    }
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                dishesMutFlow.value,
            )

    fun getFilteredDishesByType(type: String): StateFlow<List<Dish>> {
        return searchText
            .combine(dishesMutFlow) { text, dishes ->
                val filteredByType = dishes.filter { it.type == type }
                if (text.isBlank()) {
                    filteredByType
                } else {
                    filteredByType.filter { it.doesSearchMatchQuery(text) }
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList(),
            )
    }

    val getFilteredDishesByFavorite =
        searchText
            .combine(dishesMutFlow) { text, dishes ->
                val filteredByFavorite = dishes.filter { it.isFavorite }
                if (text.isBlank()) {
                    filteredByFavorite
                } else {
                    filteredByFavorite.filter { it.doesSearchMatchQuery(text) }
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList(),
            )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun clearSearchText() {
        _searchText.value = ""
    }

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

    fun toggleRating(
        dishId: Int,
        newRating: Int,
    ) {
        dishesMutFlow.value =
            dishesMutFlow.value.map { dish ->
                if (dish.id == dishId) {
                    dish.copy(rating = newRating)
                } else {
                    dish
                }
            }
    }
}
