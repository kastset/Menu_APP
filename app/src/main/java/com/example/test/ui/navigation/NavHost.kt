package com.example.test.ui.navigation

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.test.model.Dish
import com.example.test.ui.dish.DishDetailScreen
import com.example.test.ui.dish.DishListScreen
import com.example.test.ui.dish.DishViewModel
import com.example.test.ui.dish.FullDishDetail
import com.example.test.ui.dish.ListOfTypeDish
import com.example.test.ui.home.HomeScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: DishViewModel,
) {
    NavHost(navController = navController, startDestination = HomeScreen) {
        composable<HomeScreen> {
            HomeScreen.MainScreen(
                navController = navController,
                viewModel = viewModel,
            )
        }
        composable<DishListScreen>(
            typeMap = mapOf(typeOf<Dish>() to NavType.mapper<Dish>()),
        ) {
            val dish = it.toRoute<DishListScreen>().dish
            ListOfTypeDish(navController, type = dish.type, viewModel)
        }
        composable<DishDetailScreen>(
            typeMap = mapOf(typeOf<Dish>() to NavType.mapper<Dish>()),
        ) {
            val dish = it.toRoute<DishDetailScreen>().dish
            FullDishDetail(dish, viewModel)
        }
    }
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
