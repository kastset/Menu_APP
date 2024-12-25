@file:OptIn(
    ExperimentalSharedTransitionApi::class,
    ExperimentalSharedTransitionApi::class,
    ExperimentalSharedTransitionApi::class,
)

package com.example.test.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.test.model.Dish
import com.example.test.ui.bottombar.FavoriteDishListScreen
import com.example.test.ui.bottombar.MenuScreen
import com.example.test.ui.dish.DishDetailScreen
import com.example.test.ui.dish.DishListScreen
import com.example.test.ui.dish.DishViewModel
import com.example.test.ui.home.MainScreen
import kotlin.reflect.typeOf

@Composable
fun AppNavHost(
    gridState: LazyListState,
    navController: NavHostController,
    viewModel: DishViewModel,
    paddingValues: PaddingValues,
) {
    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = NavRoute.MainScreen) {
            composable<NavRoute.MainScreen> {
                MainScreen(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable,
                    paddingValues = paddingValues,
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
                if (type != null) {
                    DishListScreen(
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable,
                        paddingValues = paddingValues,
                        type = type,
                        viewModel,
                        onDishClick = { dish ->
                            navController.navigate(NavRoute.DishDetailScreen(dish))
                        },
                        onPressBack = { navController.navigateUp() },
                    )
                }
            }
            composable<NavRoute.DishDetailScreen>(
                typeMap = mapOf(typeOf<Dish>() to NavType.mapper<Dish>()),
            ) {
                val dish = it.toRoute<NavRoute.DishDetailScreen>().dish
                DishDetailScreen(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable,
                    paddingValues = paddingValues,
                    dish = dish,
                    viewModel = viewModel,
                    onPressBack = { navController.navigateUp() },
                )
            }
            composable<NavRoute.MenuScreen>(
                typeMap = mapOf(typeOf<Dish>() to NavType.mapper<Dish>()),
            ) {
                MenuScreen(
                    gridState = gridState,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable,
                    paddingValues = paddingValues,
                    viewModel = viewModel,
                    onDishClick = { dish ->
                        navController.navigate(NavRoute.DishDetailScreen(dish))
                    },
                    onPressBack = { navController.popBackStack() },
                )
            }
            composable<NavRoute.FavoriteDishListScreen>(
                typeMap = mapOf(typeOf<Dish>() to NavType.mapper<Dish>()),
            ) {
                FavoriteDishListScreen(
                    gridState = gridState,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable,
                    paddingValues = paddingValues,
                    viewModel = viewModel,
                    onDishClick = { dish ->
                        navController.navigate(NavRoute.DishDetailScreen(dish))
                    },
                    onPressBack = { navController.popBackStack() },
                )
            }
        }
    }
}
