package com.example.tiptime

import android.content.Context
import android.content.Intent
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
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TipTimeTheme
import com.example.tiptime.ui.theme.watermelon

class UserSelection : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    UserSelectionContent(onUserTypeSelected = { userType ->
                        when (userType) {
                            UserType.user -> startActivity(Intent(this, NewUser::class.java))
                            UserType.staff -> startActivity(Intent(this, NewHotel::class.java))
                        }
                    })
                }
            }
        }
    }
}



@Composable
fun UserSelectionContent(onUserTypeSelected: (UserType) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.user_selection_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Image(
                painter = painterResource(R.drawable.jt_logo), contentDescription = null,
            )
            Spacer(modifier = Modifier.height(120.dp))
            Text(
                text = "Mode Selection",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = watermelon
            )
            Spacer(modifier = Modifier.height(120.dp))
            UserSelectionButton("Normal User") {
                onUserTypeSelected(UserType.user)
            }
            Spacer(modifier = Modifier.height(60.dp))
            UserSelectionButton("Hotel User") {
                onUserTypeSelected(UserType.staff)
            }
        }
    }
}




@Composable
fun UserSelectionButton(text: String, onClick:  () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            text = text,
            fontSize = 25.sp,
            fontStyle = FontStyle.Italic
        )
    }
}


@Preview
@Composable
fun UserSelectionPreview() {
    TipTimeTheme {
        UserSelectionContent(onUserTypeSelected = { })
    }
}

