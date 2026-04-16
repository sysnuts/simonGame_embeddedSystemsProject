package com.example.embeddedprojectsimon.viewmodel

import androidx.lifecycle.ViewModel
import com.example.embeddedprojectsimon.GameColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel : ViewModel() {
    private val _selectedColors = MutableStateFlow<List<GameColor>>(emptyList())
    val selectedColors: StateFlow<List<GameColor>> = _selectedColors.asStateFlow()

    private val _savedGames = MutableStateFlow<List<List<GameColor>>>(emptyList())
    val savedGames: StateFlow<List<List<GameColor>>> = _savedGames.asStateFlow()

    fun addColor(color: GameColor) {
        _selectedColors.value += color
    }
    fun clearSelectedColors() {
        _selectedColors.value = emptyList()
    }

    fun addSavedGame(game: List<GameColor>) {
        _savedGames.value += listOf(game)
        clearSelectedColors()
        // TODO Passa alla schermata 2
    }
}
