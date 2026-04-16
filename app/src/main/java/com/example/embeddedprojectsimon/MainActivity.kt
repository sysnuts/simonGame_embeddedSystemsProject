package com.example.embeddedprojectsimon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.embeddedprojectsimon.ui.theme.EmbeddedProjectSimonTheme
import com.example.embeddedprojectsimon.ui.screens.GameScreen
import com.example.embeddedprojectsimon.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    val viewModel by viewModels<GameViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val currentSequence by viewModel.selectedColors.collectAsState()
            EmbeddedProjectSimonTheme {
                    GameScreen(
                        onColorClick = { viewModel.addColor(it) },
                        onClearClick = { viewModel.clearSelectedColors() },
                        onEndGameClick = { viewModel.addSavedGame(viewModel.selectedColors.value) },
                        sequenceText = currentSequence.joinToString(", ") { it.initial.toString() },
                    )
            }
        }
    }
}



