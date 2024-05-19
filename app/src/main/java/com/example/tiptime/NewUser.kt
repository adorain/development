package com.example.tiptime

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.tiptime.Data.ApplicationInventory
import com.example.tiptime.ui.theme.TipTimeTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NewUser : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var viewModel: NewUserRegister

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val normalUserDao = ApplicationInventory.getDatabase(applicationContext).normalUserDao()
        viewModel = NewUserRegister(normalUserDao)

        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var showErrorDialog by remember { mutableStateOf(false) }
                    var errorMessage by remember { mutableStateOf("") }

                    if (showErrorDialog) {
                        ErrorDialog(message = errorMessage) {
                            showErrorDialog = false
                        }
                    }

                    NewUserContent(
                        viewModel = viewModel,
                        onClickedButton = { name, phoneNumber, email, password ->
                            createUser(name, phoneNumber, email, password, onError = { message ->
                                errorMessage = message
                                showErrorDialog = true
                            })
                        }
                    )
                }
            }
        }
    }

    private fun createUser(
        uname: String,
        uphoneNumber: String,
        uemail: String,
        upassword: String,
        onError: (String) -> Unit
    ) {
        if (validateInput(uname, uphoneNumber, uemail, upassword, onError)) {
            auth.createUserWithEmailAndPassword(uemail, upassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val userId = task.result?.user?.uid ?: return@addOnCompleteListener
                        val user = hashMapOf(
                            "userId" to userId,
                            "userName" to uname,
                            "userPhoneNumber" to uphoneNumber,
                            "userEmail" to uemail,
                            "userPassword" to upassword
                        )
                        db.collection("users").add(user)
                            .addOnSuccessListener {
                                viewModel.updateUser(uname, uphoneNumber, uemail, upassword, userId)
                                val intent = Intent(this, MainActivity::class.java)
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

    private fun validateInput(uname: String, uphoneNumber: String, uemail: String, upassword: String, onError: (String) -> Unit): Boolean {
        return when {
            uname.isBlank() || uname.length > 45 -> {
                onError("Invalid name. Please enter a valid name.")
                false
            }
            uphoneNumber.isBlank() || uphoneNumber.length != 12 -> {
                onError("Invalid phone number. Please enter a valid phone number.")
                false
            }
            uemail.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(uemail).matches() -> {
                onError("Invalid email address. Please enter a valid email.")
                false
            }
            upassword.isBlank() || upassword.length < 6 -> {
                onError("Password must be at least 6 characters.")
                false
            }
            else -> true
        }
    }
}

@Composable
fun ErrorDialog(message: String, onDismiss: () -> Unit) {
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
fun NewUserContent(viewModel: NewUserRegister, onClickedButton: (uname: String, uphoneNumber: String, uemail: String, upassword: String) -> Unit) {
    var uemail by remember { mutableStateOf("") }
    var upassword by remember { mutableStateOf("") }
    var uname by remember { mutableStateOf("") }
    var uphoneNumber by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Image(
            painter = painterResource(id = R.drawable.normal_user_background),
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
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                modifier = Modifier
                    .padding(
                        horizontal = 35.dp,
                        vertical = 63.dp
                    )
            )

            TextField(
                value = uname,
                onValueChange = { uname = it },
                label = { Text("User Name",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = uphoneNumber,
                onValueChange = { uphoneNumber = it },
                label = { Text("Phone Number",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = uemail,
                onValueChange = { uemail = it },
                label = { Text("Email",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = upassword,
                onValueChange = { upassword = it },
                label = { Text("Password",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    onClickedButton(uname, uphoneNumber, uemail, upassword)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign Up")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewUserPreview() {
    TipTimeTheme {
        val context = LocalContext.current
        val normalUserDao = ApplicationInventory.getDatabase(context).normalUserDao()
        NewUserContent(viewModel = NewUserRegister(normalUserDao), onClickedButton = { _, _, _, _ -> })
    }
}


