package com.example.tiptime

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.text.SimpleDateFormat
import java.util.Locale

@Preview
@Composable
fun TravelMainScreen(
    viewModel: BookingViewModel = viewModel(factory = AppViewModelProvider.factory),
    viewModelHotel: hotelViewModel = viewModel(factory = AppViewModelProvider.factory),
    viewRoomViewModel: RoomViewModel = viewModel(factory = AppViewModelProvider.factory),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            HotelBottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        val uiState by viewModel.uiBookingState.collectAsState()
        val uiHotelState by viewModelHotel.uiStateHotel.collectAsState()
        NavHost(
            navController = navController,
            startDestination = TravelBottomBar.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = TravelBottomBar.Home.route) {
                TravelApp()
            }
            composable(route = TravelBottomBar.Favourite.route) {
//                favoritelayout(navController, viewModelHotel, viewModel)
            }
            composable(route = TravelBottomBar.Booked.route) {
//                RoomModify(viewRoomViewModel)
            }
            composable(route = TravelBottomBar.Settings.route) {
//                HotelSettingContent()
            }
            composable(route = "reservationCheck/{date}") { backStackEntry ->
                val date = backStackEntry.arguments?.getString("date")
                val selectedDate = SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(date)!!
                ReservationCheckScreen(navController, selectedDate, viewModel)
            }
        }
    }
}


