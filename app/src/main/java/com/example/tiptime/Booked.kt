import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tiptime.BookedViewModelFactory
import com.example.tiptime.R
import com.example.tiptime.ui.theme.TipTimeTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Booked(
    userId:String,
    viewModel: BookedViewModel = viewModel(factory = BookedViewModelFactory(LocalContext.current,userId))
) {
    val context = LocalContext.current
    val bookings by viewModel.bookings.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)
        .verticalScroll(rememberScrollState()) // Make the column scrollable
    ) {
        Text("Your Bookings", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        bookings.forEach { (booking, hotel) ->
            val hotelName = "Hotel Name: ${hotel.HotelName}"
            val dateRange = "Date: ${booking.BookedStartDate} - ${booking.BookedEndDate}"
            val pax = "Pax: ${booking.Pax}"
            val roomType = "Room: ${booking.ROOMTYPE}"
            val totalPrice = viewModel.calculateTotalPrice(booking)
            val fees = "Fees: $totalPrice"

            val currentDate = LocalDate.now()
            val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val endLocalDate = LocalDate.parse(booking.BookedEndDate, dateFormatter)
            val isReviewButton = currentDate.isAfter(endLocalDate) || booking.Status == "Canceled"

            BookingItem(
                hotelName = hotelName,
                dateRange = dateRange,
                pax = pax,
                roomType = roomType,
                fees = fees,
                onCancelClick = {
                    viewModel.cancelBooking(booking.Booked_id)
                    Toast.makeText(context, "Booking Cancelled", Toast.LENGTH_SHORT).show()
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingItem(
    hotelName: String,
    dateRange: String,
    pax: String,
    roomType: String,
    fees: String,
    onCancelClick: () -> Unit,

    ) {
    var isCancelled by remember { mutableStateOf(true) }


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

                Button(
                    onClick = {

                            onCancelClick()
                            isCancelled = false





                    },
                    modifier = Modifier.size(150.dp, 40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = if (isCancelled) Color.Green else Color.Blue),
                    enabled=isCancelled,
                    shape = RoundedCornerShape(0)
                ) {
                    Text(if (isCancelled) "Cancel" else "Cancelled")
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun BookedDetailsPreview() {
    TipTimeTheme {
        BookingItem(
            hotelName = "Hotel ABC",
            dateRange = "Date: Jan 1 - Jan 5",
            pax = "Pax: 2",
            roomType = "Room: Suite",
            fees = "Fees: $200",
            onCancelClick = {}
        )
    }
}