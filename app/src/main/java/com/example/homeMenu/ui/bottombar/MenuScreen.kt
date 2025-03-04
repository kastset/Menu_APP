@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.homeMenu.ui.bottombar

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homeMenu.R
import com.example.homeMenu.model.Dish
import com.example.homeMenu.ui.AppViewModelProvider
import com.example.homeMenu.ui.components.ListOfDish
import com.example.homeMenu.viewModel.MenuViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedVisibilityScope,
    paddingValues: PaddingValues,
    gridState: LazyListState,
    viewModel1: MenuViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onDishClick: (Dish) -> Unit,
    onPressBack: () -> Unit,
) {
    ListOfDish(
        sharedTransitionScope = sharedTransitionScope,
        animatedContentScope = animatedContentScope,
        paddingValues = paddingValues,
        screenTitle = stringResource(R.string.all_dish),
        gridState = gridState,
        viewModel = viewModel1,
        onDishClick = onDishClick,
        onPressBack = onPressBack,
    )
}
