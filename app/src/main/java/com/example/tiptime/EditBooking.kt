package com.example.tiptime

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tiptime.ui.theme.TipTimeTheme

@Composable
fun EditBooking(
    viewModel: EditBookingViewModel= viewModel(factory = EditBookingViewModelFactory(LocalContext.current)),
    hotelId: Int,
    initialHotelName: String,
    initialHotelAddress: String,
    initialRoomTypes: List<String>
) {
    var hotelName by remember { mutableStateOf(initialHotelName) }
    var hotelAddress by remember { mutableStateOf(initialHotelAddress) }
    var roomTypes by remember { mutableStateOf(initialRoomTypes) }
    var currentImageIndex by remember { mutableStateOf(0) }

    val images = listOf(
        painterResource(R.drawable.muda),
        painterResource(R.drawable.single),
        painterResource(R.drawable.king)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row {
            Image(
                painter = images[currentImageIndex],
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clickable {
                        currentImageIndex = (currentImageIndex + 1) % images.size
                    }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Image ${currentImageIndex + 1} of ${images.size}", fontSize = 14.sp)
            IconButton(onClick = { currentImageIndex = (currentImageIndex + 1) % images.size }) {
                Icon(painter = painterResource(R.drawable.next_arrow), contentDescription = "Next Image")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = hotelName,
            onValueChange = { hotelName = it },
            label = { Text("Hotel Name") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = hotelAddress,
            onValueChange = { hotelAddress = it },
            label = { Text("Hotel Address") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        roomTypes.forEachIndexed { index, roomType ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = roomTypes[index],
                    onValueChange = {
                        roomTypes = roomTypes.toMutableList().apply { this[index] = it }
                    },
                    label = { Text("Room Type ${index + 1}") },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { /* Edit Room Type */ }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Room Type")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            OutlinedButton(
                onClick = {
                    viewModel.updateHotelDetails(hotelId, hotelName, hotelAddress, roomTypes)
                },
                modifier = Modifier.size(width = 150.dp, height = 50.dp)
            ) {
                Text(text = "Confirm Edit")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EditBookingPreview() {
    val viewModel: EditBookingViewModel = viewModel() // Assuming you have a ViewModelProvider.Factory set up
    TipTimeTheme {
        EditBooking(
            EditBookingViewModelFactory(LocalContext.current).create(EditBookingViewModel::class.java),
            hotelId = 1,
            initialHotelName = "Hotel XYZ",
            initialHotelAddress = "123 Main St",
            initialRoomTypes = listOf("Single Room", "Double Room", "King Room")
        )
    }
}