package com.example.a020mycity.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a020mycity.R
import com.example.a020mycity.data.Category
import com.example.a020mycity.data.Recommendation
import com.example.a020mycity.data.RecommendationProvider
import com.example.a020mycity.ui.theme.A020MyCityTheme
import com.example.a020mycity.ui.utils.DisplayType
import com.example.a020mycity.ui.utils.PageType

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
        onCategoryClick = {
            viewModel.updateCurrentCategory(it)
            viewModel.updateCurrentPage(PageType.RECOMMENDATION_LIST)
        },
        onRecommendationClick = {
            viewModel.updateCurrentRecommendation(it)
            viewModel.updateCurrentPage(PageType.RECOMMENDATION_DETAILS)
        },
        onRecommendationListBackPressed = {
            viewModel.updateCurrentPage(PageType.CATEGORY)
        },
        onRecommendationDetailsBackPressed = {
            viewModel.updateCurrentPage(PageType.RECOMMENDATION_LIST)
        },
        displayType = displayType,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(title = {
        Text(text = stringResource(id = R.string.my_city))
    })
}

@Composable
fun CityHomeScreen(
    uiState: CityUiState,
    onCategoryClick: (Category) -> Unit,
    onRecommendationClick: (Recommendation) -> Unit,
    onRecommendationListBackPressed: () -> Unit,
    onRecommendationDetailsBackPressed: () -> Unit,
    displayType: DisplayType,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CityTopAppBar(modifier = modifier)
        }
    ) { innerPadding ->
        if(displayType == DisplayType.LIST_ONLY) {
            if(uiState.currentPage == PageType.CATEGORY) {
                CategoryList(
                    categories = Category.entries,
                    onClick = onCategoryClick,
                    modifier = Modifier.padding(innerPadding)
                )
            } else if(uiState.currentPage == PageType.RECOMMENDATION_LIST) {
                RecommendationList(
                    recommendations = uiState.currentRecommendations,
                    onClick = onRecommendationClick,
                    onBackPressed = onRecommendationListBackPressed,
                    modifier = Modifier.padding(innerPadding)
                )
            } else {
                RecommendationDetails(
                    recommendation = uiState.currentRecommendation,
                    onBackPressed = onRecommendationDetailsBackPressed,
                    modifier = Modifier.padding(innerPadding).fillMaxSize()
                )
            }
        }

    }
}