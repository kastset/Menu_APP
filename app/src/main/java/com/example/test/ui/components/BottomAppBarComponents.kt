package com.example.test.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.test.ui.navigation.NavRoute

data class BottomNavigationItem(
    val title: String,
    val route: NavRoute,
    val selectedItem: ImageVector,
    val unselectedItem: ImageVector,
)

val listOfBottomItem =
    listOf(
        BottomNavigationItem(
            title = "Home",
            route = NavRoute.MainScreen,
            selectedItem = Icons.Filled.Home,
            unselectedItem = Icons.Outlined.Home,
        ),
        BottomNavigationItem(
            title = "Menu",
            route = NavRoute.DishListScreen("Десерты"),
            selectedItem = Icons.Filled.List,
            unselectedItem = Icons.Outlined.List,
        ),
        BottomNavigationItem(
            title = "Favorite",
            route = NavRoute.DishListScreen("Супы"),
            selectedItem = Icons.Filled.Favorite,
            unselectedItem = Icons.Outlined.FavoriteBorder,
        ),
    )

@Composable
fun BottomAppBar() {
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    NavigationBar {
        listOfBottomItem.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                },
                label = {
                    Text(text = item.title)
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) item.selectedItem else item.unselectedItem,
                        contentDescription = item.title,
                    )
                },
            )
        }
    }
}
