package com.example.a023amphibians.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.a023amphibians.R
import com.example.a023amphibians.model.Amphibian

@Composable
fun HomeScreen(
    uiState: UIState,
    modifier: Modifier = Modifier
) {
    when(uiState) {
        is UIState.Loading -> LoadingScreen(modifier)
        is UIState.Success -> AmphibianScreen(uiState.amphibians, modifier)
        is UIState.Error -> ErrorScreen( modifier)
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Text(text = stringResource(id = R.string.loading), modifier = modifier)
}

@Composable
fun AmphibianScreen(
    amphibians: List<Amphibian>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = amphibians, key = {amphibian -> amphibian.name}) { amphibian ->
            AmphibianCard(amphibian)
        }
    }
}

@Composable
fun AmphibianCard(
    amphibian: Amphibian,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
        ){
            Text(
                text = "${amphibian.name} (${amphibian.type})",
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = amphibian.name,
                placeholder = painterResource(id = R.drawable.async_image_placeholder),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = amphibian.description,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier
) {
    Text(text = stringResource(id = R.string.error), modifier = modifier)
}

@Preview
@Composable
fun AmphibianCardPreview() {
    AmphibianCard(Amphibian("Great Basin Spadefoot","Toad", "This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots.", "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"))
}