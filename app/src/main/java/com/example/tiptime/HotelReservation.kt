package com.example.tiptime

import HotelTopBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun HotelReservation() {
    var currentPage by remember { mutableStateOf("Reservation") }

//    val hotels = hotelDb.getHotelDetails("YourHotelAddressHere", Date(), Date(), 1)

    Scaffold(
        topBar = {
            HotelTopBar(
                currentScreen = currentPage
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = "Click to modify the reservation.")
//                Text(text = "Welcome, ${hotels[0].HotelName}!")
            }

        }
    }
}