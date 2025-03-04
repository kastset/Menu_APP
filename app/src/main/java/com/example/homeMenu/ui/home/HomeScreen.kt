@file:OptIn(
    ExperimentalSharedTransitionApi::class,
    ExperimentalAnimationApi::class,
    ExperimentalSharedTransitionApi::class,
)

package com.example.homeMenu.ui.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homeMenu.R
import com.example.homeMenu.model.Dish
import com.example.homeMenu.ui.AppViewModelProvider
import com.example.homeMenu.ui.components.HeaderText
import com.example.homeMenu.ui.components.ListOfCard
import com.example.homeMenu.ui.components.TopBarContent
import kotlinx.coroutines.flow.filterNotNull
import com.example.homeMenu.viewModel.HomeViewModel as HomeViewModel1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedVisibilityScope,
    paddingValues: PaddingValues,
    viewModel: HomeViewModel1 = viewModel(factory = AppViewModelProvider.Factory),
    onTypeClick: (String) -> Unit,
    onDishClick: (Dish) -> Unit,
) {
    val allDish by viewModel.listOfDish.collectAsState()
    val searchText by viewModel.searchText.collectAsState()

    var isSearch by remember { mutableStateOf(false) }

    with(sharedTransitionScope) {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier =
                        Modifier.renderInSharedTransitionScopeOverlay(
                            zIndexInOverlay = 1f,
                        ),
                    title = {
                        TopBarContent(
                            isSearch = isSearch,
                            searchText = searchText,
                            onSearchTextChange = viewModel::onSearchTextChange,
                            screenTitle = stringResource(R.string.home),
                            {
                                if (isSearch) viewModel.clearSearchText()
                                isSearch = !isSearch
                            },
                        )
                    },
                    actions = {
                        IconButton(onClick = {
                            if (isSearch) viewModel.clearSearchText()
                            isSearch = !isSearch
                        }) {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = stringResource(R.string.search),
                            )
                        }
                    },
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
                        }
                    },
                    colors =
                        TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer,
                            titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        ),
                )
            },
        ) {
            AnimatedContent(
                targetState = isSearch,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                content = { targetState ->
                    if (targetState) {
                        LazyColumn(
                            modifier =
                                Modifier
                                    .fillMaxSize()
                                    .padding(
                                        top = it.calculateTopPadding(),
                                        bottom = paddingValues.calculateBottomPadding(),
                                    )
                                    .pointerInput(isSearch) {
                                        detectTapGestures(onTap = {
                                            if (isSearch) {
                                                viewModel.clearSearchText()
                                                isSearch = !isSearch
                                            }
                                        })
                                    },
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            items(allDish.dishList) { dish ->
                                Spacer(modifier = Modifier.height(8.dp))
                                ListOfCard(
                                    sharedTransitionScope = sharedTransitionScope,
                                    animatedContentScope = animatedContentScope,
                                    onDishClick = onDishClick,
                                    dish = dish,
                                    getDishById = {
                                        viewModel.getDishById(dish.id).filterNotNull()
                                    },
                                    onFavoriteClick = { viewModel.toggleFavorite(dish.id) },
                                ) { rating ->
                                    viewModel.toggleRating(dish.id, rating)
                                }
                            }
                        }
                    } else {
                        Column(
                            modifier =
                                Modifier
                                    .background(MaterialTheme.colorScheme.surface)
                                    .padding(
                                        top = it.calculateTopPadding(),
                                        bottom = paddingValues.calculateBottomPadding(),
                                    )
                                    .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            ListOfTypeDish(onTypeClick = onTypeClick)
                        }
                        Spacer(modifier = Modifier.height(32.dp))
                    }
                },
                transitionSpec = {
                    fadeIn() with fadeOut()
                },
            )
        }
    }
}

@Composable
fun ListOfTypeDish(onTypeClick: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderText(
            text = stringResource(R.string.list_of_a_dish),
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleMedium,
            textColor = MaterialTheme.colorScheme.tertiary,
        )
        Spacer(modifier = Modifier.height(16.dp))
        DishTypes(onTypeClick = onTypeClick)
    }
}

@Composable
fun DishTypes(onTypeClick: (String) -> Unit) {
    val types =
        listOf(
            listOf(stringResource(R.string.breacfast), stringResource(R.string.soup)),
            listOf(stringResource(R.string.garnish), stringResource(R.string.salad)),
            listOf(stringResource(R.string.meat_or_fish), stringResource(R.string.desserts)),
            listOf(stringResource(R.string.drinks), stringResource(R.string.snacks)),
        )
    for (row in types) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            for (type in row) {
                Spacer(modifier = Modifier.width(4.dp))
                FilterButton(onTypeClick, type)
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun FilterButton(
    onTypeClick: (String) -> Unit,
    type: String,
) {
    Button(
        onClick = {
            onTypeClick(type)
        },
        modifier =
            Modifier
                .height(50.dp)
                .width(155.dp),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
        elevation = ButtonDefaults.buttonElevation(8.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Text(
            text = type,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelLarge,
        )
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
// fun MainScreenPreview() {
//    val viewModel = DishViewModel()
//    SharedTransitionLayout {
//        AnimatedVisibility(visible = true) {
//            TestTheme {
//                val paddingValues = PaddingValues(16.dp)
//                MainScreen(
//                    sharedTransitionScope = this@SharedTransitionLayout,
//                    animatedContentScope = this,
//                    paddingValues = paddingValues,
//                    viewModel = viewModel,
//                    {},
//                    {},
//                )
//            }
//        }
//    }
// }
