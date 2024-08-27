package com.example.test.ui.home

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.test.model.Dish
import com.example.test.ui.components.FavoriteButton
import com.example.test.ui.components.HeaderText
import com.example.test.ui.components.ImageLoader
import com.example.test.ui.dish.DishDetailScreen
import com.example.test.ui.dish.DishListScreen
import com.example.test.ui.dish.DishViewModel
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen {
    @Composable
    fun MainScreen(
        navController: NavHostController,
        viewModel: DishViewModel,
    ) {
        Column(
            modifier =
                Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp)
                    .statusBarsPadding(),
            verticalArrangement = Arrangement.Center,
        ) {
            ListOfTypeDish(navController)
            Spacer(modifier = Modifier.height(32.dp))

            val favoriteDishes by viewModel.dishesFlow.collectAsState()
            val listOfFavoriteDish = favoriteDishes.filter { it.isFavorite }

            FavouriteDishGrid(
                navController = navController,
                viewModel = viewModel,
                listOfFavoriteDish = listOfFavoriteDish,
                onFavoriteClick = { dishId ->
                    viewModel.toggleFavorite(dishId)
                },
            )
        }
    }

    @Composable
    fun ListOfTypeDish(navController: NavHostController) {
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
                size = 17,
            )
            Spacer(modifier = Modifier.height(16.dp))
            DishTypes(navController = navController)
        }
    }

    @Composable
    fun DishTypes(navController: NavHostController) {
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
                    FilterButton(navController = navController, type = type)
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

    @Composable
    fun FilterButton(
        navController: NavHostController,
        type: String,
    ) {
        Button(
            onClick = {
                navController.navigate(DishListScreen(Dish(0, "", type, "", "")))
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
        ) {
            Text(text = type, color = MaterialTheme.colorScheme.onPrimary)
        }
    }

    @Composable
    fun FavouriteDishGrid(
        navController: NavHostController,
        viewModel: DishViewModel,
        listOfFavoriteDish: List<Dish>,
        onFavoriteClick: (Int) -> Unit,
    ) {
        Column {
            HeaderText(
                text = "Love dishes",
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally),
                size = 17,
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

                    val updatedDish = viewModel.dishesFlow.collectAsState().value.find { it.id == dish.id }
                    val isFavorite by rememberUpdatedState(updatedDish?.isFavorite ?: dish.isFavorite)

                    val iconTint by animateColorAsState(
                        targetValue = if (isFavorite) Color.Red else Color.Gray,
                    )

                    DishCard(
                        navController = navController,
                        dish = dish,
                        iconTint = iconTint,
                        onFavoriteClick = onFavoriteClick,
                        updatedDish = updatedDish,
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DishCard(
        navController: NavHostController,
        dish: Dish,
        onFavoriteClick: (Int) -> Unit,
        iconTint: Color,
        updatedDish: Dish?,
    ) {
        Card(
            onClick = {
                navController.navigate(DishDetailScreen(dish = dish))
            },
            modifier =
                Modifier
                    .size(180.dp, 180.dp)
                    .background(MaterialTheme.colorScheme.background),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
            elevation = CardDefaults.cardElevation(8.dp),
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
                        color = MaterialTheme.colorScheme.onSecondary,
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
                    iconTint = iconTint,
                )
            }
        }
    }
}
