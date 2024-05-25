package com.example.tiptime

import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tiptime.ui.theme.TipTimeTheme

@Composable
fun EditBooking(
    viewModel: EditBookingViewModel = viewModel(factory = EditBookingViewModelFactory(LocalContext.current)),
    hotelId:Int =1
) {
    val context = LocalContext.current


    var hotelName by remember { mutableStateOf("") }
    var hotelAddress by remember { mutableStateOf("") }
    var hotelDescription by remember { mutableStateOf("") }
    var roomTypes by remember { mutableStateOf(listOf<String>()) }
    var currentImageIndex by remember { mutableStateOf(0) }

    val images = listOf(
        painterResource(R.drawable.muda),
        painterResource(R.drawable.single),
        painterResource(R.drawable.king)
    )

    LaunchedEffect(hotelId) {
        viewModel.fetchHotelDetails(hotelId)
    }

    val hotelDetails by viewModel.hotelDetails.collectAsState()
    LaunchedEffect(hotelDetails) {
        hotelDetails?.let { (hotel, types) ->
            hotelName = hotel.HotelName
            hotelAddress = hotel.HotelAddress
            hotelDescription = hotel.HotelDescription
            roomTypes = types
            viewModel.fetchRoomPrices(hotelId, types)
        }
    }

    val roomPrices by viewModel.roomPrices.collectAsState()
    val editablePrices = remember { mutableStateMapOf<String, Boolean>() }
    val priceValues = remember { mutableStateMapOf<String, String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Make the column scrollable
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = images[currentImageIndex],
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    currentImageIndex =
                        if (currentImageIndex == 0) images.size - 1 else currentImageIndex - 1
                }) {
                    Icon(
                        painter = painterResource(R.drawable.prevarr),
                        contentDescription = "Previous Image"
                    )
                }
            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    currentImageIndex = (currentImageIndex + 1) % images.size
                }) {
                    Icon(
                        painter = painterResource(R.drawable.nextarr),
                        contentDescription = "Next Image"
                    )
                }
            }
            Text(
                text = "${currentImageIndex + 1} / ${images.size}",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
                fontSize = 16.sp
            )
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
            value = hotelDescription,
            onValueChange = { hotelDescription = it },
            label = { Text("Hotel Description") },
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
            val price = roomPrices[roomType] ?: 0.0
            var priceText by remember { mutableStateOf(price.toString()) }
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
                Spacer(modifier = Modifier.width(8.dp))
                if (editablePrices[roomType] == true) {
                    TextField(
                        value = priceText,
                        onValueChange = { priceText = it },
                        label = { Text("Price") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        singleLine = true,
                        modifier = Modifier.width(80.dp),
                        keyboardActions = KeyboardActions(onDone = {
                            editablePrices[roomType] = false
                            priceValues[roomType] = priceText
                        })
                    )
                } else {
                    Text(
                        text = "\$${priceValues[roomType] ?: price}",
                        color = Color.Green,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable {
                                editablePrices[roomType] = true
                            }
                    )
                }
                IconButton(onClick = { editablePrices[roomType] = true }) {
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
                    if (hotelName.isBlank() || hotelAddress.isBlank() || hotelDescription.isBlank() || roomTypes.any { it.isBlank() }) {
                        Toast.makeText(context, "Data cannot be null", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.updateHotelDetails(
                            hotelId,
                            hotelName,
                            hotelAddress,
                            hotelDescription,
                            roomTypes
                        )
                        Toast.makeText(context, "Data uploaded", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.size(width = 150.dp, height = 50.dp)
            ) {
                Text(text = "Confirm Edit")
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Confirm Edit")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditBookingPreview() {
    TipTimeTheme {
        EditBooking(
            EditBookingViewModelFactory(LocalContext.current).create(EditBookingViewModel::class.java),
            hotelId = 1
        )
    }
}

