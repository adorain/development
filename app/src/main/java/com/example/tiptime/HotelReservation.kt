import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tiptime.BookingViewModel
import com.example.tiptime.Data.Booking
import com.example.tiptime.hotelViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelReservation(navController: NavController, hotelViewModel: hotelViewModel, bookingViewModel: BookingViewModel) {
    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf(Date()) }
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Hotel Reservation") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                addBooking(
                    bookingViewModel,
                    1,
                    "Standard",
                    sdf.format(selectedDate),
                    sdf.format(Date(selectedDate.time + 86400000)),
                    1,
                    100.0
                )
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Reservation")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
                Text(text = sdf.format(Date()), modifier = Modifier.padding(bottom = 8.dp))

                Button(
                    onClick = {
                        showDatePicker(context, selectedDate) { date ->
                            selectedDate = date
                            hotelViewModel.updateStartDate(sdf.format(date))
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Select Date: ${sdf.format(selectedDate)}")
                }

                Button(
                    onClick = {
                        val dateString = sdf.format(selectedDate)
                        navController.navigate("reservationCheck/$dateString")
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text("Check Reservations")
                }
            }
        }
    )
}

fun showDatePicker(context: Context, date: Date, onDateSelected: (Date) -> Unit) {
    val calendar = Calendar.getInstance()
    calendar.time = date
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            onDateSelected(calendar.time)
        },
        year,
        month,
        day
    ).show()
}

fun addBooking(
    bookingViewModel: BookingViewModel,
    hotelId: Int,
    roomType: String,
    bookedStartDate: String,
    bookedEndDate: String,
    pax: Int,
    price: Double
) {
    bookingViewModel.addNewBooking(
        Booking(
            Booked_id = 0,  // Assuming 0 for a new booking
            HotelId = hotelId,
            ROOMTYPE = roomType,
            BookedStartDate = bookedStartDate,
            BookedEndDate = bookedEndDate,
            Pax = pax,
            Status = "unpaid",
            Price = price
        )
    )
}


@Composable
fun ReservationCheckScreen(navController: NavController, selectedDate: Date) {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    val dateString = sdf.format(selectedDate)

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Reservations for $dateString")
        // Implement the logic to fetch and display reservations
        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Back to Reservation Screen")
        }
    }
}
