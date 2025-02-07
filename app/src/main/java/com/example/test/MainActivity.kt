@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.test.ui.components.BottomAppBar
import com.example.test.ui.navigation.AppNavHost
import com.example.test.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTheme {
                val navController = rememberNavController()

                val gridState = rememberLazyListState()

                Scaffold(
                    bottomBar = {
                        SharedTransitionLayout {
                            AnimatedVisibility(visible = true) {
                                BottomAppBar(
                                    gridState = gridState,
                                    navController = navController,
                                    modifier =
                                        Modifier.renderInSharedTransitionScopeOverlay(
                                            zIndexInOverlay = 1f,
                                        ),
                                )
                            }
                        }
                    },
                ) { paddingValues ->
                    AppNavHost(
                        gridState = gridState,
                        navController,
                        paddingValues,
                    )
                }
            }
        }
    }
}
