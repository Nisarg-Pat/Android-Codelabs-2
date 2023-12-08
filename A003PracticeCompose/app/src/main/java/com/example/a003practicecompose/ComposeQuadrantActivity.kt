package com.example.a003practicecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a003practicecompose.ui.theme.A003PracticeComposeTheme

class ComposeQuadrantActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A003PracticeComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeQuadrants()
                }
            }
        }
    }
}


@Composable
fun ComposeQuadrants(modifier: Modifier = Modifier) {
    Column {
        Row(
            modifier = modifier.weight(1F)
        ) {
            ComposeQuadrant(
                backgroundColor = Color(0xFFEADDFF),
                title = stringResource(id = R.string.text_composable),
                description = stringResource(id = R.string.text_composable_text),
                modifier = Modifier.weight(1F)
            )
            ComposeQuadrant(
                backgroundColor = Color(0xFFD0BCFF),
                title = stringResource(id = R.string.image_composable),
                description = stringResource(id = R.string.image_composable_text),
                modifier = Modifier.weight(1F)
            )
        }
        Row(
            modifier = modifier.weight(1F)
        ) {
            ComposeQuadrant(
                backgroundColor = Color(0xFFB69DF8),
                title = stringResource(id = R.string.row_composable),
                description = stringResource(id = R.string.row_composable_text),
                modifier = Modifier.weight(1F)
            )
            ComposeQuadrant(
                backgroundColor = Color(0xFFF6EDFF),
                title = stringResource(id = R.string.column_composable),
                description = stringResource(id = R.string.column_composable_text),
                modifier = Modifier.weight(1F)
            )
        }
    }

}

@Composable
fun ComposeQuadrant(
    backgroundColor: Color,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(backgroundColor)
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )
        Text(
            text = description,
            color = Color.Black,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeQuadrantPreview() {
    A003PracticeComposeTheme {
        ComposeQuadrants()
    }
}