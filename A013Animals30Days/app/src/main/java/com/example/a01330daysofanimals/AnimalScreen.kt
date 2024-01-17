package com.example.a01330daysofanimals

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a01330daysofanimals.model.Animal
import com.example.a01330daysofanimals.model.AnimalData.animalData
import com.example.a01330daysofanimals.ui.theme.A01330DaysOfAnimalsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroesAppTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name_2),
                style = MaterialTheme.typography.displayLarge
            )
        },
        modifier = modifier
    )
}

@Composable
fun AnimalApp() {
    Scaffold(
        topBar = {
            SuperHeroesAppTopBar()
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
            contentPadding = it
        ) {
            items(animalData) { animal ->
                AnimalCard(animal = animal)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalCard(
    animal: Animal,
    modifier: Modifier = Modifier
) {

    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            ),
        onClick = { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Animal " + animal.id,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = stringResource(id = animal.animalName),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Image(
                painter = painterResource(id = animal.animalImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            if (expanded) {
                Text(
                    text = stringResource(id = animal.animalDescription),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

@Preview()
@Composable
fun AnimalCardPreview() {
    A01330DaysOfAnimalsTheme {
        AnimalApp()
    }
}

@Preview()
@Composable
fun AnimalCardPreviewDark() {
    A01330DaysOfAnimalsTheme(darkTheme = true) {
        AnimalApp()
    }
}