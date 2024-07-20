package com.example.test.ui.dish

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.test.model.Dish
import com.example.test.model.dishes
import kotlinx.serialization.Serializable

@Serializable
data class DishDetailScreen(val dish: Dish)

@Composable
fun DishDetail(dish: Dish, id: Int) {
    dishes.filter { it.id == id }
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = dish.name)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = dish.type)

    }
}