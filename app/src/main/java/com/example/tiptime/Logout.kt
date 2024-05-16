package com.example.tiptime

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TipTimeTheme
import com.example.tiptime.ui.theme.black
import com.example.tiptime.ui.theme.watermelon


class Logout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    /*LogoutContent()*/
                }
            }
        }
    }
}

@Composable
fun LogoutContent(onDismiss:()->Unit){
    var log_out by remember { mutableStateOf(false) }
    var return_setting by remember { mutableStateOf(false) }
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        modifier = Modifier.height(250.dp),

        title = {
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(imageVector = Icons.Default.Info, contentDescription = "Confirmation")
                Text(text="Are you sure to log out? ",
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
                    onClick = {log_out = true},
                    modifier = Modifier
                        .width(180.dp)
                        .padding(10.dp)
                ){
                    Text(text="Yes ")
                }

                Button(
                    onClick = { return_setting = true },
                    modifier = Modifier
                        .width(180.dp)
                        .padding(10.dp)) {
                   Text(text = "No ")
                }
            }
        }
    )
}



/*
@Preview
@Composable
fun AboutPreview() {
    TipTimeTheme {
        LogoutContent()
    }
}*/