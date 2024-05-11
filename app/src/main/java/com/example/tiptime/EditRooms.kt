@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.tiptime



import android.widget.Toast
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
        .padding(top = 30.dp)){


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
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .height(30.dp)
                .padding(cusPadding)) {
            Checkbox(
                checked = checkAv,
                onCheckedChange = { checkAv = it }
            )
            Text("Available")
        }

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .height(30.dp)
                .padding(cusPadding)) {
            Checkbox(
                checked = checkOc,
                onCheckedChange = { checkOc = it }
            )
            Text("Occupied")
        }

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .height(30.dp)
                .padding(cusPadding)) {
            Checkbox(
                checked = checkUnMa,
                onCheckedChange = { checkUnMa = it }
            )
            Text("Under Maintenance")
        }


    }



}




@Preview(showBackground = true)
@Composable
fun EditRoomsDt() {
    TipTimeTheme {
        RoomDis()
    }
}