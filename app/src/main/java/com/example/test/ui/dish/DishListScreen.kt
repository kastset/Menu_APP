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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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

@Composable
fun DishListScreen(
    type: String,
    viewModel: DishViewModel,
    onDishClick: (Dish) -> Unit,
    onPressBack: () -> Unit,
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
                tint = Color.Gray,
            )
        }

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
                ListTypeCard(
                    onDishClick = onDishClick,
                    dish = dish,
                    viewModel,
                )
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

    val iconTint by animateColorAsState(
        targetValue = if (isFavorite) Color.Red else Color.Gray,
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

            DishPrepIcon(
                Modifier
                    .wrapContentSize(Alignment.TopStart)
                    .size(45.dp)
                    .padding(top = 4.dp, end = 4.dp),
            )

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
                                .size(48.dp),
                        onFavoriteClick = { viewModel.toggleFavorite(dish.id) },
                        updatedDish = updatedDish,
                        iconTint = iconTint,
                    )

                    DishRatingIcon(
                        modifier =
                            Modifier
                                .wrapContentSize(Alignment.TopEnd)
                                .size(48.dp),
                        backgroundColorIcon = MaterialTheme.colorScheme.secondary,
                        updatedDish = updatedDish,
                    ) { rating ->
                        viewModel.toggleRating(dish.id, rating)
                    }
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
            onDishClick = {},
            type = "Супы",
            viewModel = viewModel,
            onPressBack = {},
        )
    }
}
