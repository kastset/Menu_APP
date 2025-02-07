package com.example.test.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.test.MenuApplication
import com.example.test.viewModel.BaseDishViewModel
import com.example.test.viewModel.FavoriteViewModel
import com.example.test.viewModel.HomeViewModel
import com.example.test.viewModel.MenuViewModel
import com.example.test.viewModel.TypeListViewModel

object AppViewModelProvider {
    val Factory =
        viewModelFactory {
            initializer {
                BaseDishViewModel(
                    menuApplication().container.dishesRepository,
                )
            }
            initializer {
                MenuViewModel(
                    menuApplication().container.dishesRepository,
                )
            }
            initializer {
                FavoriteViewModel(
                    menuApplication().container.dishesRepository,
                )
            }
            initializer {
                TypeListViewModel(
                    menuApplication().container.dishesRepository,
                )
            }
            initializer {
                HomeViewModel(
                    this.createSavedStateHandle(),
                    menuApplication().container.dishesRepository,
                )
            }
        }

    fun CreationExtras.menuApplication(): MenuApplication =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MenuApplication)
}
