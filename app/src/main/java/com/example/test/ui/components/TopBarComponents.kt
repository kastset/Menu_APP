package com.example.test.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TopBarContent(
    isSearch: Boolean,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    screenTitle: String,
) {
    AnimatedContent(targetState = isSearch) { targetIsSearch ->
        if (targetIsSearch) {
            Text(
                text = screenTitle,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.animateContentSize(),
            )
        } else {
            TextField(
                value = searchText,
                onValueChange = onSearchTextChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Введите название блюда...") },
            )
        }
    }
}
