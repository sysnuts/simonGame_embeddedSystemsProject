package com.example.embeddedprojectsimon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.embeddedprojectsimon.ui.components.GlobalTopBar
import com.example.embeddedprojectsimon.ui.screens.GameScreen
import com.example.embeddedprojectsimon.ui.screens.HistoryScreen
import com.example.embeddedprojectsimon.ui.theme.EmbeddedProjectSimonTheme
import com.example.embeddedprojectsimon.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<GameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val currentSequence by viewModel.selectedColors.collectAsState()
            val savedGames by viewModel.savedGames.collectAsState()
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            EmbeddedProjectSimonTheme {
                Scaffold(
                    topBar = {
                        GlobalTopBar(
                            titleResId = if (currentRoute == "history") {
                                R.string.string_screen_history
                            } else {
                                R.string.app_name
                            },
                            canNavigateBack = currentRoute == "history",
                            onNavigateBack = { navController.popBackStack() }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "game",
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        composable("game") {
                            GameScreen(
                                onColorClick = { viewModel.addColor(it) },
                                onClearClick = { viewModel.clearSelectedColors() },
                                onEndGameClick = {
                                    viewModel.addSavedGame(viewModel.selectedColors.value)
                                    navController.navigate("history")
                                },
                                sequenceText = currentSequence.joinToString(", ") { it.initial.toString() },
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        composable("history") {
                            HistoryScreen(
                                gameList = savedGames,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}
