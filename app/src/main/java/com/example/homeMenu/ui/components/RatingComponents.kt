package com.example.homeMenu.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homeMenu.R
import com.example.homeMenu.model.Dish
import com.example.homeMenu.ui.theme.TestTheme

@Composable
fun DishRatingIcon(
    modifier: Modifier = Modifier,
    backgroundColorIcon: Color,
    updatedDish: Dish?,
    onRatingChanged: (Int) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedRating by remember { mutableStateOf(updatedDish?.rating ?: 0) }

    IconButton(
        onClick = { showDialog = true },
        modifier = modifier,
    ) {
        if (selectedRating == 0) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = stringResource(R.string.rate_dish),
                tint = MaterialTheme.colorScheme.secondary,
            )
        } else {
            Icon(
                Icons.Filled.Star,
                contentDescription = stringResource(R.string.rate_dish),
                modifier =
                    Modifier
                        .size(24.dp)
                        .wrapContentSize(Alignment.Center)
                        .background(backgroundColorIcon),
                tint = Color.Yellow,
            )
            Text(
                text = updatedDish!!.rating.toString(),
                fontSize = 10.sp,
                color = Color.Black,
                modifier =
                    Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center),
            )
        }
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
}

@Composable
fun RatingDialog(
    currentRating: Int,
    onRatingSelected: (Int) -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = stringResource(R.string.rate_this_dish))
            }
        },
        text = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                for (i in 1..5) {
                    IconButton(onClick = { onRatingSelected(i) }) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "$i Stars",
                            tint = if (i <= currentRating) Color.Yellow else Color.Gray,
                        )
                    }
                }
            }
        },
        confirmButton = {
            fun String.toColor() = Color(android.graphics.Color.parseColor(this))
            val lightGreen = "#b7d9b1".toColor()
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                TextButton(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.large)
                            .background(lightGreen),
                    onClick = onDismiss,
                ) {
                    Text(
                        style =
                            TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                            ),
                        text = stringResource(R.string.done),
                    )
                }
            }
        },
        dismissButton = {
            val dishIsRated: Boolean = currentRating > 0
            if (dishIsRated) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    TextButton(
                        onClick = {
                            onRatingSelected(0)
                            onDismiss
                        },
                    ) {
                        Text(
                            text = stringResource(R.string.delete_rating),
                            color = Color.Red,
                        )
                    }
                }
            }
        },
    )
}

@Preview(
    name = "Light theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Dark theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun RatingDialogPreview() {
    TestTheme {
        RatingDialog(
            currentRating = 3,
            onRatingSelected = {},
            onDismiss = {},
        )
    }
}
