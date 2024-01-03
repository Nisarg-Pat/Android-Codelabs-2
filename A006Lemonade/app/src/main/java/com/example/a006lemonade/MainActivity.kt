package com.example.a006lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a006lemonade.ui.theme.A006LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A006LemonadeTheme(darkTheme = false) {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),

            )
        },
    ) { innerPadding ->
        LemonadeView(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(innerPadding)
        )
    }

}

@Composable
fun LemonadeView(modifier: Modifier = Modifier) {
    // A surface container using the 'background' color from the theme
    var state by remember { mutableIntStateOf(0) }

    val stateData = when(state) {
        0 -> State(
            R.drawable.lemon_tree,
            R.string.lemon_tree_cd,
            R.string.lemon_tree
        )
        1 -> State(
            R.drawable.lemon_squeeze,
            R.string.lemon_cd,
            R.string.lemon
        )
        2 -> State(
            R.drawable.lemon_drink,
            R.string.lemonade_cd,
            R.string.lemonade
        )
        else -> State(
            R.drawable.lemon_restart,
            R.string.empty_glass_cd,
            R.string.empty_glass
        )
    }

    val requiredSqueezes = (2..4).random()
    var currentSqueezes = 0

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {
                when(state) {
                    0 -> state=1
                    1 -> {
                        currentSqueezes++
                        if(currentSqueezes == requiredSqueezes) {
                            state = 2;
                        }
                    }
                    2 -> state=3
                    else -> state = 0
                }
            },
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.button_corner_radius)),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.button_background)),

        ) {
            Image(
                painter = painterResource(id = stateData.buttonImageId),
                contentDescription = stringResource(id = stateData.buttonImageCDId),
                modifier = Modifier.padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.size(18.dp))
        Text(
            text = stringResource(id = stateData.textStringId),
            fontSize = 18.sp
        )
    }
}

data class State(val buttonImageId: Int, val buttonImageCDId: Int, val textStringId: Int)