package com.example.a008artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a008artspace.ui.theme.A008ArtSpaceTheme
import com.example.a008artspace.ui.theme.artInfoBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A008ArtSpaceTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    ArtSpaceView(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun ArtSpaceView(modifier: Modifier = Modifier) {

    var state by remember { mutableIntStateOf(1) }

    val brawlerDetails = when (state) {
        1 -> State(R.drawable.spike, R.string.spike, R.string.spike_mastery, R.string.spike_release)
        2 -> State(R.drawable.edgar, R.string.edgar, R.string.edgar_mastery, R.string.edgar_release)
        else -> State(
            R.drawable.surge,
            R.string.surge,
            R.string.surge_mastery,
            R.string.surge_release
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        ArtImage(
            brawlerImage = brawlerDetails.brawlerImage,
            modifier = Modifier.padding(32.dp)
        )
        ArtInfo(
            brawlerName = brawlerDetails.brawlerName,
            brawlerTitle = brawlerDetails.brawlerTitle,
            brawlerYear = brawlerDetails.brawlerYear,
            modifier = Modifier.padding(32.dp)
        )
        Row(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    when (state) {
                        2 -> state = 1
                        3 -> state = 2
                    }
                },
                modifier = Modifier
                    .width(140.dp)
            ) {
                Text(text = stringResource(id = R.string.previous))
            }
            Button(
                onClick = {
                    when (state) {
                        1 -> state = 2
                        2 -> state = 3
                    }
                },
                modifier = Modifier
                    .width(140.dp)
            ) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}

@Composable
fun ArtImage(
    @DrawableRes brawlerImage: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
            .shadow(10.dp, RoundedCornerShape(10.dp))
    ) {
        Image(
            painter = painterResource(id = brawlerImage),
            contentDescription = null,
            modifier = Modifier
                .padding(32.dp)
                .border(width = 2.dp, Color.Black, RoundedCornerShape(5.dp))
                .padding(32.dp)
        )
    }
}

@Composable
fun ArtInfo(
    @StringRes brawlerName: Int,
    @StringRes brawlerTitle: Int,
    @StringRes brawlerYear: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(artInfoBackground)
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = brawlerTitle),
            fontSize = 32.sp
        )
        Row() {
            Text(
                text = stringResource(id = brawlerName),
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = stringResource(id = R.string.release_format, stringResource(brawlerYear)),
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    A008ArtSpaceTheme {
        ArtSpace()
    }
}

data class State(
    val brawlerImage: Int,
    val brawlerName: Int,
    val brawlerTitle: Int,
    val brawlerYear: Int,
)