package com.example.tiptime

import EditRoomsViewModelFactory
import HotelReport
import HotelReservation
import ReservationCheckScreen
import TopAppBar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.SimpleDateFormat
import java.util.Locale



@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HotelMainScreen(
    viewModel: BookingViewModel = viewModel(factory = AppViewModelProvider.factory),
    viewModelHotel: hotelViewModel = viewModel(factory = AppViewModelProvider.factory),
    viewRoomViewModel: RoomViewModel = viewModel(factory = AppViewModelProvider.factory),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            TopAppBar()
        },
        bottomBar = {
            HotelBottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        val uiState by viewModel.uiBookingState.collectAsState()
        val uiHotelState by viewModelHotel.uiStateHotel.collectAsState()
        NavHost(
            navController = navController,
            startDestination = HotelBottomBar.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = HotelBottomBar.Home.route) {
                HotelReport(viewModelHotel)
            }
            composable(route = HotelBottomBar.Reservation.route) {
                HotelReservation(navController, viewModelHotel, viewModel, onNextButton = {navController.navigate(screen.booking.name)})
            }
            composable(route = HotelBottomBar.editRoom.route) {
                EditRooms(
                    viewModel = viewModel(factory = EditRoomsViewModelFactory(LocalContext.current))
                )
            }
            composable(route = HotelBottomBar.editBook.route) {
                EditBooking(
                    viewModel = viewModel(factory = EditBookingViewModelFactory(LocalContext.current))
                )
            }
            composable(route = HotelBottomBar.Settings.route) {
//                Settings(viewRoomViewModel)
            }
            composable(route = "reservationCheck/{date}") { backStackEntry ->
                val date = backStackEntry.arguments?.getString("date")
                val selectedDate = SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(date)!!
                ReservationCheckScreen(navController, selectedDate, viewModel)
            }
        }
    }
}
