/*package com.example.jomtravel

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.jomtravel.ui.theme.JomTravelTheme
import androidx.compose.material3.Text as Text
import com.example.jomtravel.UserLogin
import com.example.jomtravel.HotelLogin


class UserSelection : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JomTravelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserSelectionContent()
                }
            }
        }
    }
}

@Composable
fun UserSelectionContent(){
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "User Login Selection",
                fontSize = 35.sp,
                color = Color.Blue
            )
            Spacer(modifier = Modifier.height(120.dp))
            UserSelectionButton("Normal User") {
                val intent = Intent(this@UserSelectionContent, UserLoginContent)
                startActivity(intent)
                }
                /*import from UserLogin.kt */}
            Spacer(modifier = Modifier.height(120.dp))
            UserSelectionButton("Hotel User") {
                val intent = Intent(this@UserSelectionContent, HotelLoginContent)
                startActivity(intent)
                /*import from HotelLogin.kt */}
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
    JomTravelTheme {
        UserSelectionContent()
    }
}

 */

