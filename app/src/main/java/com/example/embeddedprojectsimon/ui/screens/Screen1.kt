package com.example.embeddedprojectsimon.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.embeddedprojectsimon.GameColor
import com.example.embeddedprojectsimon.R

@Composable
fun GameScreen(onColorClick: (GameColor) -> Unit,
               onClearClick: () -> Unit,
               onEndGameClick: () -> Unit,
               sequenceText: String,
               ) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        if (isLandscape) {
            LandscapeLayout(onColorClick, onClearClick, onEndGameClick, sequenceText, innerPadding)
        } else {
            PortraitLayout(onColorClick, onClearClick, onEndGameClick, sequenceText, innerPadding)
        }
    }
}

@Composable
fun LandscapeLayout(onColorClick: (GameColor) -> Unit,
                    onClearClick: () -> Unit,
                    onEndGameClick: () -> Unit,
                    sequenceText: String,
                    innerPadding: PaddingValues) {
    Row(modifier = Modifier.padding(innerPadding).fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically) {
        MatrixColorsBox(onColorClick = onColorClick, modifier = Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
            SequenceTextArea(sequenceText = sequenceText)
            ControlButtons(onClearClick = onClearClick, onEndGameClick = onEndGameClick)
        }
    }
}

@Composable
fun PortraitLayout(onColorClick: (GameColor) -> Unit,
                   onClearClick: () -> Unit,
                   onEndGameClick: () -> Unit,
                   sequenceText: String,
                   innerPadding: PaddingValues) {
    Column(modifier = Modifier.padding(innerPadding).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        MatrixColorsBox(onColorClick = onColorClick, modifier = Modifier.fillMaxWidth())
        SequenceTextArea(sequenceText = sequenceText)
        ControlButtons(onClearClick = onClearClick, onEndGameClick = onEndGameClick)
    }
}
@Composable
fun MatrixColorsBox(onColorClick: (GameColor) -> Unit, modifier: Modifier = Modifier) {
    val rows = 3
    val columns = 2
    Column(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp)
                .then(modifier),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        for (i in 0 until rows) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center ) {
                for (j in 0 until columns) {
                    val colorIndex = i * columns + j
                    if (colorIndex < GameColor.entries.size) {
                        val gameColor = GameColor.entries[colorIndex]
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .border(1.dp, Color.Black),
                            onClick = { onColorClick(gameColor) },
                            colors = ButtonDefaults.buttonColors(gameColor.boxColor),
                        ) {
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SequenceTextArea(sequenceText: String) {
    Text(text = sequenceText)
}

@Composable
fun ControlButtons(onClearClick: () -> Unit, onEndGameClick: () -> Unit) {
    Row(modifier = Modifier.padding(8.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = onClearClick) {
            Text(text = stringResource(id = R.string.btn_clear))
        }
        Button(onClick = onEndGameClick) {
            Text(text = stringResource(id = R.string.btn_endGame))
        }
    }
}