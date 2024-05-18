package com.example.tiptime

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tiptime.Data.Booking
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationCheckScreen(navController: NavController, selectedDate: Date, bookingViewModel: BookingViewModel) {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    val dateString = sdf.format(selectedDate)
    val reservations by bookingViewModel.getReservationsForDate(selectedDate).collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reservations for $dateString") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
                reservations.forEach { reservation ->
                    ReservationItem(reservation = reservation, bookingViewModel = bookingViewModel)
                }
            }
        }
    )
}

@Composable
fun ReservationItem(reservation: Booking, bookingViewModel: BookingViewModel) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Room Type: ${reservation.ROOMTYPE}")
            Text(text = "Start Date: ${reservation.BookedStartDate}")
            Text(text = "End Date: ${reservation.BookedEndDate}")
            Text(text = "Pax: ${reservation.Pax}")
            Text(text = "Price: ${reservation.Price}")

            IconButton(
                onClick = {
                    bookingViewModel.deleteBooking(reservation)
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}
