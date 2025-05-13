package com.example.homeMenu.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.homeMenu.ui.theme.TestTheme

@Composable
fun AddOrEditButton(
    onScreenClick: () -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    descriptionText: String,
) {
    val icon = if (descriptionText == "Create a new Dish") Icons.Filled.Add else Icons.Filled.Edit
    FloatingActionButton(
        onClick = { onScreenClick() },
        shape = MaterialTheme.shapes.medium,
        modifier =
            modifier
                .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = paddingValues.calculateBottomPadding()),
    ) {
        Icon(
            icon,
            contentDescription = descriptionText,
        )
    }
}

@Preview(
    name = "light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun AddButtonPreview() {
    TestTheme {
        val paddingValues = PaddingValues(16.dp)
        AddOrEditButton(
            onScreenClick = {},
            descriptionText = "Create new dish",
            paddingValues = paddingValues,
        )
    }
}
