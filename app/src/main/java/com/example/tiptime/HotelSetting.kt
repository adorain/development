package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TipTimeTheme
import com.example.tiptime.ui.theme.black
import com.example.tiptime.ui.theme.navy_blue
import com.example.tiptime.ui.theme.white

class HotelSetting : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HotelSettingContent()
                }
            }
        }
    }
}

@Composable
fun HotelSettingContent(){
    var showLogoutDialog by remember {mutableStateOf(false)}
    var showAboutDialog by remember { mutableStateOf(false)}
    if (showLogoutDialog){
        LogoutContent {
            var onDismiss = { showLogoutDialog = false }
        }
    }

    if (showAboutDialog){
        AboutContent {
            var onDismiss = { showAboutDialog = false }
        }
    }

    Column(
        modifier= Modifier
            /*.fillMaxSize()*/
           /* .background(color = navy_blue)*/
            .padding(horizontal = 3.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Setting",
            color = black,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            modifier = Modifier
                .padding(
                    horizontal = 3.dp,
                    vertical = 3.dp
                )
        )

        Column(
            modifier = Modifier
                /*.fillMaxSize()*/
                /*.background(color = navy_blue)*/
                .padding(horizontal = 3.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Account",
                color = black,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(
                        horizontal = 35.dp
                    )
            )

            Text(
                "Edit Profile",
                color = black,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(
                        horizontal = 35.dp,
                        vertical = 30.dp
                    )
            )

            Button(onClick = {showLogoutDialog=true}){
                Text(
                    "Log Out",
                    color = black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(
                            horizontal = 35.dp,
                            vertical = 30.dp
                        )
                )
            }


        }

        Column(
            modifier = Modifier
              /*  .fillMaxSize()*/
              /*  .background(color = navy_blue)*/
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Preferences",
                color = black,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(
                        horizontal = 35.dp
                    )
            )

            Text(
                "Language",
                color = black,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(
                        horizontal = 35.dp,
                        vertical = 30.dp
                    )
            )

            Text(
                "Currency",
                color = black,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(
                        horizontal = 35.dp,
                        vertical = 30.dp
                    )
            )
        }

        Column(
            modifier = Modifier
                /*.fillMaxSize()
                .background(color = navy_blue)*/
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "App Information",
                color = black,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(
                        horizontal = 35.dp
                    )
            )

            Button(onClick = {showAboutDialog=true}) {
                Text(
                    "About",
                    color = black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(
                            horizontal = 35.dp,
                            vertical = 30.dp
                        )
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun HotelSettingPreview() {
    TipTimeTheme {
        HotelSettingContent()
    }
}

