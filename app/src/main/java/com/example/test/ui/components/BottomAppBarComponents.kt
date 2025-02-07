package com.example.test.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.test.ui.navigation.NavRoute
import com.example.test.ui.theme.TestTheme
import kotlinx.coroutines.launch

enum class BottomNavigationItem(
    val title: String,
    val route: NavRoute,
    val selectedItem: ImageVector,
    val unselectedItem: ImageVector,
) {
    HOME(
        title = "Категории",
        route = NavRoute.MainScreen,
        selectedItem = Icons.Filled.Home,
        unselectedItem = Icons.Outlined.Home,
    ),
    MENU(
        title = "Меню",
        route = NavRoute.MenuScreen,
        selectedItem = Icons.AutoMirrored.Filled.List,
        unselectedItem = Icons.AutoMirrored.Outlined.List,
    ),
    FAVORITE(
        title = "Любимые",
        route = NavRoute.FavoriteDishListScreen,
        selectedItem = Icons.Filled.Favorite,
        unselectedItem = Icons.Outlined.FavoriteBorder,
    ),
}

@Composable
fun BottomAppBar(
    navController: NavHostController,
    gridState: LazyListState,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        navBackStackEntry?.destination?.route
            ?: BottomNavigationItem.MENU.route::class.qualifiedName.orEmpty()

    val currentRouteTrimmed by remember(currentRoute) {
        derivedStateOf { currentRoute.substringBefore("?") }
    }

    val coroutineScope = rememberCoroutineScope()

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        modifier = modifier,
    ) {
        BottomNavigationItem.entries
            .forEachIndexed { index, item ->
                val isSelected by remember(currentRoute) {
                    derivedStateOf { currentRouteTrimmed == item.route::class.qualifiedName }
                }

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) {
                            if (item.route == NavRoute.MainScreen) {
                                navController.navigate(NavRoute.MainScreen) {
                                    popUpTo(NavRoute.MainScreen) {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            } else {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        } else {
                            coroutineScope.launch {
                                gridState.animateScrollToItem(0)
                            }
                        }
                    },
                    label = {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = if (isSelected) item.selectedItem else item.unselectedItem,
                            contentDescription = item.title,
                        )
                    },
                    colors =
                        NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        ),
                )
            }
    }
}

@Preview(
    name = "Light theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Dark theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewBar() {
    TestTheme {
        val gridState = rememberLazyListState()
        BottomAppBar(
            gridState = gridState,
            navController = rememberNavControllerStub(),
        )
    }
}

@Composable
fun rememberNavControllerStub(): NavHostController {
    val context = LocalContext.current
    val navController = remember { NavHostController(context) }

    return navController
}
