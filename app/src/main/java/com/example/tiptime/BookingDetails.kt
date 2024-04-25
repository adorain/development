package com.example.tiptime

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiptime.Data.Booking
import com.example.tiptime.ui.theme.TipTimeTheme
import javax.sql.DataSource


@Composable
fun bookingDetails(booking: Booking){
    Column {
        Row (
        ){
            Image(painter = painterResource(R.drawable.nitro_wallpaper_02_3840x2400), contentDescription = null )
        }

    }

    Column (
        modifier = Modifier.fillMaxSize().padding(top = 110.dp),
        verticalArrangement = Arrangement.Center

    ){
        Row {
            Column {
                Text(text = "Booking Id")
            }
            Column {
                Text(text = booking.Booked_id)
            }
        }
        Row {
            Column {
                Text(text = "Hotel Id")
            }
            Column {
                Text(text = booking.HotelId)
            }
        }
        Row {
            Column {
                Text(text = "Booking Start Date")
            }
            Column {
                Text(text = booking.BookedStartDate)
            }

        }

        Row {
            Column {
                Text(text = "Booking End Date")
            }
            Column {
                Text(text = booking.BookedEndDate)
            }
        }

        Row {
            Column {
                Text(text = "Status")
            }
            Column {
                Text(text = booking.Status)
            }
        }
        Row {
            Column {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Cancel")
                }
            }
            Column {
                Button(onClick = { /*TODO*/ }) {
                    Text(text =  "Next")
                }
            }
        }
        Row {
            Divider(modifier = Modifier.padding(start = 2.dp , top = 290.dp),thickness = 3.dp)
        }
    }
}
@Preview
@Composable
fun BookingDetails(){
    val booking = Booking(
        "12345",
        "Hotel123",
        "2024-04-25",
        "2024-04-28",
        "Confirmed"
    )
    TipTimeTheme {
        bookingDetails(booking = booking)
    }
}