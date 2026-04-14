package com.example.embeddedprojectsimon.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.embeddedprojectsimon.GameColor
import com.example.embeddedprojectsimon.R

@Composable
fun GameScreen(onColorClick: (GameColor) -> Unit, onCancelClick: () -> Unit, onEndGameClick: () -> Unit, sequenceText: String) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            MatrixColorsBox(onColorClick = onColorClick)
            SequenceTextArea(sequenceText = sequenceText)
            ControlButtons(onCancelClick = onCancelClick, onEndGameClick = onEndGameClick)
        }
    }
}

@Composable
fun MatrixColorsBox(onColorClick: (GameColor) -> Unit) {
    val rows = 3
    val columns = 2
    Column(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = androidx.compose.ui.Alignment.Start
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
                                .border(1.dp, androidx.compose.ui.graphics.Color.Black),
                            onClick = { onColorClick(gameColor) },
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(gameColor.composeColor)
                        ) {
                            Text(text = gameColor.name)
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
fun ControlButtons(onCancelClick: () -> Unit, onEndGameClick: () -> Unit) {
    Row(modifier = Modifier.padding(8.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = onCancelClick) {
            Text(text = stringResource(id = R.string.btn_clear))
        }
        Button(onClick = onEndGameClick) {
            Text(text = stringResource(id = R.string.btn_endGame))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    _root_ide_package_.com.example.embeddedprojectsimon.ui.theme.EmbeddedProjectSimonTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                MatrixColorsBox(onColorClick = { })
                SequenceTextArea(sequenceText = "Sequence: R G B")
                ControlButtons(
                    onCancelClick = { },
                    onEndGameClick = { })
            }
        }
    }
}