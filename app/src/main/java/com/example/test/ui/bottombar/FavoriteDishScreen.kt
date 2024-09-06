package com.example.test.ui.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.test.model.Dish
import com.example.test.ui.dish.DishViewModel
import com.example.test.ui.dish.ListTypeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteDishListScreen(
    gridState: LazyGridState,
    paddingValues: PaddingValues,
    viewModel: DishViewModel,
    onDishClick: (Dish) -> Unit,
    onPressBack: () -> Unit,
) {
    val favoriteDishes by viewModel.dishesFlow.collectAsState()
    val listOfFavoriteDish = favoriteDishes.filter { it.isFavorite }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier =
            Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = {
                    Text(
                        text = "Любимые Блюда",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                },
                actions = {
                    IconButton({ /*TODO*/ }) {
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
                    IconButton(
                        onClick = { onPressBack() },
                        modifier =
                            Modifier
                                .size(48.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go Back",
                            modifier = Modifier.size(24.dp),
                        )
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
            LazyVerticalGrid(
                modifier =
                    Modifier
                        .fillMaxSize(),
                columns = GridCells.Fixed(1),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                state = gridState,
            ) {
                items(listOfFavoriteDish) { dish ->

                    ListTypeCard(
                        onDishClick = onDishClick,
                        dish = dish,
                        viewModel,
                    )
                }
            }
        }
    }
}
