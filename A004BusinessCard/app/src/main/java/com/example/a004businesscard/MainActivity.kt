package com.example.a004businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a004businesscard.ui.theme.A004BusinessCardTheme
import com.example.a004businesscard.ui.theme.Teal120
import com.example.a004businesscard.ui.theme.Teal40
import com.example.a004businesscard.ui.theme.Teal80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A004BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCard(
                        "Nisarg Patel",
                        "Software Engineer",
                        "+1 (857) 763 8337",
                        "@NisargPat",
                        "patel187nisarg@gmail.com"
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(
    name: String,
    position: String,
    phone: String,
    handle: String,
    email: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Teal40)
    ) {
        NameInfo(
            name = name,
            position = position,
            modifier = Modifier
                .weight(1f)
        )
        ContactInfo(
            phone = phone,
            handle = handle,
            email = email,
            modifier = Modifier
                .padding(bottom = 32.dp)
        )
    }



}

@Composable
fun NameInfo(name: String, position: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .background(Teal120)
        )
        Text(
            text = name,
            fontSize = 32.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(top = 8.dp)
        )
        Text(
            text = position,
            color = Teal80,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 8.dp)
        )
    }
}

@Composable
fun ContactInfo(phone: String, handle: String, email: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ){
        ContactRow(
            text = phone,
            icon = Icons.Filled.Phone,
            color = Teal80,
            modifier = Modifier
                .padding(bottom = 12.dp)
        )
        ContactRow(
            text = handle,
            icon = Icons.Filled.Share,
            color = Teal80,
            modifier = Modifier
                .padding(bottom = 12.dp)
        )
        ContactRow(
            text = email,
            icon = Icons.Filled.Email,
            color = Teal80
        )
    }
}

@Composable
fun ContactRow(text: String, icon: ImageVector, color: Color, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = color
        )
        Text(
            text = text,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    A004BusinessCardTheme {
        BusinessCard(
            "Nisarg Patel",
            "Software Engineer",
            "+1 (857) 763 8337",
            "@NisargPat",
            "patel187nisarg@gmail.com"
        )
    }
}