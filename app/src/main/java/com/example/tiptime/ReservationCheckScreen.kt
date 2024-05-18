import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tiptime.Data.Booking
import com.example.tiptime.hotelViewModel
import kotlinx.coroutines.launch
import java.util.Date

@Composable
fun ReservationCheckScreen(
    selectedStartDate: Date,
    selectedEndDate: Date,
    hotelName: String,
    viewModel: hotelViewModel = viewModel()
) {
    var reservations by remember { mutableStateOf(listOf<Booking>()) }
    val coroutineScope = rememberCoroutineScope()

    // Fetch reservations when the screen is loaded
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            reservations = viewModel.getBookingsForDate(hotelName, selectedStartDate, selectedEndDate)
        }
    }

    Scaffold(
        topBar = {
            HotelTopBar(
                currentScreen = "Reservations"
            )
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                if (reservations.isEmpty()) {
                    Text("No reservations found for the selected date.")
                } else {
                    LazyColumn {
                        items(reservations) { booking ->
                            Text(text = "Booking ID: ${booking.Booked_id}")
                            Text(text = "Hotel ID: ${booking.HotelId}")
                            Text(text = "Room Type: ${booking.ROOMTYPE}")
                            Text(text = "Start Date: ${booking.BookedStartDate}")
                            Text(text = "End Date: ${booking.BookedEndDate}")
                            Text(text = "Status: ${booking.Status}")
                        }
                    }
                }
            }
        }
    )
}
