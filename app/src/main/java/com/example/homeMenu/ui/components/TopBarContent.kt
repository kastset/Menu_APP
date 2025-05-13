package com.example.homeMenu.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.homeMenu.R

@Composable
fun TopBarContent(
    isSearch: Boolean,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    screenTitle: String,
    onSearchToggle: () -> Unit,
) {
    val width by animateDpAsState(targetValue = if (isSearch) 300.dp else 0.dp)
    val titleWidth by animateDpAsState(targetValue = if (!isSearch) 300.dp else 0.dp)

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(isSearch) {
        if (isSearch) {
            focusRequester.requestFocus()
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier =
                Modifier
                    .width(titleWidth)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .wrapContentHeight(Alignment.CenterVertically),
        ) {
            if (!isSearch) {
                Text(
                    text = screenTitle,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier =
                        Modifier
                            .animateContentSize(
                                animationSpec =
                                    spring(
                                        stiffness = Spring.StiffnessHigh,
                                    ),
                            ),
                )
            }
        }

        Box(
            modifier =
                Modifier
                    .width(width)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surfaceContainer),
        ) {
            if (isSearch) {
                TextField(
                    value = searchText,
                    onValueChange = onSearchTextChange,
                    shape = RoundedCornerShape(30.dp),
                    colors =
                        TextFieldDefaults.colors(
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                        ),
                    singleLine = true,
                    modifier =
                        Modifier.fillMaxWidth()
                            .focusRequester(focusRequester),
                    placeholder = { Text(stringResource(R.string.enter_text)) },
                )
            }
        }
    }
}
