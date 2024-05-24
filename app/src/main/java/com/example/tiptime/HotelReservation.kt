import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.tiptime.BookingViewModel
import com.example.tiptime.Data.Booking
import com.example.tiptime.hotelViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelReservation(
    navController: NavHostController,
    viewModelHotel: hotelViewModel,
    bookingViewModel: BookingViewModel,
    onNextButton: () -> Unit
) {
    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf(Date()) }
    var showDate by remember { mutableStateOf(false) }
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    var formattedDate by remember { mutableStateOf(sdf.format(selectedDate)) }

    if (showDate) {
        showDatePicker2(context = context, date = selectedDate) { date ->
            showDate = false
            selectedDate = date
            formattedDate = sdf.format(selectedDate)
            viewModelHotel.updateStartDate(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate))
        }
    }

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
                onNextButton()
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
fun ReservationCheckScreen(navController: NavHostController, selectedDate: Date, bookingViewModel: BookingViewModel) {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    val dateString = sdf.format(selectedDate)

    // Define the reservations manually
    val reservations = listOf(
        Booking(
            Booked_id = 1,
            HotelId = 1,
            ROOMTYPE = "Single",
            BookedStartDate = "23/5/2024",
            BookedEndDate = "23/5/2024",
            Pax = 2,
            Status = "unpaid",
            Price = 100.0,
//            userName = "Alice Johnson",
//            userPhoneNumber = "555-1234"
        ),
        Booking(
            Booked_id = 2,
            HotelId = 1,
            ROOMTYPE = "Double",
            BookedStartDate = "23/5/2024",
            BookedEndDate = "23/5/2024",
            Pax = 3,
            Status = "paid",
            Price = 150.0,
//            userName = "Bob Smith",
//            userPhoneNumber = "555-5678"
        ),
        Booking(
            Booked_id = 3,
            HotelId = 1,
            ROOMTYPE = "Suite",
            BookedStartDate = "23/5/2024",
            BookedEndDate = "23/5/2024",
            Pax = 4,
            Status = "paid",
            Price = 300.0,
//            userName = "Charlie Brown",
//            userPhoneNumber = "555-9101"
        )
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Reservations in date: $dateString", modifier = Modifier.padding(bottom = 16.dp))

        if (reservations.isEmpty()) {
            Text("No reservations found for this date.", modifier = Modifier.padding(8.dp))
        } else {
            reservations.forEach { reservation ->
                ReservationCard(reservation)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Back to Reservation Screen")
        }
    }
}

@Composable
fun ReservationCard(reservation: Booking) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
//            Text("User Name: ${reservation.userName}")
//            Text("Phone Number: ${reservation.userPhoneNumber}")
            Text("Pax: ${reservation.Pax}")
            Text("Room Type: ${reservation.ROOMTYPE}")
            Text("Start Date: ${reservation.BookedStartDate}")
            Text("End Date: ${reservation.BookedEndDate}")
        }
    }
}