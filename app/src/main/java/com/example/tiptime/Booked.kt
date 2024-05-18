import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tiptime.R
import com.example.tiptime.ui.theme.TipTimeTheme
import com.example.tiptime.viewmodel.BookedViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookedFetch(
    viewModel: BookedViewModel,
    context: Context,
    /*navController: NavController

     */
) {
    val bookings by viewModel.bookings.collectAsState(initial = emptyList())
    val hotels by viewModel.hotels.collectAsState(initial = emptyList())

    Column(Modifier.fillMaxSize()) {
        bookings.forEach { booking ->
            val hotelName = hotels.find { it.HotelId == booking.HotelId }?.HotelName ?: "Unknown Hotel"
            val startDate = booking.BookedStartDate
            val endDate = booking.BookedEndDate
            val dateRange = "Date: $startDate - $endDate"
            val pax = "Pax: ${booking.Pax}"
            val roomType = "Room: ${booking.ROOMTYPE}"
            val days = viewModel.calculateDays(startDate, endDate)
            val fees = "Fees: ${booking.Price * days}"

            val currentDate = LocalDate.now()
            val endLocalDate = LocalDate.parse(endDate)
            val isReviewButton = currentDate.isAfter(endLocalDate)

            Booked(
                hotelName = hotelName,
                dateRange = dateRange,
                pax = pax,
                roomType = roomType,
                fees = fees,
                onDeleteBooking = {
                    // Handle delete booking
                },
                onReview = {
                    // Handle navigation to review screen
                },
                isReviewButton = isReviewButton
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun Booked(
    hotelName: String,
    dateRange: String,
    pax: String,
    roomType: String,
    fees: String,
    onDeleteBooking: () -> Unit,
    onReview: () -> Unit,
    isReviewButton: Boolean
) {
    Column(modifier = Modifier.fillMaxSize().padding(top = 30.dp)) {
        BookingBox(
            hotelName = hotelName,
            dateRange = dateRange,
            pax = pax,
            roomType = roomType,
            fees = fees,
            onDeleteBooking = onDeleteBooking,
            onReview = onReview,
            isReviewButton = isReviewButton
        )
    }
}

@Composable
fun BookingBox(
    hotelName: String,
    dateRange: String,
    pax: String,
    roomType: String,
    fees: String,
    onDeleteBooking: () -> Unit,
    onReview: () -> Unit,
    isReviewButton: Boolean
) {
    Box(
        modifier = Modifier.border(2.dp, Color.Black, RoundedCornerShape(8.dp))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.muda),
                contentDescription = null,
                modifier = Modifier.size(180.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(hotelName, fontSize = 30.sp)
                Text(dateRange)
                Text(pax)
                Text(roomType)
                Text(fees)

                if (isReviewButton) {
                    Button(
                        onClick = onReview,
                        modifier = Modifier.size(100.dp, 40.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                        shape = RoundedCornerShape(0)
                    ) {
                        Text("Review")
                    }

                } else {
                    Button(
                        onClick = onDeleteBooking,
                        modifier = Modifier.size(100.dp, 40.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                        shape = RoundedCornerShape(0)
                    ) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookedDetailsPreview() {
    TipTimeTheme {
        Booked(
            hotelName = "Hotel ABC",
            dateRange = "Date: Jan 1 - Jan 5",
            pax = "Pax: 2",
            roomType = "Room: Suite",
            fees = "Fees: $200",
            onDeleteBooking = {},
            onReview = {},
            isReviewButton = false
        )
    }
}
