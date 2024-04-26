package com.example.tiptime

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.Data.Booking
import com.example.tiptime.ui.theme.TipTimeTheme
import javax.sql.DataSource


@Composable
fun bookingDetails(booking: Booking
                   ,onNextButtonClicked:() -> Unit = {},
                   onCancelButtonClicked : () -> Unit = {}
) {
    Column {
        Row(
        ) {
            Image(
                painter = painterResource(R.drawable.nitro_wallpaper_02_3840x2400),
                contentDescription = null
            )
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp, start = 10.dp),
        verticalArrangement = Arrangement.Center,



    ) {
        Row(
        ) {
            Column {
                Text(text = "Booking Id : ", fontSize = 21.sp)
            }
            Column {
                Text(text = booking.Booked_id, fontSize = 21.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column {
                Text(text = "Hotel Id : ", fontSize = 21.sp)
            }
            Column {
                Text(text = booking.HotelId, fontSize = 21.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column {
                Text(text = "Booking Start Date : ", fontSize = 21.sp)
            }
            Column {
                Text(text = booking.BookedStartDate, fontSize = 21.sp)
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column {
                Text(text = "Booking End Date : ", fontSize = 21.sp)
            }
            Column {
                Text(text = booking.BookedEndDate, fontSize = 21.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column {
                Text(text = "Status : ", fontSize = 21.sp)
            }
            Column {
                Text(text = booking.Status, fontSize = 21.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column {
                Text(text = "Total Price : ", fontSize = 21.sp)
            }
            Column {
                Text(text = booking.Price.toString(), fontSize = 21.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Divider(modifier = Modifier.padding(start = 2.dp, top = 20.dp,end = 10.dp), thickness = 3.dp)
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier.padding(start = 40.dp)
        ) {

            OutlinedButton(
                onClick = onCancelButtonClicked,
                modifier = Modifier.padding(end = 100.dp).size(width = 100.dp, height = 50.dp)
            ) {
                Text(text = "Cancel")
            }
            OutlinedButton(
                onClick = onNextButtonClicked,
                modifier = Modifier.size(width = 100.dp, height = 50.dp)
            ) {
                Text(text = "Pay")
            }

        }
    }
}

@Preview
@Composable
fun BookingDetails() {
    val booking = Booking(
        "12345",
        "Hotel123",
        "2024-04-25",
        "2024-04-28",
        "Confirmed",
        0.00
    )
    TipTimeTheme {
        bookingDetails(booking = booking, onCancelButtonClicked = {}, onNextButtonClicked = {})
    }
}