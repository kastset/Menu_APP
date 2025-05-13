package com.example.homeMenu.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun RoundedCornerDropDownMenu(
    modifier: Modifier = Modifier,
    initialSelectedOption: String? = null,
    isEmptyField: Boolean,
    onOptionSelected: (String) -> Unit,
) {
    val expand = remember { mutableStateOf(false) }
    val selectOption = remember { mutableStateOf(initialSelectedOption ?: "Категории") }
    val categoryList =
        listOf(
            "Завтрак",
            "Супы",
            "Гарнир",
            "Салаты",
            "Мясо\\Рыба",
            "Десерты",
            "Напитки",
            "Закуски",
        )

    val borderColor = if (isEmptyField) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.primary
    val rightText = if (isEmptyField) "Выбирите категорию" else selectOption.value
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(20.dp),
                )
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable {
                    expand.value = true
                }
                .padding(15.dp),
    ) {
        Text(text = rightText)
        DropdownMenu(
            expanded = expand.value,
            onDismissRequest = {
                expand.value = false
            },
        ) {
            categoryList.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        selectOption.value = option
                        expand.value = false
                        onOptionSelected(option)
                    },
                )
            }
        }
    }
}
