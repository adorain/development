/*package com.example.jomtravel

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
//import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.jomtravel.ui.theme.JomTravelTheme

class HotelLogin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JomTravelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HotelLoginContent()
                }
            }
        }
    }
}

@Composable
fun HotelLoginContent(){
    Column(
        modifier=Modifier
            .fillMaxSize()
            .background(color=Color(R.color.navy_blue))
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Hotel User Login",
            fontSize = 40.sp,
            color = Color.White,
            modifier = Modifier
                .padding(
                    vertical = 120.dp,
                    horizontal = 70.dp)
        )
        UserLoginTextField(
            hint = "E-mail Address",
            keyboardType = KeyboardType.Email
        )
        Spacer(modifier = Modifier.height(40.dp))
        UserLoginTextField(
            hint = "Password",
            keyboardType = KeyboardType.Password
        )
    }

}

@Composable
fun HotelLoginTextField(hint: String, keyboardType: KeyboardType) {
    TextField(
        value = "",
        onValueChange = { },
        placeholder = { Text(text = hint) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        textStyle = TextStyle(color = Color.White),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}


@Preview
@Composable
fun HotelLoginPreview() {
    JomTravelTheme {
        HotelLoginContent()
    }
}

 */
