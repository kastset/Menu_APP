package com.example.test.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun HeaderText(
    text: String,
    modifier: Modifier = Modifier,
    size: Int,
) {
    Text(
        text = text,
        fontSize = size.sp,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier,
        textAlign = TextAlign.Center,
    )
}
