package com.example.tiptime

import android.content.Context
import android.text.style.BackgroundColorSpan
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.DateFormat
import com.example.tiptime.SqlliteManagement.BookingDb



@Composable
fun Booked(
    hotelName: String,
    dateRange: String,
    pax: String,
    roomType: String,
    fees: String,
    onDeleteBooking: () -> Unit
) {
    Column (modifier=Modifier.fillMaxSize()
        .padding(top = 30.dp)){
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


@Composable
fun BookedFetch(context: Context, bookingId: String) {

    val bookingDb = BookingDb(context)

    val booking = bookingDb.getBookingById(bookingId)


    if (booking != null) {
        val hotelName = "Hotel Name: ${booking.HotelId}"
        val startDate = DateFormat.getDateInstance().format(booking.BookedStartDate)
        val endDate = DateFormat.getDateInstance().format(booking.BookedEndDate)
        val dateRange = "Date: $startDate - $endDate"
        val pax = "Pax: ${booking.Pax}"
        val roomType = "Room: ${booking.ROOMTYPE}"
        val fees = "Fees: ${booking.Price}"


        Booked(
            hotelName = hotelName,
            dateRange = dateRange,
            pax = pax,
            roomType = roomType,
            fees = fees,
            onDeleteBooking = {
                val isSuccess = bookingDb.deleteBookingById(bookingId)
                if (isSuccess) {
                    Toast.makeText(context, "Booking canceled successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed to cancel booking", Toast.LENGTH_SHORT).show()
                }
            }
        )
    } else {
        Text("Booking not found.")
    }
}






@Preview(showBackground = true)
@Composable
fun BookedDetails(){
    TipTimeTheme {
        Booked(
            hotelName = "Hotel ABC",
            dateRange = "Date: Jan 1 - Jan 5",
            pax = "Pax: 2",
            roomType = "Room: Suite",
            fees = "Fees: $200",
            onDeleteBooking = {}
        )
    }
}


