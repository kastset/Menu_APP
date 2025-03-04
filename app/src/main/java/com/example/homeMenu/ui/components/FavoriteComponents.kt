package com.example.homeMenu.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.homeMenu.R
import com.example.homeMenu.model.Dish

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit,
    updatedDish: Dish?,
    iconTint: Color,
) {
    var isFavorite = updatedDish?.isFavorite ?: false
    IconButton(
        onClick = onFavoriteClick,
        modifier = modifier,
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = stringResource(R.string.favorite),
            modifier = Modifier.size(24.dp),
            tint = iconTint,
        )
    }
}
