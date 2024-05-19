package com.example.tiptime

import EditRoomsViewModelFactory
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditRooms(viewModel: EditRoomsViewModel = viewModel(factory = EditRoomsViewModelFactory(LocalContext.current))) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val rooms by viewModel.rooms.collectAsState(initial = emptyList())
    val selectedRoom by viewModel.selectedRoom.collectAsState(initial = null)
    val hotelName by viewModel.hotelName.collectAsState(initial = "")


    var expanded by remember { mutableStateOf(false) }
    val roomType = arrayOf("Single", "Double", "King")
    var selectedItem by remember { mutableStateOf(roomType[0]) }

    var expandS by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }
    var startButtonText by remember { mutableStateOf("Select Start Date") }
    var endButtonText by remember { mutableStateOf("Select End Date") }




    val cusPadding = PaddingValues(
        start = 8.dp,
        top = 1.dp,
        end = 8.dp
    )

    LaunchedEffect(Unit) {
        viewModel.fetchHotelName(1) // Replace "1" with actual hotel ID
    }

    Column(
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxSize()
    ) {
        Text(
            text = hotelName,
            fontSize = 30.sp,
            modifier = Modifier.padding(8.dp)
        )


        Row {
            Column(modifier = Modifier.padding(top = 15.dp,
                start = 16.dp)) {
                Text("Start Date:")
                Text("End Date  :", modifier = Modifier.padding(top = 20.dp))
            }

            Column(modifier = Modifier.padding(start = 30.dp)) {
                Button(onClick = { showDialog = true },modifier = Modifier.border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RectangleShape
                )
                    .padding(cusPadding),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    shape = RoundedCornerShape(0)
                ) {
                    Text(startButtonText ,color = Color.Black)
                }

                Button(onClick = { showDialog2 = true },modifier = Modifier.border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RectangleShape
                ) .padding(cusPadding),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    shape = RoundedCornerShape(0)) {
                    Text(endButtonText, color = Color.Black)
                }
            }
        }

        Spacer(modifier = Modifier.padding(top = 8.dp))

        // Inside the Row where TextField for roomId is defined

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedItem,
                onValueChange = { selectedItem = it },
                readOnly = true,
                label = { Text(text = "RoomType") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
                    .padding(cusPadding)
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                roomType.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(text = selectionOption) },
                        onClick = {
                            selectedItem = selectionOption
                            expanded = false
                            viewModel.fetchRoomsForDateRangeAndType(1, selectedItem, viewModel.selectedStartDate, viewModel.selectedEndDate) // Replace "1" with actual hotel ID
                        }
                    )
                }
            }
        }



        Spacer(modifier = Modifier.padding(top = 8.dp))


        Text("Available: ${viewModel.availableRooms}", modifier = Modifier.padding(cusPadding))
        Text("Occupied: ${viewModel.occupiedRooms}", modifier = Modifier.padding(cusPadding))
        Text("Under Maintenance: ${viewModel.underMaintenanceRooms}", modifier = Modifier.padding(cusPadding))

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(cusPadding),
            color = Color.Black,
            thickness = 1.dp
        )

        Text("Choose room to modify status:", modifier = Modifier.padding(8.dp))

        Surface(
            modifier = Modifier
                .padding(cusPadding)
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopStart)
        ) {
            ExposedDropdownMenuBox(expanded = expandS, onExpandedChange = { expandS = !expandS }) {
                TextField(
                    value = selectedRoom?.roomId?.toString() ?: "",
                    onValueChange = { viewModel.selectRoom(it.toIntOrNull() ?: 0, clearCheckboxes = true) },
                    label = { Text(text = "Type Room Name Here") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        if (rooms.map { it.roomId }.contains(selectedRoom?.roomId)) {
                            expandS = false
                            focusManager.clearFocus()
                            Toast.makeText(context, "Confirm: ${selectedRoom?.roomId}", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Room Not Available", Toast.LENGTH_SHORT).show()
                        }
                    }),
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expandS,
                    onDismissRequest = { expandS = false }
                ) {
                    val filteredOptions = rooms.map { it.roomId.toString() }
                        .filter { it.contains(selectedRoom?.roomId?.toString() ?: "", ignoreCase = true) }
                    filteredOptions.forEach { item ->
                        DropdownMenuItem(text = { Text(text = item) }, onClick = {
                            viewModel.selectRoom(item.toIntOrNull() ?: 0, clearCheckboxes = true)
                            expandS = false
                        })
                    }
                }
            }
        }


        Spacer(modifier = Modifier.padding(top = 8.dp))

        CheckboxOption("Available", viewModel.checkAv) {
            viewModel.updateRoomAvailability(RoomStatus.AVAILABLE, it)
        }
        CheckboxOption("Occupied", viewModel.checkOc) {
            viewModel.updateRoomAvailability(RoomStatus.OCCUPIED, it)
        }
        CheckboxOption("Under Maintenance", viewModel.checkUnMa) {
            viewModel.updateRoomAvailability(RoomStatus.UNDER_MAINTENANCE, it)
        }

        Button(onClick = {
            val isStatusValid = viewModel.validateRoomStatus()
            if (isStatusValid) {
                selectedRoom?.let {
                    it.Status = when {
                        viewModel.checkAv -> "Available"
                        viewModel.checkOc -> "Occupied"
                        viewModel.checkUnMa -> "Under Maintenance"
                        else -> it.Status
                    }
                    viewModel.updateRoomStatus(it)
                    Toast.makeText(context, "Status updated", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Error: Status cannot be null or multiple", Toast.LENGTH_SHORT).show()
            }
        }, modifier = Modifier.padding(cusPadding)) {
            Text("Confirm Edit")
        }
    }

    if (showDialog) {
        showDatePickerForBooking(context = context, date = Date()) { selectedDate ->
            // Update start date and button text
            showDialog = false
            startButtonText = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate)
            viewModel.updateStartDate(selectedDate)
        }
    }

    if (showDialog2) {
        showDatePickerForBooking(context = context, date = Date()) { selectedDate ->
            // Update end date and button text
            showDialog2 = false
            endButtonText = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate)
            viewModel.updateEndDate(selectedDate)
        }
    }
}


@Composable
fun CheckboxOption(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(label)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun EditRoomsPreview() {
    TipTimeTheme {
        EditRooms(EditRoomsViewModelFactory(LocalContext.current).create(EditRoomsViewModel::class.java))
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun showDatePickerForBooking(context: Context, date: Date, onDateSelected: (Date) -> Unit) {
    val calendar = Calendar.getInstance()
    calendar.time = date

    android.app.DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            onDateSelected(calendar.time)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}