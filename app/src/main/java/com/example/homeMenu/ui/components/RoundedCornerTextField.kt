package com.example.homeMenu.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.homeMenu.ui.theme.TestTheme

@Composable
fun RoundedCornerTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    placeHoleder: String,
    isEmptyField: Boolean,
    errorText: String,
    onValueChange: (String) -> Unit,
) {
    val borderColor =
        if (!isEmptyField) {
            MaterialTheme.colorScheme.surfaceContainer
        } else {
            MaterialTheme.colorScheme.errorContainer
        }
    TextField(
        modifier =
            modifier
                .border(1.dp, borderColor, RoundedCornerShape(20.dp)),
        value = text,
        isError = isEmptyField,
        shape = RoundedCornerShape(20.dp),
        supportingText = {
            if (isEmptyField) {
                Text(
                    text = errorText,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        },
        onValueChange = { onValueChange(it) },
        label = { Text(text = label, color = Color.Gray) },
        singleLine = true,
        colors =
            TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                errorContainerColor = MaterialTheme.colorScheme.errorContainer,
            ),
        placeholder = { Text(text = placeHoleder) },
    )
}

@Preview(
    name = "Light theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Dark theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun RoundedCornerShapePreview() {
    val dishName = "aboba"
    TestTheme {
        Column {
            RoundedCornerTextField(
                text = dishName,
                label = "name",
                placeHoleder = "Укажите что-то",
                isEmptyField = false,
                errorText = "",
            ) {
                dishName
            }
            Spacer(Modifier.height(10.dp))
            RoundedCornerTextField(
                text = "",
                label = "name",
                placeHoleder = "Укажите что-то",
                isEmptyField = true,
                errorText = "Dish should be called",
            ) {
                dishName
            }
        }
    }
}
