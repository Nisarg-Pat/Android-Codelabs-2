package com.example.a020mycity.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a020mycity.R
import com.example.a020mycity.data.Category
import com.example.a020mycity.data.Recommendation
import com.example.a020mycity.data.RecommendationProvider
import com.example.a020mycity.ui.theme.A020MyCityTheme

@Composable
fun CategoryListItem(
    category: Category,
    onItemClick: (Category) -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false
) {
    val borderedModifier = when(selected) {
        true ->
            modifier
                .fillMaxWidth()
                .size(dimensionResource(id = R.dimen.recommendation_card_height))
                .border(
                    width = 2.dp,
                    color = Color(0xFF1E90FF),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.recommendation_card_rounded_corner))
                )
        false -> modifier
            .fillMaxWidth()
            .size(dimensionResource(id = R.dimen.recommendation_card_height))
    }
    Card(
        onClick = { onItemClick(category) },
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.category_card_rounded_corner)),
        modifier = borderedModifier
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = category.backgroundImage),
                contentDescription = stringResource(id = category.nameId),
                contentScale = ContentScale.FillWidth,
                modifier = modifier
                    .fillMaxWidth()
                    .graphicsLayer { alpha = 0.8f }
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color(0xAA000000)) // semi-transparent black
            )
            Text(
                text = stringResource(id = category.nameId),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = R.dimen.category_card_text_size).value.sp,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun CategoryList(
    categories: List<Category>,
    onClick: (Category) -> Unit,
    modifier: Modifier = Modifier,
    currentRecommendation: Recommendation? = null
) {
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.category_list_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.category_list_padding)),
        modifier = modifier,
    ) {
        items(categories, key = { category -> category.ordinal }) { category ->
            CategoryListItem(
                category = category,
                onItemClick = onClick,
                selected = currentRecommendation?.category == category
            )
        }
    }
}

@Preview
@Composable
fun CategoryListItemPreview() {
    A020MyCityTheme {
        CategoryListItem(
            category = Category.COFFEE_SHOP,
            onItemClick = {}
        )
    }
}

@Preview
@Composable
fun CategoryListPreview() {
    A020MyCityTheme {
        CategoryList(
            categories = Category.entries,
            onClick = {}
        )
    }
}

@Composable
fun RecommendationListItem(
    recommendation: Recommendation,
    onItemClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false
) {
    val borderedModifier = when(selected) {
        true ->
            modifier
                .fillMaxWidth()
                .size(dimensionResource(id = R.dimen.recommendation_card_height))
                .border(
                    width = 2.dp,
                    color = Color(0xFF1E90FF),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.recommendation_card_rounded_corner))
                )
        false -> modifier
            .fillMaxWidth()
            .size(dimensionResource(id = R.dimen.recommendation_card_height))
    }
    Card(
        onClick = { onItemClick(recommendation) },
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.recommendation_card_rounded_corner)),
        modifier = borderedModifier
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = recommendation.category.backgroundImage),
                contentDescription = stringResource(id = recommendation.name),
                contentScale = ContentScale.FillWidth,
                modifier = modifier
                    .fillMaxWidth()
                    .graphicsLayer { alpha = 0.8f }
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color(0xAA000000)) // semi-transparent black
            )
            Text(
                text = stringResource(id = recommendation.name),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = R.dimen.recommendation_card_text_size).value.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.recommendation_list_item_padding))
            )
        }
    }
}

@Composable
fun RecommendationList(
    recommendations: List<Recommendation>,
    onClick: (Recommendation) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    currentRecommendation: Recommendation? = null
) {
    BackHandler {
        onBackPressed()
    }
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.recommendation_list_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.recommendation_list_padding)),
        modifier = modifier,
    ) {
        items(recommendations, key = { recommendation -> recommendation.id }) { recommendation ->
            RecommendationListItem(
                recommendation = recommendation,
                onItemClick = onClick,
                selected = recommendation == currentRecommendation
            )
        }
    }
}

@Composable
fun RecommendationDetails(
    recommendation: Recommendation,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier
            .verticalScroll(scrollState)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.recommendation_details_image_size)),
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    painter = painterResource(id = recommendation.category.backgroundImage),
                    contentDescription = stringResource(id = recommendation.name),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { alpha = 0.8f }
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color(0xAA000000)) // semi-transparent black
                )
                Text(
                    text = stringResource(id = recommendation.name),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.recommendation_details_name_size).value.sp,
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.recommendation_details_name_padding),
                        bottom = dimensionResource(id = R.dimen.recommendation_details_name_padding)
                    )
                )
            }
            Column(
                modifier = Modifier.padding(all = dimensionResource(id = R.dimen.recommendation_details_text_padding))
            ) {
                Text(
                    text = stringResource(id = recommendation.details),
                )
                Text(
                    text = stringResource(id = R.string.location),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.recommendation_details_text_padding)
                    )
                )
                Text(
                    text = stringResource(id = recommendation.location)
                )
            }
        }
    }

}

@Preview
@Composable
fun RecommendationListItemPreview() {
    A020MyCityTheme {
        RecommendationListItem(
            recommendation = RecommendationProvider.allRecommendations[0],
            onItemClick = {}
        )
    }
}

@Preview
@Composable
fun RecommendationListPreview() {
    A020MyCityTheme {
        RecommendationList(
            recommendations = RecommendationProvider.allRecommendations.filter { recommendation -> recommendation.category == Category.COFFEE_SHOP },
            onClick = {},
            onBackPressed = {}
        )
    }
}

@Preview
@Composable
fun RecommendationDetailsPreview() {
    A020MyCityTheme {
        RecommendationDetails(
            recommendation = RecommendationProvider.allRecommendations[11],
            onBackPressed = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun CategoryAndRecommendationListAndDetails(
    categories: List<Category>,
    recommendations: List<Recommendation>,
    onCategoryClick: (Category) -> Unit,
    onRecommendationClick: (Recommendation) -> Unit,
    onBackPressed: () -> Unit,
    currentRecommendation: Recommendation,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(dimensionResource(id = R.dimen.category_and_recommendation_list_and_details_padding)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CategoryList(
            categories = categories,
            onClick = onCategoryClick,
            modifier = Modifier.weight(3f),
            currentRecommendation = currentRecommendation,
        )
        RecommendationList(
            recommendations = recommendations,
            onClick = onRecommendationClick,
            onBackPressed = {},
            modifier = Modifier.weight(3f),
            currentRecommendation = currentRecommendation
        )
        RecommendationDetails(
            recommendation = currentRecommendation,
            onBackPressed = onBackPressed,
            modifier = Modifier.weight(4f)
        )
    }
}

@Preview(widthDp = 1000)
@Composable
fun CategoryAndRecommendationsListAndDetailsPreview() {
    A020MyCityTheme {
        CategoryAndRecommendationListAndDetails(
            categories = Category.entries,
            recommendations = RecommendationProvider.allRecommendations.filter { recommendation -> recommendation.category == Category.COFFEE_SHOP },
            onCategoryClick = {},
            onRecommendationClick = {},
            onBackPressed = {},
            currentRecommendation = RecommendationProvider.allRecommendations[0],
            modifier = Modifier.fillMaxSize()
        )
    }
}