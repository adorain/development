package com.example.tiptime
/*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiptime.ui.theme.TipTimeTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import org.jdom.Text

/*
class NewHotel : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
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
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phonenumber by remember { mutableStateOf("") }
    var invalidName by remember { mutableStateOf(false) }
    var invalidPhoneNumber by remember { mutableStateOf(false) }
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

    Column {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { org.jdom.Text("Hotel Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(color = white),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        if (invalidName) {
            Text(text = "Invalid email address", color = red)
        }
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = phonenumber,
            onValueChange = { phonenumber = it },
            label = { org.jdom.Text("Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(color = white),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        if (invalidPhoneNumber) {
            Text(text = "Invalid phone number", color = red)
        }
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { org.jdom.Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(color = white),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        if (invalidEmail) {
            Text(text = "Invalid email address", color = red)
        }
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { org.jdom.Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(color = white),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        if (invalidPassword) {
            Text(text = "Password must be at least 6 characters", color = red)
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            if (name.isBlank() ||name.length < 45) {
                invalidName = true
            }
            else if (phonenumber.isBlank() ||phonenumber.length < 12) {
                invalidPassword = true
            }
            else if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                invalidEmail = true
            } else if (password.isBlank() ||password.length < 6) {
                invalidPassword = true
            } else {
                // Placeholder logic for demonstration purposes
                val validCredentials = email == "JT@gmail.com" && password == "JT123"
                val validCredentials2 = email == "LHY@gmail.com" && password == "HY123"
                val validCredentials3 = email == "WKC@gmail.com" && password == "KC123"
                val validCredentials4 = email == "LXL@gmail.com" && password == "XL123"
                val validCredentials5 = email == "LLW@gmail.com" && password == "LW123"

                if (validCredentials||validCredentials2||validCredentials3||validCredentials4||validCredentials5) {
                    // Navigate to home page (replace MainActivity::class.java with your actual home activity)
                    val intent = Intent(context, HotelNewInformation::class.java)
                    context.startActivity(intent)
                    // Finish the current activity to prevent going back to it after login
                    (context as ComponentActivity).finish()
                } else {
                    // Show error message
                    showError = true
                }
            }

            val auth = com.google.firebase.Firebase.auth
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(context as ComponentActivity) { task ->
                    if (task.isSuccessful) {
                        showSuccessDialog = true
                    } else {
                        showFailureDialog = true
                    }
                }
        }) {
            Text(text = "Submit")
        }

        if (showError) {
            Text(text = "Invalid login credentials", color = red)
        }
    }

}


@Composable
fun ShowSuccessHotelDialog(onDismiss: () -> Unit) {
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
fun ShowFailureHotelDialog(onDismiss: () -> Unit) {
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

@Preview(showBackground = true)
@Composable
fun NewHotelPreview() {
    TipTimeTheme {
        NewHotelContent()
    }
}

class NewHotel : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()

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

                    NewHotelContent(onClickedButton = { name, phoneNumber, email, password ->
                        createHotel(name, phoneNumber, email, password, onError = { message ->
                            errorMessage = message
                            showErrorDialog = true
                        })
                    })
                }
            }
        }
    }

    private fun createHotel(name: String, phoneNumber: String, email: String, password: String, onError: (String) -> Unit) {
        if (validateInput(name, phoneNumber, email, password, onError)) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Save user to Firestore
                        val user = hashMapOf(
                            "name" to name,
                            "phoneNumber" to phoneNumber,
                            "email" to email
                        )
                        db.collection("users").add(user)
                            .addOnSuccessListener {
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

    private fun validateInput(name: String, phoneNumber: String, email: String, password: String, onError: (String) -> Unit): Boolean {
        return when {
            name.isBlank() || name.length > 45 -> {
                onError("Invalid name. Please enter a valid name.")
                false
            }
            phoneNumber.isBlank() || phoneNumber.length != 12 -> {
                onError("Invalid phone number. Please enter a valid phone number.")
                false
            }
            email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                onError("Invalid email address. Please enter a valid email.")
                false
            }
            password.isBlank() || password.length < 6 -> {
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
fun NewHotelContent(onClickedButton: (name: String, phoneNumber: String, email: String, password: String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.hotel_user_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Hotel Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(color = Color.White),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(color = Color.White),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(color = Color.White),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(color = Color.White),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            onClickedButton(name, phoneNumber, email, password)
        }) {
            Text(text = "Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewHotelPreview() {
    TipTimeTheme {
        NewHotelContent(onClickedButton = { _, _, _, _ -> })
    }
}

