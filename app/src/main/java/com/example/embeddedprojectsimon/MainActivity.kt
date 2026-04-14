package com.example.embeddedprojectsimon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.embeddedprojectsimon.ui.theme.EmbeddedProjectSimonTheme
import com.example.embeddedprojectsimon.ui.screens.GameScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmbeddedProjectSimonTheme {
                    GameScreen(
                        onColorClick = { /* TODO: Gestisci il click sui colori */ },
                        onCancelClick = { /* TODO: Gestisci il click su Cancel */ },
                        onEndGameClick = { /* TODO: Gestisci il click su End Game */ },
                        sequenceText = "Sequence: R G B" // Placeholder
                    )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EmbeddedProjectSimonTheme {
            GameScreen(
                onColorClick = { /* TODO: Gestisci il click sui colori */ },
                onCancelClick = { /* TODO: Gestisci il click su Cancel */ },
                onEndGameClick = { /* TODO: Gestisci il click su End Game */ },
                sequenceText = "Sequence: R G B"
            )
    }
}



