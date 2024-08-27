package com.example.test.ui.dish

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.R
import com.example.test.model.Dish
import com.example.test.ui.components.DishRatingIcon
import com.example.test.ui.components.FavoriteButton
import com.example.test.ui.components.HeaderText
import com.example.test.ui.components.ImageLoader
import com.example.test.ui.components.RatingDialog
import com.example.test.ui.theme.TestTheme
import kotlinx.serialization.Serializable

@Serializable
data class DishDetailScreen(val dish: Dish)

@Composable
fun FullDishDetail(
    dish: Dish,
    viewModel: DishViewModel,
) {
    val updatedDish = viewModel.dishesFlow.collectAsState().value.find { it.id == dish.id }
    val isFavorite by rememberUpdatedState(updatedDish?.isFavorite ?: dish.isFavorite)

    val iconTint by animateColorAsState(
        targetValue = if (isFavorite) Color.Red else Color.Gray,
    )

    Box(
        modifier =
            Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .statusBarsPadding()
                .systemBarsPadding(),
    ) {
        BasicDishInfo(dish = dish, updatedDish) { rating ->
            viewModel.toggleRating(dish.id, rating)
        }

        FavoriteButton(
            modifier =
                Modifier
                    .align(Alignment.TopEnd)
                    .size(48.dp),
            onFavoriteClick = { viewModel.toggleFavorite(dish.id) },
            updatedDish = updatedDish,
            iconTint = iconTint,
        )

        OpenLinkButton(
            modifier =
                Modifier
                    .align(Alignment.BottomCenter)
                    .size(180.dp, 50.dp),
            dish = dish,
        )
    }
}

@Composable
fun OpenLinkButton(
    modifier: Modifier = Modifier,
    dish: Dish,
) {
    val context = LocalContext.current

    val url = dish.recipeLink

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

    Button(
        onClick = {
            context.startActivity(intent)
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
    ) {
        Text(
            "Рецепт",
            fontSize = 17.sp,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
fun BasicDishInfo(
    dish: Dish,
    updateDish: Dish?,
    onRatingChanged: (Int) -> Unit,
) {
    Column(
        modifier =
            Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        HeaderText(
            text = dish.name,
            size = 20,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.height(24.dp))
        SecondDishInfo(
            updateDish,
            onRatingChanged,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier =
                Modifier
                    .size(200.dp, 200.dp)
                    .background(MaterialTheme.colorScheme.background),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        ) {
            ImageLoader(
                dish.imageUrl,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Composable
fun SecondDishInfo(
    updateDish: Dish?,
    onRatingChanged: (Int) -> Unit,
) {
    Row(
        modifier =
            Modifier
                .size(400.dp, 70.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        CookedPrepTime()
        Feedback(updateDish, onRatingChanged)
    }
}

@Composable
fun Feedback(
    updatedDish: Dish?,
    onRatingChanged: (Int) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedRating by remember { mutableStateOf(updatedDish?.rating ?: 0) }

    Card(
        modifier =
            Modifier
                .size(185.dp, 70.dp)
                .background(MaterialTheme.colorScheme.background)
                .wrapContentWidth(align = Alignment.End),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
    ) {
        HeaderText(
            text = "Ваша оценка",
            size = 17,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
        if (selectedRating == 0) {
            Button(
                onClick = { showDialog = true },
                modifier = Modifier.padding(2.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary),
                elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
            ) {
                Text(
                    text = "Оценить",
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            if (showDialog) {
                RatingDialog(
                    currentRating = selectedRating,
                    onRatingSelected = { rating ->
                        selectedRating = rating
                        onRatingChanged(rating)
                        showDialog = false
                    },
                    onDismiss = { showDialog = false },
                )
            }
        } else {
            DishRatingIcon(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(48.dp),
                backgroundColorIcon = MaterialTheme.colorScheme.background,
                updatedDish = updatedDish,
                onRatingChanged = onRatingChanged,
            )
        }
    }
}

@Composable
fun CookedPrepTime() {
    Box(
        modifier =
            Modifier
                .size(185.dp, 70.dp)
                .wrapContentWidth(Alignment.Start),
    ) {
        HeaderText(
            text = "Время приготовления",
            size = 17,
            modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally),
        )
        Row(
            modifier =
                Modifier
                    .padding(top = 25.dp)
                    .size(185.dp, 50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
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
                        .background(MaterialTheme.colorScheme.background),
                tint = MaterialTheme.colorScheme.primary,
            )

            Text(
                // dish.time
                text = "30 мин.",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier =
                    Modifier
                        .wrapContentSize(align = Alignment.CenterStart)
                        .padding(start = 8.dp),
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
fun DetailView() {
    val viewModel = DishViewModel()
    val dish = Dish(0, "Sendwith with Egg", "Завтрак", "", "")
    TestTheme {
        FullDishDetail(dish = dish, viewModel)
    }
}
