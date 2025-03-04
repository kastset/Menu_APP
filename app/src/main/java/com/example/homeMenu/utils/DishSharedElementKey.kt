package com.example.homeMenu.utils

data class DishSharedElementKey(
    val dishId: Int,
    val origin: String,
    val type: DishSharedElementType,
)

enum class DishSharedElementType {
    Bounds,
    Image,
    Title,
    Tagline,
    Background,
}

object FilterSharedElementKey
