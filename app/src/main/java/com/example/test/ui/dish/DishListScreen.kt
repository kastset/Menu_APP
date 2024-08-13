package com.example.test.ui.dish

import android.content.res.Configuration
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.test.ImageLoader
import com.example.test.R
import com.example.test.model.Dish
import com.example.test.model.dishes
import com.example.test.ui.theme.TestTheme
import kotlinx.serialization.Serializable

@Serializable
data class DishListScreen(val dish: Dish)

@Composable
fun ListOfTypeDish(
    navController: NavHostController,
    type: String,
) {
    val types = dishes.filter { type == it.type }

    Column(
        modifier =
            Modifier
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
            modifier =
                Modifier
                    .fillMaxWidth(),
        )
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
                ListTypeCard(navController, dish = dish)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListTypeCard(
    navController: NavHostController,
    dish: Dish,
) {
    Card(
        onClick = {
            navController.navigate(DishDetailScreen(dish = dish))
        },
        modifier =
            Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
        elevation = CardDefaults.cardElevation(16.dp),
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
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondary,
                    textAlign = TextAlign.Center,
                )
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
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier =
                            Modifier
                                .wrapContentSize(Alignment.TopEnd)
                                .size(48.dp),
                    ) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.onSecondary,
                        )
                    }

                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier =
                            Modifier
                                .wrapContentSize(Alignment.TopEnd)
                                .size(48.dp),
                    ) {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = null,
                            modifier =
                                Modifier
                                    .size(24.dp)
                                    .wrapContentSize(Alignment.Center)
                                    .background(MaterialTheme.colorScheme.secondary),
                            tint = MaterialTheme.colorScheme.onSecondary,
                        )
                        // dish.rating
                        Text(
                            text = "5",
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier =
                                Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(align = Alignment.Center),
                        )
                    }
                }
            }

            Box(
                modifier =
                    Modifier
                        .align(Alignment.TopStart)
                        .size(45.dp),
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
                            .background(MaterialTheme.colorScheme.secondary),
                    tint = MaterialTheme.colorScheme.onSecondary,
                )

                Text(
                    // dish.time
                    text = "30",
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center),
                )
            }
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
fun DetailListView() {
    TestTheme {
        val navController = rememberNavControllerStub()
        ListOfTypeDish(navController = navController, "Супы")
    }
}

@Composable
fun rememberNavControllerStub(): NavHostController {
    // Получаем контекст текущего composable
    val context = LocalContext.current
    // Создаем NavHostController
    val navController = remember { NavHostController(context) }

    // Возвращаем созданный NavHostController
    return navController
}
