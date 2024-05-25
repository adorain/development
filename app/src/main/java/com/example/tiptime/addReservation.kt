package com.example.tiptime

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AddReservation(
    navController: NavController,
    viewModel: BookingViewModel = viewModel(factory = AppViewModelProvider.factory)
) {
    val context = LocalContext.current
    val hotelId = viewModel.hotel_Id.toString()
    val userId = ""

    val roomTypes = listOf("King Room", "Single Room", "Double Room")
    var selectedRoomType by remember { mutableStateOf(roomTypes[0]) }

    val (startDate, setStartDate) = remember { mutableStateOf("") }
    val (endDate, setEndDate) = remember { mutableStateOf("") }
    val (pax, setPax) = remember { mutableStateOf(TextFieldValue("")) }
    val (price, setPrice) = remember { mutableStateOf(TextFieldValue("")) }

    fun showDatePicker(context: Context, onDateSelected: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                onDateSelected(dateFormat.format(selectedDate.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Hotel ID: $hotelId")
        Spacer(modifier = Modifier.height(8.dp))

        Text("Select Room Type")
        roomTypes.forEach { roomType ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { selectedRoomType = roomType }
            ) {
                RadioButton(
                    selected = (selectedRoomType == roomType),
                    onClick = { selectedRoomType = roomType }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = roomType)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                showDatePicker(context) { selectedDate -> setStartDate(selectedDate) }
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text("Select Start Date: $startDate")
        }

        Button(
            onClick = {
                showDatePicker(context) { selectedDate -> setEndDate(selectedDate) }
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text("Select End Date: $endDate")
        }

        OutlinedTextField(
            value = pax,
            onValueChange = {
                if (it.text.all { char -> char.isDigit() }) {
                    setPax(it)
                }
            },
            label = { Text("Pax") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = price,
            onValueChange = {
                if (it.text.all { char -> char.isDigit() || char == '.' }) {
                    setPrice(it)
                }
            },
            label = { Text("Price") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            onClick = {
                viewModel.hotel_Id = hotelId.toInt()
                viewModel.roomtype = selectedRoomType
                viewModel.BookedStartDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(startDate)!!
                viewModel.BookedEndDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(endDate)!!
                viewModel.Pax = pax.text.toInt()
                viewModel.Price = price.text.toDouble()
                viewModel.updateUserId(userId)
                viewModel.addNewBooking()
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Add Reservation")
        }
    }
}
