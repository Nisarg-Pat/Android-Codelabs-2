package com.example.a020mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.a020mycity.R
import com.example.a020mycity.data.Category
import com.example.a020mycity.ui.theme.A020MyCityTheme

@Composable
fun CategoryList(
    categories: List<Category>,
    onClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.category_list_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.category_list_padding)),
        modifier = modifier,
    ) {
        items(categories, key = { category -> category.ordinal }) { category ->
            CategoryListItem(
                category = category,
                onItemClick = onClick
            )
        }
    }
}

@Composable
fun CategoryListItem(
    category: Category,
    onItemClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onItemClick(category) },
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.category_card_rounded_corner)),
        modifier = modifier
            .fillMaxWidth()
            .size(dimensionResource(id = R.dimen.category_card_height))
    ){
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