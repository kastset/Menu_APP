package com.example.test.ui.navigation

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
import com.example.test.ui.home.MainScreen
import kotlin.reflect.typeOf

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: DishViewModel,
) {
    NavHost(navController = navController, startDestination = NavRoute.MainScreen) {
        composable<NavRoute.MainScreen> {
            MainScreen(
                viewModel = viewModel,
                onTypeClick = { type ->
                    navController.navigate(
                        NavRoute.DishListScreen(type),
                    )
                },
                onDishClick = { dish ->
                    navController.navigate(NavRoute.DishDetailScreen(dish))
                },
            )
        }
        composable<NavRoute.DishListScreen>(
            typeMap = mapOf(typeOf<Dish>() to NavType.mapper<Dish>()),
        ) {
            val type = it.toRoute<NavRoute.DishListScreen>().type
            DishListScreen(
                type = type,
                viewModel,
                onDishClick = { dish ->
                    navController.navigate(NavRoute.DishDetailScreen(dish))
                },
                onPressBack = { navController.navigateUp() },
            )
        }
        composable<NavRoute.DishDetailScreen>(
            typeMap = mapOf(typeOf<Dish>() to NavType.mapper<Dish>()),
        ) {
            val dish = it.toRoute<NavRoute.DishDetailScreen>().dish
            DishDetailScreen(
                dish = dish,
                viewModel = viewModel,
                onPressBack = { navController.navigateUp() },
            )
        }
    }
}
