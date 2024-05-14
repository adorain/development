package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TipTimeTheme
import com.example.tiptime.ui.theme.black

class About : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AboutContent(onDismiss = {  })
                }
            }
        }
    }
}

@Composable
fun AboutContent(onDismiss:()->Unit){
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        modifier = Modifier.height(250.dp),

        title = {
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(imageVector = Icons.Default.Info, contentDescription = "About ")
                Text(text=" About",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
            }
        },

        text={
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                /*verticalAlignment = Arrangement.Center*/
            ){
                Button(
                    onClick = {},
                    modifier = Modifier
                        .width(180.dp)
                        .padding(10.dp)
                ){
                    Text(
                        "JomTravel is your go-to app for seamless travel experiences. Whether you're planning a weekend getaway or a month-long expedition, JomTravel has you covered. Discover hidden gems, book accommodations, find the best local eateries, and connect with fellow travelers for unforgettable adventures. With intuitive navigation and real-time updates, JomTravel makes exploring the world easier and more exciting than ever before. Let's embark on your next journey together!",
                        color = black,
                        fontSize = 15.sp,
                        modifier = Modifier
                    )
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(180.dp)
                        .padding(10.dp)) {
                    Text(text = " ")
                }
            }
        }
    )
}
/*
@Composable
fun AboutContent(){
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.user_selection_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "About",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = watermelon
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    "JomTravel is your go-to app for seamless travel experiences. Whether you're planning a weekend getaway or a month-long expedition, JomTravel has you covered. Discover hidden gems, book accommodations, find the best local eateries, and connect with fellow travelers for unforgettable adventures. With intuitive navigation and real-time updates, JomTravel makes exploring the world easier and more exciting than ever before. Let's embark on your next journey together!",
                    color = black,
                    fontSize = 15.sp,
                    modifier = Modifier
                )


            }

        }
    }
}*/


@Preview
@Composable
fun AboutPreview() {
    TipTimeTheme {
        AboutContent(onDismiss={})
    }
}