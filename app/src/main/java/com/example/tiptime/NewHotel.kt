package com.example.tiptime

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.Data.ApplicationInventory
import com.example.tiptime.ui.theme.TipTimeTheme
import com.example.tiptime.ui.theme.red
import com.example.tiptime.ui.theme.shadow
import com.example.tiptime.ui.theme.white
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NewHotel : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var viewModel: NewHotelRegister

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()

        // Initialize the ViewModel
        val hotelDao = ApplicationInventory.getDatabase(applicationContext).hotelDao()
        viewModel = NewHotelRegister(hotelDao)

        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var showErrorDialog by remember { mutableStateOf(false) }
                    var errorMessage by remember { mutableStateOf("") }

                    if (showErrorDialog) {
                        ErrorDialog2(message = errorMessage) {
                            showErrorDialog = false
                        }
                    }

                    NewHotelContent(
                        viewModel = viewModel,
                        onClickedButton = { name, phoneNumber, email, password ->
                            createHotel(name, phoneNumber, email, password, onError = { message ->
                                errorMessage = message
                                showErrorDialog = true
                            }, viewModel)
                        }
                    )
                }
            }
        }
    }

    private fun createHotel(
        hname: String, hphoneNumber: String, hemail: String, hpassword: String,
        onError: (String) -> Unit, viewModel: NewHotelRegister
    ) {
        if (validateInput(hname, hphoneNumber, hemail, hpassword, onError)) {
            auth.createUserWithEmailAndPassword(hemail, hpassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Save user to Firestore
                        val user = hashMapOf(
                            "password" to hpassword,
                            "name" to hname,
                            "phoneNumber" to hphoneNumber,
                            "email" to hemail
                        )
                        db.collection("users").add(user)
                            .addOnSuccessListener {
                                // Update the staff information in the ViewModel
                                viewModel.updateStaff(hname, hphoneNumber, hemail, hpassword)
                                // Navigate to home page
                                val intent = Intent(this, HotelNewInformation::class.java)
                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener {
                                onError("Failed to save user data. Please try again.")
                            }
                    } else {
                        onError("Failed to create user. Please try again.")
                    }
                }
        }
    }

    private fun validateInput(hname: String, hphoneNumber: String, hemail: String, hpassword: String, onError: (String) -> Unit): Boolean {
        return when {
            hname.isBlank() || hname.length > 45 -> {
                onError("Invalid name. Please enter a valid name.")
                false
            }
            hphoneNumber.isBlank() || hphoneNumber.length != 12 -> {
                onError("Invalid phone number. Please enter a valid phone number.")
                false
            }
            hemail.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(hemail).matches() -> {
                onError("Invalid email address. Please enter a valid email.")
                false
            }
            hpassword.isBlank() || hpassword.length < 6 -> {
                onError("Password must be at least 6 characters.")
                false
            }
            else -> true
        }
    }
}

@Composable
fun ErrorDialog2(message: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Error") },
        text = { Text(message) },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}

@Composable
fun NewHotelContent(viewModel: NewHotelRegister, onClickedButton: (hname: String, hphoneNumber: String, hemail: String, hpassword: String) -> Unit) {
    var hemail by remember { mutableStateOf("") }
    var hpassword by remember { mutableStateOf("") }
    var hname by remember { mutableStateOf("") }
    var hphoneNumber by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Image(
            painter = painterResource(id = R.drawable.hotel_user_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Sign up",
                color = white,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                modifier = Modifier
                    .padding(
                        horizontal = 35.dp,
                        vertical = 63.dp
                    )
            )

            TextField(
                value = hname,
                onValueChange = { hname = it },
                label = { Text("Staff Name", color = shadow, fontWeight = FontWeight.Bold) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = hphoneNumber,
                onValueChange = { hphoneNumber = it },
                label = { Text("Phone Number", color = shadow, fontWeight = FontWeight.Bold) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = hemail,
                onValueChange = { hemail = it },
                label = { Text("Email", color = shadow, fontWeight = FontWeight.Bold) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = hpassword,
                onValueChange = { hpassword = it },
                label = { Text("Password", color = shadow, fontWeight = FontWeight.Bold) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                onClickedButton(hname, hphoneNumber, hemail, hpassword)
            }) {
                Text(text = "Submit")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewHotelPreview() {
    TipTimeTheme {
        NewHotelContent(viewModel = NewHotelRegister(ApplicationInventory.getDatabase(LocalContext.current).hotelDao()), onClickedButton = { _, _, _, _ -> })
    }
}


