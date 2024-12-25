@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.test.ui.home

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.test.model.Dish
import com.example.test.ui.components.HeaderText
import com.example.test.ui.components.TopBarContent
import com.example.test.ui.dish.DishViewModel
import com.example.test.ui.dish.ListTypeCard
import com.example.test.ui.theme.TestTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedVisibilityScope,
    paddingValues: PaddingValues,
    viewModel: DishViewModel,
    onTypeClick: (String) -> Unit,
    onDishClick: (Dish) -> Unit,
) {
    val allDish by viewModel.dishesFullList.collectAsState()
    val searchText by viewModel.searchText.collectAsState()

    var isSearch by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarContent(
                        isSearch = isSearch,
                        searchText = searchText,
                        onSearchTextChange = viewModel::onSearchTextChange,
                        screenTitle = "Категории",
                    )
                },
                actions = {
                    IconButton(onClick = { isSearch = !isSearch }) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Поиск",
                        )
                    }
                },
                navigationIcon = {
                    if (!isSearch) {
                        IconButton(
                            onClick = {
                                isSearch = !isSearch
                                viewModel.clearSearchText()
                            },
                            modifier =
                                Modifier
                                    .size(48.dp),
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Go Back",
                                modifier = Modifier.size(24.dp),
                            )
                        }
                    }
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                        titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    ),
            )
        },
    ) {
        if (isSearch) {
            Column(
                modifier =
                    Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(
                            top = it.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding(),
                        )
                        .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                ListOfTypeDish(onTypeClick = onTypeClick)
            }
            Spacer(modifier = Modifier.height(32.dp))
        } else {
            LazyColumn(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(
                            top = it.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding(),
                        )
                        .pointerInput(isSearch) {
                            detectTapGestures(onTap = {
                                if (!isSearch) {
                                    isSearch = true
                                    viewModel.clearSearchText()
                                }
                            })
                        },
                contentPadding = PaddingValues(horizontal = 16.dp),
//                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(allDish) { dish ->
                    ListTypeCard(
                        sharedTransitionScope = sharedTransitionScope,
                        animatedContentScope = animatedContentScope,
                        onDishClick = onDishClick,
                        dish = dish,
                        viewModel,
                    )
                }
            }
        }
    }
}

@Composable
fun ListOfTypeDish(onTypeClick: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderText(
            text = "List of a dish",
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleMedium,
            textColor = MaterialTheme.colorScheme.tertiary,
        )
        Spacer(modifier = Modifier.height(16.dp))
        DishTypes(onTypeClick = onTypeClick)
    }
}

@Composable
fun DishTypes(onTypeClick: (String) -> Unit) {
    val types =
        listOf(
            listOf("Завтрак", "Супы"),
            listOf("Гарнир", "Салаты"),
            listOf("Мясо/Рыба", "Десерты"),
            listOf("Напитки", "Закуски"),
        )
    for (row in types) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            for (type in row) {
                Spacer(modifier = Modifier.width(4.dp))
                FilterButton(onTypeClick, type)
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun FilterButton(
    onTypeClick: (String) -> Unit,
    type: String,
) {
    Button(
        onClick = {
            onTypeClick(type)
        },
        modifier =
            Modifier
                .height(50.dp)
                .width(155.dp),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
        elevation = ButtonDefaults.buttonElevation(8.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Text(
            text = type,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview(
    name = "Light theme",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Dark theme",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun MainScreenPreview() {
    val viewModel = DishViewModel()
    TestTheme {
        val paddingValues = PaddingValues(16.dp)
//        MainScreen(paddingValues = paddingValues, viewModel = viewModel, {}, { })
    }
}
