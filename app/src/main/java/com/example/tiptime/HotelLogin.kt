package com.example.tiptime

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tiptime.Data.ApplicationInventory
import com.example.tiptime.ui.theme.TipTimeTheme
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


class HotelLogin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val hotelViewModel: hotelViewModel by viewModels { AppViewModelProvider.factory }
        setContent {
            val navController = rememberNavController()
            HotelLoginScreen(navController = navController, viewModel = hotelViewModel)
        }
    }
}

@Composable
fun HotelLoginScreen(navController: NavController,viewModel: hotelViewModel=viewModel(factory=AppViewModelProvider.factory)) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var invalidEmail by remember { mutableStateOf(false) }
    var invalidPassword by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val hotelUserDao = ApplicationInventory.getDatabase(LocalContext.current).hotelUserDao()

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

            Column(
                modifier = Modifier
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Hotel Sign-in",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 120.dp, horizontal = 70.dp)
                )
                HotelLoginTextField(
                    hint = "E-mail Address",
                    keyboardType = KeyboardType.Email,
                    value = email,
                    onValueChange = {
                        email = it
                        invalidEmail = false
                        showError = false
                    },
                    isError = invalidEmail
                )
                Spacer(modifier = Modifier.height(20.dp))
                HotelLoginTextField(
                    hint = "Password",
                    keyboardType = KeyboardType.Password,
                    value = password,
                    onValueChange = {
                        password = it
                        invalidPassword = false
                        showError = false
                    },
                    isError = invalidPassword,
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(20.dp))
                if (showError) {
                    Text(
                        text = "Invalid email or password",
                        color = Color.Red
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            invalidEmail = true
                        } else if (password.length < 6) {
                            invalidPassword = true
                        } else {
                            coroutineScope.launch {
                                val user = hotelUserDao.getUserByEmailAndPassword(email, password)
                                if (user != null) {
                                    val hotelId = user.HotelId
                                    viewModel.setHotelId(hotelId)
                                    navController.navigate(HotelBottomBar.Home.route)
                                } else {
                                    // Show error message
                                    showError = true
                                }
                            }
                        }
                    }
                ) {
                    Text(text = "Submit")
                }


                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        // Navigate to registration page
                        navController.navigate(screen.newHotel.name)
                    }
                ) {
                    Text(text = "New User? Please Sign-up")
                }
            }
        }
    }
}

@Composable
fun HotelLoginTextField(
    hint: String,
    keyboardType: KeyboardType,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = hint) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        textStyle = TextStyle(color = Color.White),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        isError = isError
    )
}

@Preview
@Composable
fun HotelLoginPreview() {
    val navController = rememberNavController()
    val hotelViewModel: hotelViewModel = viewModel(factory = AppViewModelProvider.factory)
    HotelLoginScreen(navController = navController, viewModel = hotelViewModel)
}



