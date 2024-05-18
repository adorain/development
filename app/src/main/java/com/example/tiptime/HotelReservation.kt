package com.example.tiptime

import HotelTopBar
import ReservationCheckScreen
import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tiptime.Data.Hotel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "hotelReservation") {
        composable("hotelReservation") { HotelReservation(navController) }
        composable("reservationCheck/{date}/{hotelName}") { backStackEntry ->
            val date = backStackEntry.arguments?.getString("date")
            val hotelName = backStackEntry.arguments?.getString("hotelName")
            if (date != null && hotelName != null) {
                ReservationCheckScreen(
                    selectedStartDate = SimpleDateFormat("yyyy-MM-dd").parse(date)!!,
                    selectedEndDate = SimpleDateFormat("yyyy-MM-dd").parse(date)!!,
                    hotelName = hotelName
                )
            }
        }
    }
}

@Preview
@Composable
fun HotelReservation(navController: NavController = rememberNavController(), viewModel: hotelViewModel = viewModel()) {
    var currentPage by remember { mutableStateOf("Reservation") }
    val selectedDate = remember { mutableStateOf(Calendar.getInstance().time) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            HotelTopBar(
                currentScreen = currentPage
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Handle adding reservation */ }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Reservation")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Button(
                    onClick = {
                        showDatePicker(
                            context = context,
                            date = selectedDate.value,
                            onDateSelected = { date ->
                                selectedDate.value = date
                            }
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = "Calendar",
                        modifier = Modifier.size(24.dp)
                    )
                    Text("Select Date")
                }

                Button(
                    onClick = {
                        val dateString = SimpleDateFormat("yyyy-MM-dd").format(selectedDate.value)
                        navController.navigate("reservationCheck/$dateString/${viewModel.uiStateHotel.value.HotelName}") // Using hotel name from ViewModel
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Icon(painter = painterResource(id = R.drawable.home), contentDescription = null)
                    Text("Check Reservations")
                }
            }
        }
    )
}

