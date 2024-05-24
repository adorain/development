package com.example.tiptime

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
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

class Logout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LogoutContent(
                        onLogout = { logout() },
                        onCancel = { navigateToSettings() }
                    )
                }
            }
        }
    }

    private fun logout() {
        // Implement logout logic here, such as clearing session or authentication token
        // For example, navigate to the login screen
        val intent = Intent(this, UserSelection::class.java)
        startActivity(intent)
        // Finish the current activity to prevent going back to it after logout
        finish()
    }

    private fun navigateToSettings() {
        // Implement logic to navigate to the settings screen
        // For example, navigate to the settings activity
        val intent = Intent(this, UserSelection::class.java)
        startActivity(intent)
    }
}

@Composable
fun LogoutContent(onLogout:()->Unit, onCancel:()->Unit){
    AlertDialog(
        onDismissRequest = { /* Handle dismiss action if needed */ },
        confirmButton = {
            Button(
                onClick = onLogout,
                modifier = Modifier
                    .width(180.dp)
                    .padding(10.dp)
            ){
                Text(text="Confirm")
            }
        },
        dismissButton = {
            Button(
                onClick = onCancel,
                modifier = Modifier
                    .width(180.dp)
                    .padding(10.dp)
            ){
                Text(text="Cancel")
            }
        },
        modifier = Modifier.height(250.dp),

        title = {
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(imageVector = Icons.Default.Info, contentDescription = "Confirmation")
                Text(text="Are you sure you want to log out?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
            }
        },

        text = {}
    )
}



@Preview
@Composable
fun LogoutPreview() {
    TipTimeTheme {
        LogoutContent(onLogout={},onCancel={})
    }
}

