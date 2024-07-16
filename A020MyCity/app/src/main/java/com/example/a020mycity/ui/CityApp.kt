package com.example.a020mycity.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a020mycity.ui.utils.DisplayType

@Composable
fun CityApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {

    val viewModel: CityViewModel = viewModel()
    val cityUiState = viewModel.uiState.collectAsState().value

    val displayType = when (windowSize) {
        WindowWidthSizeClass.Compact -> DisplayType.LIST_ONLY
        else -> DisplayType.LIST_AND_DETAIL
    }

    CityHomeScreen(
        uiState = cityUiState,
        displayType = displayType,
        modifier = modifier
    )
}