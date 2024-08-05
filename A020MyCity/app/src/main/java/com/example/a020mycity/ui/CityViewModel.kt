package com.example.a020mycity.ui

import androidx.lifecycle.ViewModel
import com.example.a020mycity.data.Category
import com.example.a020mycity.data.Recommendation
import com.example.a020mycity.data.RecommendationProvider
import com.example.a020mycity.ui.utils.PageType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CityViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState

    init {
        initializeUIState()
    }

    private fun initializeUIState() {
        val recommendations: Map<Category, List<Recommendation>> =
            RecommendationProvider.allRecommendations.groupBy { it.category }
        _uiState.value =
            CityUiState(
                recommendations = recommendations,
                currentCategory = Category.COFFEE_SHOP,
                currentRecommendation = recommendations[Category.COFFEE_SHOP]?.get(0)!!,
                currentPage = PageType.CATEGORY
            )
    }

    fun updateCurrentPage(currentPage: PageType) {
        _uiState.update {
            it.copy(currentPage = currentPage)
        }
    }

    fun updateCurrentCategory(category: Category) {
        _uiState.update {
            it.copy(currentCategory = category)
        }
    }

    fun updateCurrentRecommendation(recommendation: Recommendation) {
        _uiState.update {
            it.copy(currentRecommendation = recommendation)
        }
    }
}