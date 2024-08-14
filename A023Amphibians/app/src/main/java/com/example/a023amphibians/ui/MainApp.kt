package com.example.a023amphibians.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a023amphibians.R
import com.example.a023amphibians.ui.screens.AmphibianViewModel
import com.example.a023amphibians.ui.screens.HomeScreen
import com.example.a023amphibians.ui.theme.A023AmphibiansTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibianApp(
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = { AppBar(scrollBehavior = scrollBehavior) },
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
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
fun AppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
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