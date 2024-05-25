package com.example.tiptime

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tiptime.Data.Booking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelReservation(
    navController: NavHostController,
    viewModelHotel: hotelViewModel,
    bookingViewModel: BookingViewModel
) {
    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf(Date()) }
    var showDate by remember { mutableStateOf(false) }
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    var formattedDate by remember { mutableStateOf(sdf.format(selectedDate)) }
    val coroutineScope = rememberCoroutineScope()
    var reservations by remember { mutableStateOf<List<Booking>>(emptyList()) }

    if (showDate) {
        showDatePicker2(context = context, date = selectedDate) { date ->
            showDate = false
            selectedDate = date
            formattedDate = sdf.format(selectedDate)
            viewModelHotel.updateStartDate(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate))

            coroutineScope.launch {
                val dateString = SimpleDateFormat("dd/MM/yyyy", Locale.US).format(selectedDate)
                withContext(Dispatchers.IO) {
                    reservations = bookingViewModel.getReservationsForDate(selectedDate)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Hotel Reservation") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(screen.addreservation.name)
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Reservation")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
                Text(text = formattedDate, modifier = Modifier.padding(bottom = 8.dp))

                Button(
                    onClick = { showDate = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Select Date: $formattedDate")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Reservations for $formattedDate:", modifier = Modifier.padding(vertical = 8.dp))

                if (reservations.isEmpty()) {
                    Text("No reservations found for this date.", modifier = Modifier.padding(8.dp))
                } else {
                    reservations.forEach { reservation ->
                        ReservationItem(reservation = reservation, bookingViewModel = bookingViewModel)
                    }
                }
            }
        }
    )
}

@Composable
fun showDatePicker2(context: Context, date: Date, onDateSelected: (Date) -> Unit) {
    val calendar = Calendar.getInstance()
    calendar.time = date
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            calendar.set(selectedYear, selectedMonth, selectedDayOfMonth)
            onDateSelected(calendar.time)
        },
        year,
        month,
        day
    ).show()
}

@Composable
fun ReservationItem(reservation: Booking, bookingViewModel: BookingViewModel) {
    val coroutineScope = rememberCoroutineScope()

    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Room Type: ${reservation.ROOMTYPE}")
            Text(text = "Start Date: ${reservation.BookedStartDate}")
            Text(text = "End Date: ${reservation.BookedEndDate}")
            Text(text = "Pax: ${reservation.Pax}")
            Text(text = "Price: ${reservation.Price}")

            IconButton(
                onClick = {
                    coroutineScope.launch(Dispatchers.IO) {
                        bookingViewModel.deleteBooking(reservation)
                    }
                },
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}
