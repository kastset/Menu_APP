package com.example.test.ui.dish

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.R
import com.example.test.model.Dish
import com.example.test.model.dishes
import com.example.test.ui.components.DishRatingIcon
import com.example.test.ui.components.FavoriteButton
import com.example.test.ui.components.ImageLoader
import com.example.test.ui.theme.TestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishListScreen(
    type: String,
    viewModel: DishViewModel,
    onDishClick: (Dish) -> Unit,
    onPressBack: () -> Unit,
) {
    val types = dishes.filter { type == it.type }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Список блюд для типа: $type",
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
                                .wrapContentSize(Alignment.TopStart)
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
        bottomBar = {
//            BottomAppBar(
//                containerColor = MaterialTheme.colorScheme.surfaceContainer,
//                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
//            ) {
//                Spacer(modifier = Modifier.weight(0.5f))
//                IconButton(
//                    onClick = { /*TODO*/ },
//                    modifier =
//                        Modifier
//                            .clip(CircleShape)
//                            .background(MaterialTheme.colorScheme.secondaryContainer)
//                            .size(48.dp),
//                ) {
//                    Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
//                }
//                Spacer(modifier = Modifier.weight(1f))
//                IconButton(
//                    onClick = { /*TODO*/ },
//                    modifier =
//                        Modifier
//                            .clip(CircleShape)
//                            .background(MaterialTheme.colorScheme.secondaryContainer)
//                            .size(48.dp),
//                ) {
//                    Icon(imageVector = Icons.Default.List, contentDescription = "List")
//                }
//                Spacer(modifier = Modifier.weight(1f))
//                IconButton(
//                    onClick = { /*TODO*/ },
//                    modifier =
//                        Modifier
//                            .clip(CircleShape)
//                            .background(MaterialTheme.colorScheme.secondaryContainer)
//                            .size(48.dp),
//                ) {
//                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite")
//                }
//                Spacer(modifier = Modifier.weight(0.5f))
//            }
        },
    ) {
        Column(
            modifier =
                Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxSize()
                    .padding(it),
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier =
                    Modifier
                        .fillMaxSize(),
            ) {
                items(types) { dish ->
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListTypeCard(
    onDishClick: (Dish) -> Unit,
    dish: Dish,
    viewModel: DishViewModel,
) {
    val updatedDish = viewModel.dishesFlow.collectAsState().value.find { it.id == dish.id }
    val isFavorite by rememberUpdatedState(updatedDish?.isFavorite ?: dish.isFavorite)

    val backgroundTint by animateColorAsState(
        targetValue = if (isFavorite) MaterialTheme.colorScheme.surfaceContainerHigh else MaterialTheme.colorScheme.surfaceContainerLow,
    )

    val iconTint by animateColorAsState(
        targetValue = if (isFavorite) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant,
    )

    Card(
        onClick = {
            onDishClick(dish)
        },
        modifier =
            Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainerHigh),
        elevation = CardDefaults.cardElevation(16.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ImageLoader(
                    imageUrl = dish.imageUrl,
                    modifier =
                        Modifier
                            .size(100.dp)
                            .padding(bottom = 8.dp),
                )

                Text(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(align = Alignment.CenterHorizontally),
                    text = dish.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                )
            }

            DishRatingIcon(
                modifier =
                    Modifier
                        .wrapContentSize(Alignment.TopStart)
                        .padding(top = 4.dp, end = 4.dp, start = 4.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceContainerLow)
                        .size(48.dp),
                backgroundColorIcon = MaterialTheme.colorScheme.surfaceContainerLow,
                updatedDish = updatedDish,
            ) { rating ->
                viewModel.toggleRating(dish.id, rating)
            }

            Box(
                modifier =
                    Modifier
                        .align(Alignment.TopEnd)
                        .size(48.dp, 100.dp)
                        .padding(top = 4.dp, end = 4.dp),
            ) {
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround,
                ) {
                    FavoriteButton(
                        modifier =
                            Modifier
                                .wrapContentSize(Alignment.TopEnd)
                                .clip(CircleShape)
                                .size(48.dp)
                                .background(backgroundTint),
                        onFavoriteClick = { viewModel.toggleFavorite(dish.id) },
                        updatedDish = updatedDish,
                        iconTint = iconTint,
                    )

                    DishPrepIcon(
                        Modifier
                            .wrapContentSize(Alignment.TopEnd)
                            .size(48.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun DishPrepIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
    ) {
        Icon(
            painter =
                painterResource(
                    id = R.drawable.free_icon_clock_7164560,
                    // Эта обложка была разработана с использованием ресурсов сайта Flaticon.com.
                ),
            contentDescription = null,
            modifier =
                Modifier
                    .size(24.dp)
                    .align(Alignment.Center)
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh),
            tint = MaterialTheme.colorScheme.secondary,
        )

        Text(
            // dish.time
            text = "30",
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier =
                Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center),
        )
    }
}

@Preview(
    name = "Light theme",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Dark theme",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun DetailListView() {
    val viewModel = DishViewModel()
    TestTheme {
        DishListScreen(
            type = "Супы",
            viewModel = viewModel,
            onDishClick = {},
            onPressBack = {},
        )
    }
}
