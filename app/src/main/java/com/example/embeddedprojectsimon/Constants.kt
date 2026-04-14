package com.example.embeddedprojectsimon

import androidx.compose.ui.graphics.Color

enum class GameColor(val initial: Char, val composeColor: Color) {
    RED('R', Color.Red),
    GREEN('G', Color.Green),
    BLUE('B', Color.Blue),
    MAGENTA('M', Color.Magenta),
    YELLOW('Y', Color.Yellow),
    CYAN('C', Color.Cyan);
}