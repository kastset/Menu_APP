package com.example.test.ui.bottombar

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.R
import com.example.test.model.Dish
import com.example.test.ui.AppViewModelProvider
import com.example.test.ui.components.ListOfDish
import com.example.test.viewModel.FavoriteViewModel

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
