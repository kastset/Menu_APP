package com.example.homeMenu.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

@Composable
fun HeaderText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle,
    textColor: Color,
) {
    Text(
        text = text,
        color = textColor,
        style = style,
        modifier = modifier,
        textAlign = TextAlign.Center,
    )
}
