package com.example.test.ui.home

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.model.Dish
import com.example.test.ui.components.FavoriteButton
import com.example.test.ui.components.HeaderText
import com.example.test.ui.components.ImageLoader
import com.example.test.ui.dish.DishViewModel
import com.example.test.ui.theme.TestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: DishViewModel,
    onTypeClick: (String) -> Unit,
    onDishClick: (Dish) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Menu",
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
                        titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    ),
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
//                    Modifier
//                        .clip(CircleShape)
//                        .background(MaterialTheme.colorScheme.secondaryContainer)
//                        .size(48.dp),
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
            verticalArrangement = Arrangement.Center,
        ) {
            ListOfTypeDish(onTypeClick = onTypeClick)
            Spacer(modifier = Modifier.height(32.dp))

            val favoriteDishes by viewModel.dishesFlow.collectAsState()
            val listOfFavoriteDish = favoriteDishes.filter { it.isFavorite }

            FavouriteDishGrid(
                viewModel = viewModel,
                listOfFavoriteDish = listOfFavoriteDish,
                onDishClick = onDishClick,
                onFavoriteClick = { dishId ->
                    viewModel.toggleFavorite(dishId)
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
            text = "List of a dish",
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
            listOf("Завтрак", "Супы"),
            listOf("Гарнир", "Салаты"),
            listOf("Мясо/Рыба", "Десерты"),
            listOf("Напитки", "Закуски"),
        )
    for (row in types) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            for (type in row) {
                FilterButton(onTypeClick, type)
                Spacer(modifier = Modifier.width(8.dp))
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

@Composable
fun FavouriteDishGrid(
    viewModel: DishViewModel,
    listOfFavoriteDish: List<Dish>,
    onDishClick: (Dish) -> Unit,
    onFavoriteClick: (Int) -> Unit,
) {
    Column {
        HeaderText(
            text = "Love dishes",
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleMedium,
            textColor = MaterialTheme.colorScheme.tertiary,
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(220.dp),
        ) {
            items(listOfFavoriteDish) { dish ->

                val updatedDish =
                    viewModel.dishesFlow.collectAsState().value.find { it.id == dish.id }
                val isFavorite by rememberUpdatedState(
                    updatedDish?.isFavorite ?: dish.isFavorite,
                )

                val iconTint by animateColorAsState(
                    targetValue = if (isFavorite) Color.Red else Color.Gray,
                )

                DishCard(
                    dish = dish,
                    onFavoriteClick = onFavoriteClick,
                    onDishClick = onDishClick,
                    updatedDish = updatedDish,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishCard(
    dish: Dish,
    onFavoriteClick: (Int) -> Unit,
    onDishClick: (Dish) -> Unit,
    updatedDish: Dish?,
) {
    Card(
        onClick = {
            onDishClick(dish)
        },
        modifier =
            Modifier
                .size(180.dp, 180.dp)
                .background(MaterialTheme.colorScheme.surface),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainerHigh),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Box(
            modifier =
                Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                ImageLoader(
                    imageUrl = dish.imageUrl,
                    modifier =
                        Modifier
                            .size(125.dp)
                            .padding(bottom = 8.dp),
                )

                Text(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(align = Alignment.CenterHorizontally),
                    text = dish.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }

            FavoriteButton(
                modifier =
                    Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .size(48.dp),
                onFavoriteClick = { onFavoriteClick(dish.id) },
                updatedDish = updatedDish,
                iconTint = MaterialTheme.colorScheme.secondary,
            )
        }
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
fun MainScreenPreview() {
    val viewModel = DishViewModel()
    TestTheme {
        MainScreen(viewModel = viewModel, {}, { })
    }
}
