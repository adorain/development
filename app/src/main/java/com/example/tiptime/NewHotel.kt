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
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewHotel : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var viewModel: NewHotelRegister

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()

        // Initialize the ViewModel
        val hotelUserDao = ApplicationInventory.getDatabase(applicationContext).hotelUserDao()
        viewModel = NewHotelRegister(hotelUserDao)

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

                    var currentStep by remember { mutableStateOf(1) }

                    var staffName by remember { mutableStateOf("") }
                    var staffPhoneNumber by remember { mutableStateOf("") }
                    var staffEmail by remember { mutableStateOf("") }
                    var staffPassword by remember { mutableStateOf("") }

                    if (currentStep == 1) {
                        StaffDetailsForm(
                            viewModel = viewModel,
                            onNextClicked = {
                                currentStep = 2
                            },
                            onSetName = { staffName = it },
                            onSetNumber = { staffPhoneNumber = it },
                            onSetEmail = { staffEmail = it },
                            onSetPassword = { staffPassword = it }
                        )
                    } else {
                        HotelDetailsForm(
                            viewModel = viewModel,
                            onSubmitClicked = {hotelName, hotelAddress, hotelDescription, type ->
                                createHotel(staffName, staffPhoneNumber, staffEmail, staffPassword,
                                    hotelName, hotelAddress, hotelDescription, type, onError = { message ->
                                        errorMessage = message
                                        showErrorDialog = true
                                    }, viewModel)
                            },
                            onSetHotelName = { viewModel.hotelName = it },
                            onSetHotelAddress = { viewModel.hotelAddress = it },
                            onSetHotelDescription = { viewModel.hotelDescription = it },
                            onSetHotelType = { viewModel.hotelType = it }
                        )
                    }
                }
            }
        }
    }

    private fun createHotel(
        hname: String, hphoneNumber: String, hemail: String, hpassword: String,
        hotelName: String, hotelAddress: String, hotelDescription: String, type: String,
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
                                // Insert staff information into Room database
                                CoroutineScope(Dispatchers.IO).launch {
                                    viewModel.insertStaff(
                                        hname, hphoneNumber, hemail, hpassword,
                                        hotelName, hotelAddress, hotelDescription, type
                                    )
                                }
                                // Navigate to home page
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
fun StaffDetailsForm(
    viewModel: NewHotelRegister,
    onNextClicked: () -> Unit,
    onSetName: (String) -> Unit,
    onSetNumber: (String) -> Unit,
    onSetEmail: (String) -> Unit,
    onSetPassword: (String) -> Unit
) {
    var name by remember { mutableStateOf(viewModel.staffName) }
    var phoneNumber by remember { mutableStateOf(viewModel.staffPhoneNumber) }
    var email by remember { mutableStateOf(viewModel.staffEmail) }
    var password by remember { mutableStateOf(viewModel.staffPassword) }

    Surface(
        modifier=Modifier.fillMaxSize(),
        color=Color.White
    ) {
        Image(
            painter=painterResource(id = R.drawable.hotel_user_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier=Modifier.fillMaxWidth()
        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Staff Details",
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
            value = name,
            onValueChange = {
                name = it
                onSetName(it)
            },
            label = { Text("Staff Name", color = shadow, fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
                onSetNumber(it)
            },
            label = { Text("Phone Number", color = shadow, fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = email,
            onValueChange = {
                email = it
                onSetEmail(it)
            },
            label = { Text("Email", color = shadow, fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = password,
            onValueChange = {
                password = it
                onSetPassword(it)
            },
            label = { Text("Password", color = shadow, fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            viewModel.staffName = name
            viewModel.staffPhoneNumber = phoneNumber
            viewModel.staffEmail = email
            viewModel.staffPassword = password
            onNextClicked()
        }) {
            Text(text = "Next")
        }
    }
    }
}

@Composable
fun HotelDetailsForm(
    viewModel: NewHotelRegister,
    onSubmitClicked: (
        hotelName: String, hotelAddress: String, hotelDescription: String, type: String
    ) -> Unit,
    onSetHotelName: (String) -> Unit,
    onSetHotelAddress: (String) -> Unit,
    onSetHotelDescription: (String) -> Unit,
    onSetHotelType: (String) -> Unit
) {
    var hotelName by remember { mutableStateOf(viewModel.hotelName) }
    var hotelAddress by remember { mutableStateOf(viewModel.hotelAddress) }
    var hotelDescription by remember { mutableStateOf(viewModel.hotelDescription) }
    var type by remember { mutableStateOf(viewModel.hotelType) }


    Surface(
        modifier=Modifier.fillMaxSize(),
        color=Color.White
    ) {
        Image(
            painter = painterResource(id = R.drawable.hotel_user_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Hotel Details",
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
                value = hotelName,
                onValueChange = {
                    hotelName = it
                    onSetHotelName(it)
                },
                label = { Text("Hotel Name", color = shadow, fontWeight = FontWeight.Bold) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = hotelAddress,
                onValueChange = {
                    hotelAddress = it
                    onSetHotelAddress(it)
                },
                label = { Text("Hotel Address", color = shadow, fontWeight = FontWeight.Bold) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = hotelDescription,
                onValueChange = {
                    hotelDescription = it
                    onSetHotelDescription(it)
                },
                label = { Text("Hotel Description", color = shadow, fontWeight = FontWeight.Bold) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = type,
                onValueChange = {
                    type = it
                    onSetHotelType(it)
                },
                label = { Text("Type", color = shadow, fontWeight = FontWeight.Bold) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                onSubmitClicked(
                    hotelName, hotelAddress, hotelDescription, type
                )
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
        StaffDetailsForm(
            viewModel = NewHotelRegister(ApplicationInventory.getDatabase(LocalContext.current).hotelUserDao()),
            onNextClicked = {},
            onSetName = {},
            onSetNumber = {},
            onSetEmail = {},
            onSetPassword = {}
        )
    }
}