package com.example.test.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Dish(
    val id: Int,
    val name: String,
    val type: String
) : Parcelable

val dishes = listOf(
    Dish(1, "Egg_Pashot", "Завтрак"),
    Dish(0, "Tea", "Завтрак"),
    Dish(0, "Borsh", "Супы"),
    Dish(2, "Mashroom_soup", "Супы"),
    Dish(3, "Vagu", "Гарнир"),
    Dish(4, "Egg_Benedict1", "Завтрак"),
    Dish(1, "Egg_Pashot1", "Завтрак"),
    Dish(0, "Tea1", "Завтрак"),
    Dish(0, "Borsh1", "Завтрак"),
    Dish(2, "Mashroom_soup1", "Завтрак"),
    Dish(3, "Vagu1", "Завтрак"),
    Dish(4, "Egg_Benedict1", "Завтрак"),
    Dish(1, "Egg_Pashot", "Завтрак"),
    Dish(0, "Tea", "Завтрак"),
    Dish(0, "Borsh", "Супы"),
    Dish(2, "Mashroom_soup", "Супы"),
    Dish(3, "Vagu", "Гарнир"),
    Dish(4, "Egg_Benedict1", "Завтрак"),
    Dish(1, "Egg_Pashot1", "Завтрак"),
    Dish(0, "Tea1", "Завтрак"),
    Dish(0, "Borsh1", "Завтрак"),
    Dish(2, "Mashroom_soup1", "Завтрак"),
    Dish(3, "Vagu1", "Завтрак"),
    Dish(4, "Egg_Benedict1", "Завтрак"),
    Dish(0, "Borsh1", "Завтрак"),
    Dish(2, "Mashroom_soup1", "Завтрак"),
    Dish(3, "Vagu1", "Завтрак"),
    Dish(4, "Egg_Benedict1", "Завтрак"),
    Dish(1, "Egg_Pashot", "Завтрак"),
    Dish(0, "Tea", "Завтрак"),
    Dish(0, "Borsh", "Супы"),
    Dish(2, "Mashroom_soup", "Супы"),
    Dish(3, "Vagu", "Гарнир"),
    Dish(4, "Egg_Benedict1", "Завтрак"),
    Dish(1, "Egg_Pashot1", "Завтрак"),
    Dish(0, "Tea1", "Завтрак"),
    Dish(0, "Borsh1", "Завтрак"),
    Dish(2, "Mashroom_soup1", "Завтрак"),
    Dish(3, "Vagu1", "Завтрак"),
    Dish(4, "Egg_Benedict1", "Завтрак"),
    Dish(0, "Borsh1", "Завтрак"),
    Dish(2, "Mashroom_soup1", "Завтрак"),
    Dish(3, "Vagu1", "Завтрак"),
    Dish(4, "Egg_Benedict1", "Завтрак"),
    Dish(1, "Egg_Pashot", "Завтрак"),
    Dish(0, "Tea", "Завтрак"),
    Dish(0, "Borsh", "Супы"),
    Dish(2, "Mashroom_soup", "Супы"),
    Dish(3, "Vagu", "Гарнир"),
    Dish(4, "Egg_Benedict1", "Завтрак"),
    Dish(1, "Egg_Pashot1", "Завтрак"),
    Dish(0, "Tea1", "Завтрак"),
    Dish(0, "Borsh1", "Завтрак"),
    Dish(2, "Mashroom_soup1", "Завтрак"),
    Dish(3, "Vagu1", "Завтрак"),
    Dish(4, "Egg_Benedict1", "Завтрак"),

    )