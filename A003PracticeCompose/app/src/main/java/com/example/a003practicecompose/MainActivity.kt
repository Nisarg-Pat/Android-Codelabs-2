package com.example.a003practicecompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a003practicecompose.ui.theme.A003PracticeComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A003PracticeComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ButtonList(
                        onComposeArticleClick = { onComposeArticleClick() },
                        onTaskManagerClick = { onTaskManagerClick() },
                        onComposeQuadrantClick = { onComposeQuadrantClick() }
                    )
                }
            }
        }
    }

    private fun onComposeArticleClick() {
        val intent = Intent(this, ComposeArticleActivity::class.java)
        startActivity(intent)
    }

    private fun onTaskManagerClick() {
        val intent = Intent(this, TaskManagerActivity::class.java)
        startActivity(intent)
    }

    private fun onComposeQuadrantClick() {
        val intent = Intent(this, ComposeQuadrantActivity::class.java)
        startActivity(intent)
    }
}

@Composable
fun ButtonList(
    onComposeArticleClick: () -> Unit,
    onTaskManagerClick: () -> Unit,
    onComposeQuadrantClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Button(
            onClick = { onComposeArticleClick() },
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(8.dp)
        ) {
            Text(
                text = "Compose Article",
                fontSize = 20.sp
            )
        }
        OutlinedButton(
            onClick = { onTaskManagerClick() },
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(8.dp)
        ) {
            Text(
                text = "Task Manager",
                fontSize = 20.sp
            )
        }
        ElevatedButton(
            onClick = { onComposeQuadrantClick() },
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(8.dp)

        ) {
            Text(
                text = "Compose Quadrant",
                fontSize = 20.sp
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ButtonListPreview() {
    A003PracticeComposeTheme {
        ButtonList(
            onComposeArticleClick = {},
            onTaskManagerClick = {},
            onComposeQuadrantClick = {}
        )
    }
}