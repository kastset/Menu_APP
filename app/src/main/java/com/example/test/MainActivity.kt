package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.test.ui.components.BottomAppBar
import com.example.test.ui.dish.DishViewModel
import com.example.test.ui.navigation.AppNavHost
import com.example.test.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTheme {
                val navController = rememberNavController()
                val viewModel: DishViewModel = ViewModelProvider(this)[DishViewModel::class.java]

                val gridState = rememberLazyListState()

                Scaffold(
                    bottomBar = {
                        BottomAppBar(
                            gridState = gridState,
                            navController = navController,
                            viewModel = viewModel,
                        )
                    },
                ) { paddingValues ->
                    AppNavHost(
                        gridState = gridState,
                        navController,
                        viewModel,
                        paddingValues,
                    )
                }
            }
        }
    }
}
