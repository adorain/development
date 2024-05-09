package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TipTimeTheme
import com.example.tiptime.ui.theme.white

class NewHotel : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewHotelContent()
                }
            }
        }
    }
}

@Composable
fun NewHotelContent(){
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.hotel_user_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                /*                .background(color = navy_blue)*/
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign-up",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = white,
                modifier = Modifier
                    .padding(
                        vertical = 120.dp,
                        horizontal = 70.dp
                    )
            )
            NewHotelTextField(
                hint = "Name",
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(40.dp))
            NewHotelTextField(
                hint = "Phone Number",
                keyboardType = KeyboardType.Phone
            )
            Spacer(modifier = Modifier.height(40.dp))
            NewHotelTextField(
                hint = "Gender (Male/Female)",
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(40.dp))
            NewHotelTextField(
                hint = "E-mail Address",
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(40.dp))
            NewHotelTextField(
                hint = "Password",
                keyboardType = KeyboardType.Password
            )
            Spacer(modifier = Modifier.height(40.dp))

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Submit")
            }


        }
    }

}




@Composable
fun NewHotelTextField(hint: String, keyboardType: KeyboardType) {
    TextField(
        value = "",
        onValueChange = { },
        placeholder = { Text(text = hint) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        textStyle = TextStyle(color = white),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

@Preview
@Composable
fun NewHotelPreview() {
    TipTimeTheme {
        NewHotelContent()
    }
}

