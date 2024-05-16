package com.example.tiptime

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TipTimeTheme
import com.example.tiptime.ui.theme.black
import com.example.tiptime.ui.theme.lavender
import com.example.tiptime.ui.theme.white

class UserLogin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserLoginContent()
                }
            }
        }
    }
}


@Composable
fun UserLoginContent(){
    var clickSubmit by remember { mutableStateOf(false) }
    var clickNewUserRegister by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.normal_user_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
    Column(
        modifier= Modifier
            .fillMaxSize()
/*            .background(color = lavender)*/
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "User Sign-in",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = white,
            modifier = Modifier
                .padding(
                    vertical = 120.dp,
                    horizontal = 70.dp
                )
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
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = { clickSubmit = true }, modifier = Modifier) {
            Text(text = "Submit")

        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = { clickNewUserRegister = true }, modifier = Modifier) {
            Text(text = "New User? Please Sign-up")

        }

        if(clickSubmit){
            //Validation
        }else{

        }
        if(clickNewUserRegister){
            //redirect to register page
        }else{

        }

    }
    }

}

@Composable
fun UserLoginTextField(hint: String, keyboardType: KeyboardType) {
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
fun UserLoginPreview() {
    TipTimeTheme {
        UserLoginContent()
    }
}
