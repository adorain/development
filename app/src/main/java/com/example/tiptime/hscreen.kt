package com.example.tiptime

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tiptime.Data.Booking
import com.example.tiptime.Data.Hotel
import com.example.tiptime.Data.room
import com.example.tiptime.ui.theme.TipTimeTheme
import java.util.Date

enum class data{
    start , bookingd,payment
}
@Composable
fun TravelApp(
    viewModel: BookingViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    Scaffold(

    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        val hotel = Hotel(
            "12345",
            "Hotel123",
            "2024-04-25",
            "2024-04-28",
            "Confirmed",
            2,
            "da",
            "Available"
        )

        val room = room(
            "ds",
            "ds",
            0.00,
            "ds"
        )
        val booking = Booking(
            "12345",
            "Hotel123",
            "",
            Date(),
            Date(),
            "Confirmed",
            0.00
        )
        NavHost(
            navController = navController,
            startDestination = data.start.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable( route = data.start.name){
                booking(

                    onNextButtonClicked = {navController.navigate(data.bookingd.name)},
                    onCancelButtonClicked = {navController.navigate(data.start.name)},

                )
            }
            composable( route = data.bookingd.name){
                bookingDetails(
                    booking = booking,
                    onCancelButtonClicked = {navController.navigate(data.start.name)},
                    onNextButtonClicked = {navController.navigate(data.payment.name)}
                )
            }
            composable( route = data.payment.name){
                PaymentLayout(
                )
            }
        }

    }
}

private fun cancelOrderAndNavigateToStart(
    navController: NavController
){

    navController.popBackStack(data.start.name , inclusive = false)
}
