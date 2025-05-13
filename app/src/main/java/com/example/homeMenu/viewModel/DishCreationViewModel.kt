package com.example.homeMenu.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeMenu.data.DishesRepository
import com.example.homeMenu.model.Dish
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DishCreationViewModel(private val dishRepository: DishesRepository) : ViewModel() {
    private val _nameState = MutableStateFlow("")
    val nameState = _nameState.asStateFlow()

    private val _recipeState = MutableStateFlow("")
    val recipeState = _recipeState.asStateFlow()

    private val _imageState = MutableStateFlow("")
    val imageState = _imageState.asStateFlow()

    private val _typeState = MutableStateFlow("")
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
    private var lastIdInDb: Int = 0
    val remoteSnapshot =
        db.collection("dishes").get().addOnSuccessListener { dishes ->
            val remoteDishes = dishes.toObjects(Dish::class.java)
            lastIdInDb = remoteDishes.maxByOrNull { it.id }!!.id + 1
        }

    fun isDishSaved(): Boolean {
        return (
            _nameState.value.trim().isBlank() || _recipeState.value.trim().isBlank() ||
                _imageState.value.trim().isBlank() || _typeState.value.trim().isBlank()
        )
    }

    fun saveDish() {
        if (isDishSaved()) {
            return
        } else {
            viewModelScope.launch {
                val newDish =
                    Dish(
                        id = lastIdInDb,
                        name = nameState.value,
                        type = typeState.value,
                        imageUrl = imageState.value,
                        recipeLink = recipeState.value,
                        isFavorite = false,
                        rating = 0,
                    )
                dishRepository.insertDish(newDish)
                db.collection("dishes").add(newDish)
            }
        }
    }
}
