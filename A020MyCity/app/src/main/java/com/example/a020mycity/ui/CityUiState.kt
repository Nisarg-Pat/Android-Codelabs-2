package com.example.a020mycity.ui

import com.example.a020mycity.data.Category
import com.example.a020mycity.data.Recommendation
import com.example.a020mycity.ui.utils.PageType

data class CityUiState (
    val recommendations: Map<Category, List<Recommendation>> = mapOf(),
    val currentCategory: Category = Category.COFFEE_SHOP,
    val currentRecommendation: Recommendation = Recommendation(0,Category.COFFEE_SHOP,0,0,0),
    val currentPage: PageType = PageType.CATEGORY
) {
    val currentRecommendations: List<Recommendation> by lazy { recommendations[currentCategory]!! }

    fun getFirstRecommendation(category: Category): Recommendation {
        return recommendations[category]!![0]
    }
}