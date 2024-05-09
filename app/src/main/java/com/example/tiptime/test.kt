package com.example.tiptime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

/*
@Composable
fun NumberPicker(
    minValue: Int,
    maxValue: Int,
    initialValue: Int,
    onValueChange: (Int) -> Unit,
    Close:()-> Unit
) {
    var value by remember { mutableStateOf(initialValue) }
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(onClick = { if (value > minValue) value-- }) {
                Icon(painterResource( R.drawable.down_icon), contentDescription = "Increase")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value.toString())
            Spacer(modifier = Modifier.height(8.dp))
            IconButton(onClick = { if (value < maxValue) value++ }) {
                Icon(painterResource( R.drawable.down_icon), contentDescription = "Decrease")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Select")
            }
        }


        LaunchedEffect(value) {
            onValueChange(value)
        }
    }

}


@Preview
@Composable
fun NumberPickerExample() {
    var selectedValue by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Selected Value: $selectedValue")

        Spacer(modifier = Modifier.height(16.dp))

        NumberPicker(
            minValue = 0,
            maxValue = 10,
            initialValue = selectedValue,
            onValueChange = { newValue -> selectedValue = newValue },
            Close = {}
        )
    }
}



 */

