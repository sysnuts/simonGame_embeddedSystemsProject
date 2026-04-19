package com.example.embeddedprojectsimon.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.embeddedprojectsimon.GameColor
import com.example.embeddedprojectsimon.R

@Composable
fun GameScreen(
    onColorClick: (GameColor) -> Unit,
    onClearClick: () -> Unit,
    onEndGameClick: () -> Unit,
    sequenceText: String,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        LandscapeLayout(
            onColorClick = onColorClick,
            onClearClick = onClearClick,
            onEndGameClick = onEndGameClick,
            sequenceText = sequenceText,
            modifier = modifier
        )
    } else {
        PortraitLayout(
            onColorClick = onColorClick,
            onClearClick = onClearClick,
            onEndGameClick = onEndGameClick,
            sequenceText = sequenceText,
            modifier = modifier
        )
    }
}

@Composable
fun LandscapeLayout(
    onColorClick: (GameColor) -> Unit,
    onClearClick: () -> Unit,
    onEndGameClick: () -> Unit,
    sequenceText: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MatrixColorsBox(
            onColorClick = onColorClick,
            modifier = Modifier.weight(1f)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            SequenceTextArea(sequenceText = sequenceText)
            ControlButtons(
                onClearClick = onClearClick,
                onEndGameClick = onEndGameClick
            )
        }
    }
}

@Composable
fun PortraitLayout(
    onColorClick: (GameColor) -> Unit,
    onClearClick: () -> Unit,
    onEndGameClick: () -> Unit,
    sequenceText: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MatrixColorsBox(
            onColorClick = onColorClick,
            modifier = Modifier.fillMaxWidth().weight(1f)
        )
        SequenceTextArea(sequenceText = sequenceText)
        ControlButtons(
            onClearClick = onClearClick,
            onEndGameClick = onEndGameClick
        )
    }
}

@Composable
fun MatrixColorsBox(
    onColorClick: (GameColor) -> Unit,
    modifier: Modifier = Modifier
) {
    val rows = 3
    val columns = 2

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (i in 0 until rows) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (j in 0 until columns) {
                    val colorIndex = i * columns + j
                    if (colorIndex < GameColor.entries.size) {
                        val gameColor = GameColor.entries[colorIndex]
                        Button(
                            onClick = { onColorClick(gameColor) },
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .border(2.dp, Color.Black, RoundedCornerShape(16.dp)),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = gameColor.boxColor
                            )
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
    val scrollState = rememberScrollState()
    LaunchedEffect(scrollState.maxValue) {
        scrollState.animateScrollTo(scrollState.maxValue)
    }

    Text(
        text = sequenceText.ifEmpty { stringResource(id = R.string.string_emptyList) },
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(100.dp)
            .verticalScroll(scrollState)
    )
}

@Composable
fun ControlButtons(
    onClearClick: () -> Unit,
    onEndGameClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = onClearClick) {
            Text(text = stringResource(id = R.string.btn_clear))
        }
        Button(onClick = onEndGameClick) {
            Text(text = stringResource(id = R.string.btn_endGame))
        }
    }
}
