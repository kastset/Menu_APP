package com.example.test.ui.dish

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import kotlinx.serialization.Serializable

@Serializable
data class DishListScreen(val dish: Dish)


@Composable
fun ListOfTypeDish(navController: NavHostController, type: String) {
  val types = dishes.filter { type == it.type }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
            .statusBarsPadding(),
    ) {
        Text(
            text = "Список блюд для типа: $type",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(types) { dish ->
                ListTypeCard(navController, dish = dish)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListTypeCard(navController: NavHostController, dish: Dish) {
    val image: Painter = painterResource(R.drawable.ic_launcher_background)
    Card(
        onClick = {
            navController.navigate(DishDetailScreen(dish = dish))
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
        elevation = CardDefaults.cardElevation(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = image,
                    contentDescription = dish.name,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(bottom = 8.dp)
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally),
                    text = dish.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(48.dp, 100.dp)
                    .padding(top = 4.dp, end = 4.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround,
                ) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .wrapContentSize(Alignment.TopEnd)
                            .size(48.dp)
                    ) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }

                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .wrapContentSize(Alignment.TopEnd)
                            .size(48.dp)
                    ) {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .wrapContentSize(Alignment.Center)
                                .background(MaterialTheme.colorScheme.secondary),
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                        Text(
                            text = "5"/* dish.rating*/,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(align = Alignment.Center)
                        )
                    }
                }
            }


            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .size(45.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.free_icon_clock_7164560/*Эта обложка была разработана с использованием ресурсов сайта Flaticon.com.*/),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center)
                        .background(MaterialTheme.colorScheme.secondary),
                    tint = MaterialTheme.colorScheme.onSecondary
                )
                Text(
                    text = "30"/* dish.time*/,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }
    }
}
