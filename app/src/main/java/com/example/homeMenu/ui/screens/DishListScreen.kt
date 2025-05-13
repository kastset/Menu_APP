@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.homeMenu.ui.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homeMenu.R
import com.example.homeMenu.model.Dish
import com.example.homeMenu.ui.AppViewModelProvider
import com.example.homeMenu.ui.components.AddOrEditButton
import com.example.homeMenu.ui.components.ListOfCard
import com.example.homeMenu.ui.components.TopBarContent
import com.example.homeMenu.viewModel.AuthViewModel
import com.example.homeMenu.viewModel.TypeListViewModel
import kotlinx.coroutines.flow.filterNotNull

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishListScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedVisibilityScope,
    paddingValues: PaddingValues,
    type: String,
    viewModel: TypeListViewModel = viewModel(factory = AppViewModelProvider.Factory),
    authViewModel: AuthViewModel,
    onDishClick: (Dish) -> Unit,
    onPressBack: () -> Unit,
    onCreateClick: () -> Unit,
) {
    val filteredDish by viewModel.listOfDish(type).collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val searchText by viewModel.searchText.collectAsState()

    var isSearch by remember { mutableStateOf(false) }

    val isAdmin by authViewModel.isAdmin.collectAsState()
    with(sharedTransitionScope) {
        Scaffold(
            modifier =
                Modifier
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    modifier =
                        Modifier.renderInSharedTransitionScopeOverlay(
                            zIndexInOverlay = 1f,
                        ),
                    scrollBehavior = scrollBehavior,
                    title = {
                        TopBarContent(
                            isSearch = isSearch,
                            searchText = searchText,
                            onSearchTextChange = viewModel::onSearchTextChange,
                            screenTitle = "Список блюд для типа: $type",
                            onSearchToggle = {
                                if (isSearch) viewModel.clearSearchText()
                                isSearch = !isSearch
                            },
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                if (isSearch) viewModel.clearSearchText()
                                isSearch = !isSearch
                            },
                        ) {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = stringResource(R.string.search),
                            )
                        }
                    },
                    colors =
                        TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer,
                            titleContentColor = MaterialTheme.colorScheme.onSurface,
                            navigationIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        ),
                    navigationIcon = {
                        if (isSearch) {
                            IconButton(
                                onClick = {
                                    isSearch = !isSearch
                                    viewModel.clearSearchText()
                                },
                                modifier =
                                    Modifier
                                        .size(48.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = stringResource(R.string.go_back),
                                    modifier = Modifier.size(24.dp),
                                )
                            }
                        } else {
                            IconButton(
                                onClick = { onPressBack() },
                                modifier =
                                    Modifier
                                        .size(48.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = stringResource(R.string.go_back),
                                    modifier = Modifier.size(24.dp),
                                )
                            }
                        }
                    },
                )
            },
            floatingActionButton = {
                if (isAdmin) {
                    AddOrEditButton(
                        onScreenClick = { onCreateClick() },
                        paddingValues = paddingValues,
                        descriptionText = "Create a new Dish",
                        modifier =
                            Modifier.renderInSharedTransitionScopeOverlay(
                                zIndexInOverlay = 1f,
                            ),
                    )
                }
            },
        ) {
            Column(
                modifier =
                    Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .fillMaxSize()
                        .padding(
                            top = it.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding(),
                        ),
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .pointerInput(isSearch) {
                                detectTapGestures(onTap = {
                                    if (isSearch) {
                                        viewModel.clearSearchText()
                                        isSearch = !isSearch
                                    }
                                })
                            },
                ) {
                    items(filteredDish.dishList) { dish ->
                        Spacer(modifier = Modifier.height(8.dp))
                        ListOfCard(
                            sharedTransitionScope = sharedTransitionScope,
                            animatedContentScope = animatedContentScope,
                            onDishClick = onDishClick,
                            dish = dish,
                            getDishById = { viewModel.getDishById(dish.id).filterNotNull() },
                            onFavoriteClick = { viewModel.toggleFavorite(dish.id) },
                        ) { rating ->
                            viewModel.toggleRating(dish.id, rating)
                        }
                    }
                }
            }
        }
    }
}

// @Preview(
//    name = "Light theme",
//    showBackground = true,
//    showSystemUi = true,
//    uiMode = Configuration.UI_MODE_NIGHT_NO,
// )
// @Preview(
//    name = "Dark theme",
//    showBackground = true,
//    showSystemUi = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
// )
// @Composable
// private fun DishCardPreview() {
//    val viewModel = DishViewModel()
//
//    SharedTransitionLayout {
//        val paddingValues = PaddingValues(16.dp)
//        AnimatedVisibility(visible = true) {
//            TestTheme {
//                DishListScreen(
//                    sharedTransitionScope = this@SharedTransitionLayout,
//                    animatedContentScope = this,
//                    paddingValues = paddingValues,
//                    type = "Супы",
//                    viewModel = viewModel,
//                    onDishClick = {},
//                    onPressBack = {},
//                )
//            }
//        }
//    }
// }

// @Composable
// fun PreviewWrapper(content: @Composable () -> Unit) {
//    TestTheme {
//        SharedTransitionLayout {
//            AnimatedVisibility(visible = true) {
//                CompositionLocalProvider(
//                    LocalSharedTransitionScope provides this@SharedTransitionLayout,
//                    LocalNavAnimatedVisibilityScope provides this,
//                ) {
//                    content()
//                }
//            }
//        }
//    }
// }
