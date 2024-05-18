package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TipTimeTheme
import org.jdom.Text

/*
class HotelNewInformation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewInformationContent()
                }
            }
        }
    }
}

@Composable
fun NewInformationContent(){
    val context = LocalContext.current
    var hotelemail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var invalidEmail by remember { mutableStateOf(false) }
    var invalidPassword by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var showFailureDialog by remember { mutableStateOf(false) }

    if (showSuccessDialog) {
        ShowSuccessHotelDialog { showSuccessDialog = false }
    }
    if (showFailureDialog) {
        ShowFailureHotelDialog { showFailureDialog = false }
    }
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
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hotel New Information",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = white,
                modifier = Modifier
                    .padding(
                        vertical = 120.dp,
                        horizontal = 70.dp
                    )
            )
            HotelNewInformationTextField(
                hint = "Hotel Name",
                keyboardType = KeyboardType.Text
            )

            Spacer(modifier = Modifier.height(40.dp))
            HotelNewInformationTextField(
                hint = "Hotel Address",
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(40.dp))
            HotelNewInformationTextField(
                hint = "Hotel Description",
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(40.dp))

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Next")
            }


        }
    }

}

@Composable
fun ShowSuccessInfoDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { org.jdom.Text("Success") },
        text = { org.jdom.Text("User created successfully.") },
        confirmButton = {
            Button(onClick = onDismiss) {
                org.jdom.Text("OK")
            }
        }
    )
}

@Composable
fun ShowFailureInfoDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { org.jdom.Text("Failure") },
        text = { org.jdom.Text("Failed to create user. Please try again.") },
        confirmButton = {
            Button(onClick = onDismiss) {
                org.jdom.Text("OK")
            }
        }
    )
}


@Composable
fun HotelNewInformationTextField(hint: String, keyboardType: KeyboardType) {
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
fun HotelNewInformationPreview() {
    TipTimeTheme {
        NewInformationContent()
    }
}*/

class HotelNewInformation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewInformationContent(onClickedButton = { _, _, _ -> })
                }
            }
        }
    }
}

@Composable
fun NewInformationContent(onClickedButton: (hotelName: String, hotelAddress: String, hotelDescription: String) -> Unit){
    val context = LocalContext.current
    var hotelName by remember { mutableStateOf("") }
    var hotelAddress by remember { mutableStateOf("") }
    var hotelDescription by remember { mutableStateOf("") }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var showFailureDialog by remember { mutableStateOf(false) }

    if (showSuccessDialog) {
        ShowSuccessInfoDialog { showSuccessDialog = false }
    }
    if (showFailureDialog) {
        ShowFailureInfoDialog { showFailureDialog = false }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.hotel_user_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Hotel",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(
                        vertical = 35.dp,
                        horizontal = 63.dp
                    )
            )
            Text(
                text = "New",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(
                        vertical = 35.dp,
                        horizontal = 63.dp
                    )
            )
            Text(
                text = "Information",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(
                        vertical = 35.dp,
                        horizontal = 63.dp
                    )
            )
            HotelNewInformationTextField(
                hint = "Hotel Name",
                keyboardType = KeyboardType.Text,
                value = hotelName,
                onValueChange = { hotelName = it }
            )

            Spacer(modifier = Modifier.height(40.dp))
            HotelNewInformationTextField(
                hint = "Hotel Address",
                keyboardType = KeyboardType.Text,
                value = hotelAddress,
                onValueChange = { hotelAddress = it }
            )
            Spacer(modifier = Modifier.height(40.dp))
            HotelNewInformationTextField(
                hint = "Hotel Description",
                keyboardType = KeyboardType.Text,
                value = hotelDescription,
                onValueChange = { hotelDescription = it }
            )
            Spacer(modifier = Modifier.height(40.dp))

            Button(onClick = {
                // Save data to database here
                onClickedButton(hotelName, hotelAddress, hotelDescription)
            }) {
                Text(text = "Submit")
            }
        }
    }
}



@Composable
fun ShowSuccessInfoDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Success") },
        text = { Text("Hotel information saved successfully.") },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}

@Composable
fun ShowFailureInfoDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Failure") },
        text = { Text("Failed to save hotel information. Please try again.") },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}

@Composable
fun HotelNewInformationTextField(
    hint: String,
    keyboardType: KeyboardType,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = hint) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        textStyle = TextStyle(color = Color.White),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

@Preview
@Composable
fun HotelNewInformationPreview() {
    TipTimeTheme {
        NewInformationContent(onClickedButton = { _, _, _ -> })
    }
}
