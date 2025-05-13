@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.homeMenu.ui.screens

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homeMenu.R
import com.example.homeMenu.ui.AppViewModelProvider
import com.example.homeMenu.ui.components.ImageLoader
import com.example.homeMenu.ui.components.RoundedCornerDropDownMenu
import com.example.homeMenu.ui.components.RoundedCornerTextField
import com.example.homeMenu.ui.components.TopBarContent
import com.example.homeMenu.ui.theme.TestTheme
import com.example.homeMenu.viewModel.DishCreationViewModel

@Composable
fun DishCreationScreen(
    paddingValues: PaddingValues,
    onPressBack: () -> Unit,
    onDishSave: () -> Unit,
    viewModel: DishCreationViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val dishName by viewModel.nameState.collectAsState()
    val dishRecipe by viewModel.recipeState.collectAsState()
    val dishImage by viewModel.imageState.collectAsState()

    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarContent(
                        isSearch = false,
                        searchText = "",
                        onSearchTextChange = { },
                        screenTitle = "Создать Блюдо",
                        {
                        },
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onPressBack() },
                        modifier = Modifier.size(48.dp),
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.go_back),
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                        titleContentColor = MaterialTheme.colorScheme.onSurface,
                        navigationIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    ),
            )
        },
    ) {
        Column(
            modifier =
                Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxSize()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = it.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding(),
                    ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(10.dp))
            RoundedCornerTextField(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                text = dishName,
                label = "Название блюда",
                placeHoleder = "Как называется блюдо",
                isEmptyField = viewModel.emptyNameField.value,
                errorText = "Название блюда не может быть пустым",
            ) {
                viewModel.nameTextChange(it)
            }
            Spacer(Modifier.height(15.dp))
            Row(
                modifier =
                    Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .height(70.dp)
                        .fillMaxWidth(),
            ) {
                RoundedCornerTextField(
                    modifier = Modifier.weight(0.6f),
                    text = dishRecipe,
                    label = "Cссылка на рецепт",
                    placeHoleder = "Укажите рецепт блюда",
                    isEmptyField = viewModel.emptyRecipeField.value,
                    errorText = "Ссылка на рецепт не может быть пустой",
                ) {
                    viewModel.recipeTextChange(it)
                }
                Spacer(Modifier.width(10.dp))
                RoundedCornerDropDownMenu(
                    modifier = Modifier.weight(0.4f),
                    isEmptyField = viewModel.emptyTypeField.value,
                ) {
                        selectedOption ->
                    viewModel.typeTextChange(selectedOption)
                }
            }
            Spacer(Modifier.width(15.dp))
            RoundedCornerTextField(
                modifier = Modifier.fillMaxWidth(),
                text = dishImage,
                label = "Ссылка для фото",
                placeHoleder = "Вставте ссылку на фото",
                isEmptyField = viewModel.emptyImageField.value,
                errorText = "Ссылка на фото не может быть пустой",
            ) {
                viewModel.imageTextChange(it)
            }
            ImageLoader(
                modifier =
                    Modifier
                        .size(200.dp)
                        .padding(15.dp),
                imageUrl = dishImage,
            )
            Spacer(Modifier.weight(2f))
            Button(
                modifier = Modifier.size(180.dp, 50.dp),
                onClick = {
                    if (viewModel.isDishSaved()) {
                        Toast.makeText(context, "Проверьте заполнены ли поля", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.saveDish()
                        onDishSave()
                    }
                },
            ) {
                Text(text = "Сохранить")
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
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
fun DishCreationPreview() {
    TestTheme {
        val paddingValues = PaddingValues(16.dp)
        DishCreationScreen(
            paddingValues = paddingValues,
            onPressBack = {},
            onDishSave = {},
        )
    }
}
