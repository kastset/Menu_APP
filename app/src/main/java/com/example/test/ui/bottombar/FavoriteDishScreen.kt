package com.example.test.ui.bottombar

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
import androidx.compose.foundation.lazy.LazyListState
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
import androidx.compose.ui.unit.dp
import com.example.test.model.Dish
import com.example.test.ui.components.TopBarContent
import com.example.test.ui.dish.DishViewModel
import com.example.test.ui.dish.ListTypeCard

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun FavoriteDishListScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedVisibilityScope,
    paddingValues: PaddingValues,
    gridState: LazyListState,
    viewModel: DishViewModel,
    onDishClick: (Dish) -> Unit,
    onPressBack: () -> Unit,
) {
    val listOfFavoriteDish by viewModel.getFilteredDishesByFavorite.collectAsState()
    val searchText by viewModel.searchText.collectAsState()

    var isSearch by remember { mutableStateOf(true) }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier =
            Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = {
                    TopBarContent(
                        isSearch = isSearch,
                        searchText = searchText,
                        onSearchTextChange = viewModel::onSearchTextChange,
                        screenTitle = "Любимые Блюда",
                    )
                },
                actions = {
                    IconButton(onClick = { isSearch = !isSearch }) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Поиск",
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
                    if (!isSearch) {
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
                                contentDescription = "Go Back",
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
                                contentDescription = "Go Back",
                                modifier = Modifier.size(24.dp),
                            )
                        }
                    }
                },
            )
        },
    ) {
        Column(
            modifier =
                Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(
                        top = it.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding(),
                    )
                    .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .pointerInput(isSearch) {
                            detectTapGestures(onTap = {
                                if (!isSearch) {
                                    isSearch = true
                                    viewModel.clearSearchText()
                                }
                            })
                        },
                state = gridState,
                contentPadding = PaddingValues(horizontal = 16.dp),
//                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(listOfFavoriteDish) { dish ->
                    ListTypeCard(
                        sharedTransitionScope = sharedTransitionScope,
                        animatedContentScope = animatedContentScope,
                        onDishClick = onDishClick,
                        dish = dish,
                        viewModel,
                    )
                }
            }
        }
    }
}
