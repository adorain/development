
package com.example.tiptime

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun EditRooms(viewModel: EditRoomsViewModel){
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var expanded by remember { mutableStateOf(false) }
    val items = arrayOf("Item 1", "Item 2", "Item 3")
    var selectedItem by remember { mutableStateOf(items[0]) }

    var expandS by remember { mutableStateOf( false) }
    val rooms = arrayOf("Room 101","Room 102","Room 201")

    var showDialog by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }
    var showStartButtonText by remember { mutableStateOf("Select start Date") }
    var showEndButtonText by remember { mutableStateOf("Select end Date") }

    val cusPadding = PaddingValues(
        start = 8.dp,
        top = 1.dp,
        end =8.dp
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        /*
        PLACE CALENDAR STARTDATE HERE
         */
        Row {
            Column(modifier = Modifier.padding(top = 10.dp)) {
                Text(text = "Booking Start Date:", modifier = Modifier.padding(cusPadding))
            }
            Column {
                Button(
                    onClick = { showDialog = true },
                    modifier = Modifier.border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RectangleShape
                    ),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    shape = RoundedCornerShape(0)
                ) {
                    Text(showStartButtonText, color = Color.Black)
                }
            }
        }

// Inside the Column where you want the end date button
        Row {
            Column(modifier = Modifier.padding(top = 10.dp)) {
                Text(text = "Booking End Date:", modifier = Modifier.padding(cusPadding))
            }
            Column {
                Button(
                    onClick = { showDialog2 = true },
                    modifier = Modifier.border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RectangleShape
                    ),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    shape = RoundedCornerShape(0)
                ) {
                    Text(showEndButtonText, color = Color.Black)
                }
            }
        }

        /*
        PLACE CALENDAR ENDDATE HERE
         */


        Spacer(modifier = Modifier.height(8.dp))

        // UI code for RoomDis
        Text(
            text = "HOTELNAME",
            fontSize = 30.sp,
            modifier = Modifier.padding(8.dp)
        )
        // UI code for selecting room type
        // Implementation not shown for brevity

        Text("Choose Room Type:",modifier=Modifier.padding(cusPadding))
        Surface (
            modifier = Modifier
                .padding(cusPadding)
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopStart)) {

            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = true }) {
                TextField(
                    value = selectedItem,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    items.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedItem = item
                                expanded = false
                                Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
        }

        Text("Available:",modifier=Modifier.padding(cusPadding))
        Text("Occupied:",modifier=Modifier.padding(cusPadding))
        Text("Under Maintenance:",modifier=Modifier.padding(cusPadding))

        Divider(modifier= Modifier
            .fillMaxWidth()
            .padding(cusPadding),
            color = Color.Black,
            thickness = 1.dp)

        Text("Choose room to modify status:", modifier = Modifier.padding(8.dp))
        // UI code for selecting room and its status
        // Implementation not shown for brevity

        Surface (
            modifier = Modifier
                .padding(cusPadding)
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopStart)) {

            ExposedDropdownMenuBox(expanded = expandS, onExpandedChange = { expandS = true }) {
                TextField(
                    value = viewModel.selectedRoom,
                    onValueChange = { viewModel.selectRoom (it)},
                    label={ Text(text = "Type Room Name Here")},
                    trailingIcon = {
                        if ( viewModel.selectedRoom.isNotEmpty()) {
                            IconButton(onClick = {
                                if (rooms.contains( viewModel.selectedRoom)) {
                                    viewModel.roomAvailable = true
                                    // Handle confirm action here
                                    expandS = false
                                    focusManager.clearFocus()
                                    Toast.makeText(context,"Confirm: ${viewModel.selectedRoom}",
                                        Toast.LENGTH_SHORT).show()
                                } else {
                                    viewModel.roomAvailable = false
                                    Toast.makeText(context, "Room Not Available", Toast.LENGTH_SHORT).show()
                                }
                            }) {
                                Icon(Icons.Filled.Check, contentDescription = "Confirm")
                            }
                        } else {
                            null
                        }
                    },
                    modifier = Modifier.menuAnchor()
                )

                val filteredOptions = rooms.filter{it.contains(viewModel.selectedRoom,ignoreCase = true)}
                if (filteredOptions.isNotEmpty()){
                    ExposedDropdownMenu(expanded = expandS, onDismissRequest = {}) {
                        filteredOptions.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {
                                    viewModel.selectedRoom = item
                                    expandS = false
                                }
                            )
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
    }

    if (showDialog) {
        showDatePickerForBooking(context = context, date = Date()) { selectedDate ->
            // Update start date and button text
            showDialog = false
            showStartButtonText = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate)
            // Handle start date selection
        }
    }

    if (showDialog2) {
        showDatePickerForBooking(context = context, date = Date()) { selectedDate ->
            // Update end date and button text
            showDialog2 = false
            showEndButtonText = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate)
            // Handle end date selection
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
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            val selectedDate = calendar.time
            onDateSelected(selectedDate)
        },
        year,
        month,
        day
    )

    datePickerDialog.show()
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun EditRoomsDt() {
    TipTimeTheme {
        EditRooms(EditRoomsViewModel())
    }
}