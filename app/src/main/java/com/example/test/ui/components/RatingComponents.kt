package com.example.test.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.model.Dish

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
                contentDescription = "Rate Dish",
                tint = MaterialTheme.colorScheme.secondary,
            )
        } else {
            Icon(
                Icons.Filled.Star,
                contentDescription = "Rate Dish",
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
        title = { Text("Rate this dish") },
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
            TextButton(onClick = onDismiss) {
                Text("Done")
            }
        },
    )
}

// Пример превью для быстрой проверки UI
@Preview
@Composable
fun RatingDialogPreview() {
    RatingDialog(currentRating = 3, onRatingSelected = {}, onDismiss = {})
}
