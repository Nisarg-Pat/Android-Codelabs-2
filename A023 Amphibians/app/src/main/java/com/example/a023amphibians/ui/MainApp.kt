package com.example.a023amphibians.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a023amphibians.R
import com.example.a023amphibians.ui.screens.AmphibianViewModel
import com.example.a023amphibians.ui.screens.HomeScreen
import com.example.a023amphibians.ui.theme.A023AmphibiansTheme

@Composable
fun AmphibianApp(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { AppBar() },
        modifier = modifier
    ) {
        val amphibianViewModel: AmphibianViewModel = viewModel(factory = AmphibianViewModel.Factory)
        HomeScreen(
            uiState = amphibianViewModel.uiState,
            modifier = Modifier.fillMaxSize().padding(it)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun AmphibianAppPreview() {
    AmphibianApp()
}