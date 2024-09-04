package com.example.test.ui.navigation

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.example.test.model.Dish
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
sealed class NavRoute {
    @Serializable
    data object MainScreen : NavRoute()

    @Serializable
    data class DishListScreen(val type: String) : NavRoute()

    @Serializable
    data class DishDetailScreen(val dish: Dish) : NavRoute()
}

inline fun <reified T : Parcelable> NavType.Companion.mapper(): NavType<T> {
    return object : NavType<T>(
        isNullableAllowed = false,
    ) {
        override fun put(
            bundle: Bundle,
            key: String,
            value: T,
        ) {
            bundle.putParcelable(key, value)
        }

        override fun get(
            bundle: Bundle,
            key: String,
        ): T? {
            return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                @Suppress("DEPRECATION")
                bundle.getParcelable(key)
            } else {
                bundle.getParcelable(key, T::class.java)
            }
        }

        override fun serializeAsValue(value: T): String {
            // Serialized values must always be Uri encoded
            return Uri.encode(Json.encodeToString(value))
        }

        override fun parseValue(value: String): T {
            // Navigation takes care of decoding the string
            // before passing it to parseValue()
            return Json.decodeFromString<T>(value)
        }
    }
}
