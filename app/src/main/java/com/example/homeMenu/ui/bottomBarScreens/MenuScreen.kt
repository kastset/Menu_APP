@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.homeMenu.ui.bottomBarScreens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homeMenu.R
import com.example.homeMenu.model.Dish
import com.example.homeMenu.ui.AppViewModelProvider
import com.example.homeMenu.ui.components.ListOfDish
import com.example.homeMenu.viewModel.AuthViewModel
import com.example.homeMenu.viewModel.MenuViewModel

@Composable
fun MenuScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedVisibilityScope,
    paddingValues: PaddingValues,
    gridState: LazyListState,
    viewModel: MenuViewModel = viewModel(factory = AppViewModelProvider.Factory),
    authViewModel: AuthViewModel,
    onDishClick: (Dish) -> Unit,
    onPressBack: () -> Unit,
    onCreateClick: () -> Unit,
) {
    ListOfDish(
        sharedTransitionScope = sharedTransitionScope,
        animatedContentScope = animatedContentScope,
        paddingValues = paddingValues,
        screenTitle = stringResource(R.string.all_dish),
        gridState = gridState,
        viewModel = viewModel,
        authViewModel = authViewModel,
        onDishClick = onDishClick,
        onPressBack = onPressBack,
        onCreateClick = onCreateClick,
    )
}
