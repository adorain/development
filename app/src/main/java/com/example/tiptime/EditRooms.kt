
package com.example.tiptime

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditRooms(viewModel: EditRoomsViewModel) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var expanded by remember { mutableStateOf(false) }
    val roomType = arrayOf("Single", "Double", "King")
    var selectedItem by remember { mutableStateOf(roomType[0]) }

    var expandS by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }
    var showStartButtonText by remember { mutableStateOf("Select Start Date") }
    var showEndButtonText by remember { mutableStateOf("Select End Date") }

    val cusPadding = PaddingValues(
        start = 8.dp,
        top = 1.dp,
        end = 8.dp
    )

    Column(
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "HOTELNAME",
            fontSize = 30.sp,
            modifier = Modifier.padding(8.dp)
        )

        Row {
            Column(modifier = Modifier.padding(top = 10.dp)) {
                Text(showStartButtonText, modifier = Modifier.padding(8.dp))
                Text(showEndButtonText, modifier = Modifier.padding(8.dp))
            }

            Column(modifier = Modifier.padding(start = 40.dp)) {
                Button(onClick = { showDialog = true }, modifier = Modifier.padding(8.dp)) {
                    Text("Select Start Date")
                }

                Button(onClick = { showDialog2 = true }, modifier = Modifier.padding(8.dp)) {
                    Text("Select End Date")
                }
            }
        }

        Spacer(modifier = Modifier.padding(top = 8.dp))

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
                            viewModel.fetchRoomsForDateRangeAndType("1", selectedItem, viewModel.selectedStartDate, viewModel.selectedEndDate) // Replace "1" with actual hotel ID
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
            ExposedDropdownMenuBox(expanded = expandS, onExpandedChange = { expandS = true }) {
                TextField(
                    value = viewModel.selectedRoom,
                    onValueChange = { viewModel.selectRoom(it) },
                    label = { Text(text = "Type Room Name Here") },
                    trailingIcon = {
                        if (viewModel.selectedRoom.isNotEmpty()) {
                            IconButton(onClick = {
                                if (viewModel.rooms.map { it.roomId }.contains(viewModel.selectedRoom)) {
                                    viewModel.roomAvailable = true
                                    expandS = false
                                    focusManager.clearFocus()
                                    Toast.makeText(context, "Confirm: ${viewModel.selectedRoom}", Toast.LENGTH_SHORT).show()
                                } else {
                                    viewModel.roomAvailable = false
                                    Toast.makeText(context, "Room Not Available", Toast.LENGTH_SHORT).show()
                                }
                            }) {
                                Icon(Icons.Filled.Check, contentDescription = "Confirm")
                            }
                        }
                    },
                    modifier = Modifier.menuAnchor()
                )

                val filteredOptions = viewModel.rooms.map { it.roomId }.filter { it.contains(viewModel.selectedRoom, ignoreCase = true) }
                if (filteredOptions.isNotEmpty()) {
                    ExposedDropdownMenu(expanded = expandS, onDismissRequest = { expandS = false }) {
                        filteredOptions.forEach { item ->
                            DropdownMenuItem(text = { Text(text = item) }, onClick = {
                                viewModel.selectedRoom = item
                                expandS = false
                            })
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.padding(top = 8.dp))

        // Checkbox options for room status
        CheckboxOption("Available", viewModel.checkAv) {
            viewModel.updateRoomAvailability(RoomStatus.AVAILABLE)
        }
        CheckboxOption("Occupied", viewModel.checkOc) {
            viewModel.updateRoomAvailability(RoomStatus.OCCUPIED)
        }
        CheckboxOption("Under Maintenance", viewModel.checkUnMa) {
            viewModel.updateRoomAvailability(RoomStatus.UNDER_MAINTENANCE)
        }

        Button(onClick = {
            val selectedRoom = viewModel.rooms.find { it.roomId == viewModel.selectedRoom }
            if (selectedRoom != null) {
                selectedRoom.Status = when {
                    viewModel.checkAv -> "Available"
                    viewModel.checkOc -> "Occupied"
                    viewModel.checkUnMa -> "Under Maintenance"
                    else -> selectedRoom.Status
                }
                viewModel.updateRoomStatus(selectedRoom)
            }
        }, modifier = Modifier.padding(cusPadding)) {
            Text("Confirm Edit")
        }
    }

    if (showDialog) {
        showDatePickerForBooking(context = context, date = Date()) { selectedDate ->
            // Update start date and button text
            showDialog = false
            showStartButtonText = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate)
            viewModel.updateStartDate(selectedDate)
        }
    }

    if (showDialog2) {
        showDatePickerForBooking(context = context, date = Date()) { selectedDate ->
            // Update end date and button text
            showDialog2 = false
            showEndButtonText = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate)
            viewModel.updateEndDate(selectedDate)
        }
    }
}

@Composable
fun CheckboxOption(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .height(30.dp)
            .padding(
                start = 8.dp,
                top = 1.dp,
                end = 8.dp
            )
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(label)
    }
}

fun showDatePickerForBooking(context: Context, date: Date, onDateSelected: (Date) -> Unit) {
    val calendar = Calendar.getInstance()
    calendar.time = date

    DatePickerDialog(
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun EditRoomsDt() {
    TipTimeTheme {
        EditRooms(EditRoomsViewModel())
    }
}
