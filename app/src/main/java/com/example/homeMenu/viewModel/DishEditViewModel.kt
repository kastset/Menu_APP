package com.example.homeMenu.viewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavType
import androidx.navigation.toRoute
import com.example.homeMenu.data.DishesRepository
import com.example.homeMenu.model.Dish
import com.example.homeMenu.ui.navigation.NavRoute
import com.example.homeMenu.ui.navigation.mapper
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.reflect.typeOf

class DishEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val dishesRepository: DishesRepository,
) : ViewModel() {
    private var currentDish = MutableStateFlow(Dish())
        private set

    init {
        currentDish.update {
            savedStateHandle.toRoute<NavRoute.DishEditScreen>(
                typeMap =
                    mapOf(
                        typeOf<Dish>() to NavType.mapper<Dish>(),
                    ),
            ).dish
        }
    }

    private val _nameState = MutableStateFlow(currentDish.value.name)
    val nameState = _nameState.asStateFlow()

    private val _recipeState = MutableStateFlow(currentDish.value.recipeLink)
    val recipeState = _recipeState.asStateFlow()

    private val _imageState = MutableStateFlow(currentDish.value.imageUrl)
    val imageState = _imageState.asStateFlow()

    private val _typeState = MutableStateFlow(currentDish.value.type)
    val typeState = _typeState.asStateFlow()

    private val _emptyNameField = MutableStateFlow(false)
    val emptyNameField = _emptyNameField.asStateFlow()

    private val _emptyRecipeField = MutableStateFlow(false)
    val emptyRecipeField = _emptyRecipeField.asStateFlow()

    private val _emptyImageField = MutableStateFlow(false)
    val emptyImageField = _emptyImageField.asStateFlow()

    private val _emptyTypeField = MutableStateFlow(false)
    val emptyTypeField = _emptyTypeField.asStateFlow()

    fun nameTextChange(name: String) {
        _nameState.value = name
        _emptyNameField.value = name.trim().isBlank()
    }

    fun recipeTextChange(recipe: String) {
        _recipeState.value = recipe
        _emptyRecipeField.value = recipe.trim().isBlank()
    }

    fun imageTextChange(imageUrl: String) {
        _imageState.value = imageUrl
        _emptyImageField.value = imageUrl.trim().isBlank()
    }

    fun typeTextChange(type: String) {
        _typeState.value = type
        _emptyTypeField.value = type == "Категории"
    }

    private val db = Firebase.firestore

    fun isDishSaved(): Boolean {
        return (
            _nameState.value.trim().isBlank() || _recipeState.value.trim().isBlank() ||
                _imageState.value.trim().isBlank() || _typeState.value.trim().isBlank()
        )
    }

    fun updateDish() {
        viewModelScope.launch {
            val newDish =
                Dish(
                    id = currentDish.value.id,
                    name = nameState.value,
                    type = typeState.value,
                    imageUrl = imageState.value,
                    recipeLink = recipeState.value,
                    isFavorite = currentDish.value.isFavorite,
                    rating = currentDish.value.rating,
                )
            val newDishData =
                mapOf(
                    "name" to nameState.value,
                    "type" to typeState.value,
                    "imageUrl" to imageState.value,
                    "recipeLink" to recipeState.value,
                )
            db.collection("dishes")
                .whereEqualTo("id", currentDish.value.id)
                .get()
                .addOnSuccessListener { result ->
                    if (!result.isEmpty) {
                        val documentId = result.documents.first().id

                        db.collection("dishes").document(documentId)
                            .update(newDishData)
                            .addOnSuccessListener {
                                Log.d("Firestore", "Блюдо обновлено")
                            }
                    } else {
                        Log.d("Firestore", "Блюдо с id=${currentDish.value.id} не найдено")
                    }
                }
            dishesRepository.updateDish(newDish)
        }
    }

    fun deleteDish() {
        viewModelScope.launch {
            db.collection("dishes")
                .whereEqualTo("id", currentDish.value.id)
                .get()
                .addOnSuccessListener { result ->
                    if (!result.isEmpty) {
                        val documentId = result.documents.first().id

                        db.collection("dishes").document(documentId)
                            .delete()
                            .addOnSuccessListener {
                                Log.d("Firestore", "Блюдо удалено")
                            }
                    } else {
                        Log.d("Firestore", "Блюдо с id=${currentDish.value.id} не найдено")
                    }
                }
            dishesRepository.deleteDish(currentDish.value)
        }
    }
}
