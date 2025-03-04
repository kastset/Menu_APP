package com.example.homeMenu.ui.bottombar

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
import com.example.homeMenu.viewModel.FavoriteViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FavoriteDishListScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedVisibilityScope,
    paddingValues: PaddingValues,
    gridState: LazyListState,
    viewModel1: FavoriteViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onDishClick: (Dish) -> Unit,
    onPressBack: () -> Unit,
) {
    ListOfDish(
        sharedTransitionScope = sharedTransitionScope,
        animatedContentScope = animatedContentScope,
        paddingValues = paddingValues,
        screenTitle = stringResource(R.string.favorites),
        gridState = gridState,
        viewModel = viewModel1,
        onDishClick = onDishClick,
        onPressBack = onPressBack,
    )
}
