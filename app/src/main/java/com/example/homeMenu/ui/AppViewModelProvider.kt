package com.example.homeMenu.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.homeMenu.MenuApplication
import com.example.homeMenu.viewModel.BaseDishViewModel
import com.example.homeMenu.viewModel.FavoriteViewModel
import com.example.homeMenu.viewModel.HomeViewModel
import com.example.homeMenu.viewModel.MenuViewModel
import com.example.homeMenu.viewModel.TypeListViewModel

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
