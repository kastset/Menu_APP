@file:OptIn(
    ExperimentalSharedTransitionApi::class,
    ExperimentalSharedTransitionApi::class,
    ExperimentalSharedTransitionApi::class,
    ExperimentalSharedTransitionApi::class,
)

package com.example.homeMenu.ui.navigation

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
import com.example.homeMenu.model.Dish
import com.example.homeMenu.ui.bottomBarScreens.FavoriteDishListScreen
import com.example.homeMenu.ui.bottomBarScreens.MenuScreen
import com.example.homeMenu.ui.homeScreen.MainScreen
import com.example.homeMenu.ui.screens.DishCreationScreen
import com.example.homeMenu.ui.screens.DishDetailScreen
import com.example.homeMenu.ui.screens.DishEditScreen
import com.example.homeMenu.ui.screens.DishListScreen
import com.example.homeMenu.viewModel.AuthViewModel
import kotlin.reflect.typeOf

@Composable
fun AppNavHost(
    gridState: LazyListState,
    navController: NavHostController,
    paddingValues: PaddingValues,
    authViewModel: AuthViewModel,
) {
    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = NavRoute.MainScreen) {
            composable<NavRoute.MainScreen> {
                MainScreen(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable,
                    paddingValues = paddingValues,
                    authViewModel = authViewModel,
                    onTypeClick = { type ->
                        navController.navigate(
                            NavRoute.DishListScreen(type),
                        )
                    },
                    onDishClick = { dish ->
                        navController.navigate(NavRoute.DishDetailScreen(dish))
                    },
                    onCreateClick = {
                        navController.navigate(NavRoute.DishCreationScreen)
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
                        authViewModel = authViewModel,
                        onDishClick = { dish ->
                            navController.navigate(NavRoute.DishDetailScreen(dish))
                        },
                        onPressBack = { navController.navigateUp() },
                        onCreateClick = { navController.navigate(NavRoute.DishCreationScreen) },
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
                    authViewModel = authViewModel,
                    dish = dish,
                    onPressBack = { navController.navigateUp() },
                    onEditClick = { navController.navigate(NavRoute.DishEditScreen(dish)) },
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
                    authViewModel = authViewModel,
                    onDishClick = { dish ->
                        navController.navigate(NavRoute.DishDetailScreen(dish))
                    },
                    onPressBack = { navController.popBackStack() },
                    onCreateClick = { navController.navigate(NavRoute.DishCreationScreen) },
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
                    authViewModel = authViewModel,
                    onDishClick = { dish ->
                        navController.navigate(NavRoute.DishDetailScreen(dish))
                    },
                    onPressBack = { navController.popBackStack() },
                    onCreateClick = { navController.navigate(NavRoute.DishCreationScreen) },
                )
            }
            composable<NavRoute.DishCreationScreen>(
                typeMap = mapOf(typeOf<Dish>() to NavType.mapper<Dish>()),
            ) {
                DishCreationScreen(
                    paddingValues = paddingValues,
                    onPressBack = { navController.popBackStack() },
                    onDishSave = { navController.popBackStack() },
                )
            }
            composable<NavRoute.DishEditScreen>(
                typeMap = mapOf(typeOf<Dish>() to NavType.mapper<Dish>()),
            ) {
                DishEditScreen(
                    paddingValues = paddingValues,
                    onPressBack = { navController.popBackStack() },
                    onSaveBack = { navController.popBackStack() },
                    onDeleteClick = { type ->
                        navController.navigate(NavRoute.DishListScreen(type))
                    },
                )
            }
        }
    }
}
