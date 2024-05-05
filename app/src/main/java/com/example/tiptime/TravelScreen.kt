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
import com.example.tiptime.Data.room
import com.example.tiptime.ui.theme.TipTimeTheme
import java.util.Date

enum class screen{
    home , booking , detail, summary,payment
}
@Composable
fun TravelApp(
    viewModel: BookingViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    Scaffold(

    ) { innerPadding ->
        val uiState by viewModel.uiBookingState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = screen.booking.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = screen.home.name){
                HomeScreen (
                    onSelectedHotel = {
                        viewModel.setHotelId(it)
                        navController.navigate(screen.booking.name)},

                )
            }
            composable( route = screen.booking.name){
                booking(
                    onNextButtonClicked = {
                        navController.navigate(screen.detail.name) },
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(navController)},
                    "",
                    "",
                    "",
                    ""
                )
            }
            composable( route = screen.detail.name){
                bookingDetails(

                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(navController)},
                    onNextButtonClicked = {



                        //viewModel.setHotelId(it)
                        //viewModel.setRoomType(it)
                        navController.navigate(screen.summary.name)
                    },
                    OnBookingStartDateChange ={viewModel.updateBookingStartDate(it)} ,
                    OnBookingEndDateChange = {viewModel.updateBookingEndDate(it)},
                    OnPaxChange ={viewModel.updatePax(it)} ,
                    HotelId = "",
                    BookingStartDate = viewModel.setBookingStartDate(),
                    BookingEndDate = viewModel.setBookingEndDate(),
                    Price = viewModel.calculatePrice(),
                    pax = viewModel.setPax(),
                    roomType = "",
                )
            }
            composable(route = screen.summary.name){
                bookingSummary(
                    onNextButtonClicked ={navController.navigate(screen.payment.name)},
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(navController)},
                    BookingStartDate = uiState.BookedStartDate,
                    BookingEndDate = uiState.BookedEndDate,
                    Price = uiState.Price,
                    pax = uiState.Pax,
                    roomType = ""
                )
            }
            composable( route = screen.payment.name){
                PaymentLayout(
                    booking = Booking(Booked_id = "",
                        BookedStartDate = uiState.BookedStartDate,
                        BookedEndDate = uiState.BookedEndDate,
                        Pax = uiState.Pax,
                        Status = uiState.Status,
                        ROOMTYPE = "",
                        Price = 0.00,
                        HotelId = ""
                    ),
                    onClickedButton = {(navController.navigate(screen.booking.name))}
                )
            }
        }

    }
}

private fun cancelOrderAndNavigateToStart(
    navController: NavController
){

    navController.popBackStack(screen.booking.name , inclusive = false)
}
