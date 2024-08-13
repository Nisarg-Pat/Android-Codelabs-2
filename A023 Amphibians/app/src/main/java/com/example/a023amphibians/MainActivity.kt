package com.example.a023amphibians

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.a023amphibians.ui.AmphibianApp
import com.example.a023amphibians.ui.theme.A023AmphibiansTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A023AmphibiansTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AmphibianApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AmphibianPreview() {
    A023AmphibiansTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            AmphibianApp()
        }
    }
}