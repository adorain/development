@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.tiptime




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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TipTimeTheme
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarPicker(onDateSelected: (LocalDate) -> Unit) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    // Mutable state to control the expansion of the month dropdown menu
    var isMonthDropdownExpanded by remember { mutableStateOf(false) }

    // Mutable state to control the expansion of the year dropdown menu
    var isYearDropdownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp)
            .widthIn(max = 309.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()

        ) {
            // Month Dropdown
            val months = Month.values().map { it.getDisplayName(TextStyle.SHORT, Locale.getDefault()) }
            var selectedMonthIndex by remember { mutableStateOf(selectedDate.month.value - 1) } // Adjust for 0-based indexing
            ExposedDropdownMenuBox(
                expanded = isMonthDropdownExpanded,
                onExpandedChange = { isMonthDropdownExpanded = it },
                modifier = Modifier.padding(end = 16.dp) // Add padding to the dropdown
            ) {
                ExposedDropdownMenu(
                    expanded = isMonthDropdownExpanded,
                    onDismissRequest = { isMonthDropdownExpanded = false }
                ) {
                    months.forEachIndexed { index, month ->
                        DropdownMenuItem(
                            text = { Text(text = month) },
                            onClick = {
                                selectedMonthIndex = index
                                isMonthDropdownExpanded = false
                            }
                        )
                    }
                }
            }

            // Year Dropdown
            val years = (1900..LocalDate.now().year).map { it.toString() }
            var selectedYearIndex by remember { mutableStateOf(years.indexOf(selectedDate.year.toString())) }
            ExposedDropdownMenuBox(
                expanded = isYearDropdownExpanded,
                onExpandedChange = { isYearDropdownExpanded = it }
            ) {
                ExposedDropdownMenu(
                    expanded = isYearDropdownExpanded,
                    onDismissRequest = { isYearDropdownExpanded = false }
                ) {
                    years.forEachIndexed { index, year ->
                        DropdownMenuItem(
                            text = { Text(text = year) },
                            onClick = {
                                selectedYearIndex = index
                                isYearDropdownExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Selected Date Display
            BasicTextField(
                value = TextFieldValue(selectedDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))),
                onValueChange = {},
                modifier = Modifier.padding(end = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        CalendarMonth(selectedDate = selectedDate) { date ->
            selectedDate = date
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            onDateSelected(selectedDate)
        }) {
            Text("Select")
        }
    }
}





@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarMonth(selectedDate: LocalDate, onDateSelected: (LocalDate) -> Unit) {
    // Simplified implementation for demonstration
    val daysInMonth = selectedDate.month.length(selectedDate.isLeapYear)
    val firstDayOfMonth = selectedDate.withDayOfMonth(1)
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7
    val days = (1..firstDayOfWeek).map { null } +
            (1..daysInMonth).map { firstDayOfMonth.plusDays((it - 1).toLong()) }

    Column {
        var index = 0
        while (index < days.size) {
            val week = days.subList(index, minOf(index + 7, days.size))
            WeekRow(week, selectedDate, onDateSelected)
            index += 7
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeekRow(days: List<LocalDate?>, selectedDate: LocalDate, onDateSelected: (LocalDate) -> Unit) {
    Row (
        modifier = Modifier.fillMaxWidth()
    ){
        days.forEach { day ->
            if (day != null) {
                CalendarDay(
                    date = day,
                    isSelected = day == selectedDate,
                    onDateSelected = onDateSelected
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}





@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarDay(date: LocalDate, isSelected: Boolean, onDateSelected: (LocalDate) -> Unit) {
    Surface(
        color = if (isSelected) Color.LightGray else Color.Transparent,
        modifier = Modifier
            .padding(4.dp)
            .selectable(selected = isSelected) {
                onDateSelected(date)
            }
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            modifier = Modifier
                .padding(8.dp)
                .widthIn(min = 20.dp),
            textAlign = TextAlign.Center
                // Set a fixed width for consistent size
        )
    }
}




@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomDis() {
    var expanded by remember { mutableStateOf(false) }
    val items = arrayOf("Item 1", "Item 2", "Item 3")
    var selectedItem by remember { mutableStateOf(items[0]) }

    var expandS by remember { mutableStateOf( false) }
    val rooms = arrayOf("Room 101","Room 102","Room 201")
    var selectedRoom by remember { mutableStateOf("") }

    var roomAvailable by remember { mutableStateOf(true) }
    var checkAv by remember { mutableStateOf(false) }
    var checkOc by remember { mutableStateOf(false) }
    var checkUnMa by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val cusPadding = PaddingValues(
        start = 8.dp,
        top = 1.dp,
        end =8.dp
    )

    Column (modifier= Modifier
        .fillMaxSize()
    ){

        /*
        CALLENDAR


         */

        CalendarPicker {

        }





        Text(
            text = "HOTELNAME",
            fontSize = 30.sp,
            modifier = Modifier.padding(cusPadding)
        )
        Text("Choose Room Type:",modifier=Modifier.padding(cusPadding))
        Surface (
            modifier = Modifier
                .padding(cusPadding)
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopStart)) {

            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded=true }) {
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

        Text("Choose room to modify status:",modifier=Modifier.padding(cusPadding))
        Surface (
            modifier = Modifier
                .padding(cusPadding)
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopStart)) {

                ExposedDropdownMenuBox(expanded = expandS, onExpandedChange = { expandS = true }) {
                    TextField(
                        value = selectedRoom,
                        onValueChange = {selectedRoom = it},
                        label={ Text(text = "Type Room Name Here")},
                        trailingIcon = {
                            if (selectedRoom.isNotEmpty()) {
                                IconButton(onClick = {
                                    if (rooms.contains(selectedRoom)) {
                                        roomAvailable = true
                                        // Handle confirm action here
                                        expandS = false
                                        focusManager.clearFocus()
                                        Toast.makeText(context, "Confirm: $selectedRoom", Toast.LENGTH_SHORT).show()
                                    } else {
                                        roomAvailable = false
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

                    val filteredOptions = rooms.filter{it.contains(selectedRoom,ignoreCase = true)}
                    if (filteredOptions.isNotEmpty()){
                    ExposedDropdownMenu(expanded = expandS, onDismissRequest = {}) {
                        filteredOptions.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {
                                    selectedRoom = item
                                    expandS = false
                                }
                            )
                        }
                    }
                    }
                }

        }
        Spacer(modifier = Modifier.padding(top = 8.dp))

        // Checkbox selection allowing only one option to be checked
        CheckboxOption("Available", checkAv) {
            checkAv = it
            if (it) {
                checkOc = false
                checkUnMa = false
            }
        }
        CheckboxOption("Occupied", checkOc) {
            checkOc = it
            if (it) {
                checkAv = false
                checkUnMa = false
            }
        }
        CheckboxOption("Under Maintenance", checkUnMa) {
            checkUnMa = it
            if (it) {
                checkAv = false
                checkOc = false
            }
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




@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun EditRoomsDt() {
    TipTimeTheme {
        RoomDis()
    }
}
