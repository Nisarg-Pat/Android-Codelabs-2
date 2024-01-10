package com.example.a010coursesgrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a010coursesgrid.data.DataSource
import com.example.a010coursesgrid.model.Topic
import com.example.a010coursesgrid.ui.theme.A010CoursesGridTheme
import com.example.a010coursesgrid.ui.theme.Purple80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A010CoursesGridTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CourseGridApp()
                }
            }
        }
    }
}

@Composable
fun CourseGridApp() {
    CourseGrid(courseTopics = DataSource.topics)
}

@Composable
fun CourseGrid(
    courseTopics: List<Topic>,
    modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(courseTopics) { topic ->
            TopicItem(topic)
        }
    }
}

@Composable
fun TopicItem(
    topic: Topic,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(percent = 15),
        modifier = modifier.height(68.dp)
    ) {
        Row(
            modifier = modifier.background(Purple80)
        ) {
            Image(
                painter = painterResource(id = topic.image),
                contentDescription = null,
                modifier = Modifier.aspectRatio(1F)
            )
            Column(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = stringResource(id = topic.name),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_grain),
                        colorFilter = ColorFilter.tint(Color.Black),
                        contentDescription = null
                    )
                    Text(
                        text = topic.associatedCourses.toString(),
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun TopicItemPreview() {
    A010CoursesGridTheme {
        CourseGridApp()
    }
}