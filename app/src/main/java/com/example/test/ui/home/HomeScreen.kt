package com.example.test.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.test.R
import com.example.test.model.Dish
import com.example.test.model.dishes
import com.example.test.ui.dish.DishDetailScreen
import com.example.test.ui.dish.DishListScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen {
    @Composable
    fun MainScreen(navController: NavHostController) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
                .statusBarsPadding(),
            verticalArrangement = Arrangement.Center
        ) {
            ListOfTypeDish(navController)
            Spacer(modifier = Modifier.height(32.dp))
            FavouriteDishGrid(navController)
        }
    }

    @Composable
    fun ListOfTypeDish(navController: NavHostController) {
        Column {
            Text(
                text = "Список блюд",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)

            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    FilterButton(navController, type = "Завтрак")
                    Spacer(modifier = Modifier.height(8.dp))
                    FilterButton(navController, type = "Гарнир")
                    Spacer(modifier = Modifier.height(8.dp))
                    FilterButton(navController, type = "Десерты")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    FilterButton(navController, type = "Супы")
                    Spacer(modifier = Modifier.height(8.dp))
                    FilterButton(navController, type = "Салаты")
                    Spacer(modifier = Modifier.height(8.dp))
                    FilterButton(navController, type = "Мясо/Рыба")
                }
            }

        }
    }

    @Composable
    fun FilterButton(navController: NavHostController, type: String) {
        Button(
            onClick = {
                navController.navigate(DishListScreen(Dish(0, "", type)))
            },
            modifier = Modifier
                .height(50.dp)
                .width(155.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),

            ) {
            Text(text = type, color = MaterialTheme.colorScheme.onPrimary)
        }
    }

}


@Composable
fun FavouriteDishGrid(navController: NavHostController) {
    Column {
        Text(
            text = "Любимые блюда",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) {
            items(dishes) { dish ->
                DishCard(navController, dish)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishCard(navController: NavHostController, dish: Dish) {
    Card(
        onClick = {
            navController.navigate(DishDetailScreen(dish = dish))
        },
        modifier = Modifier
            .size(150.dp, 190.dp)
            .background(MaterialTheme.colorScheme.background),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val image: Painter = painterResource(R.drawable.ic_launcher_background)
            Image(
                painter = image, contentDescription = dish.name,
                modifier = Modifier
                    .size(95.dp)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = dish.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

