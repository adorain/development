package com.example.tiptime


import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.tiptime.Data.Booking
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import javax.sql.DataSource


@Composable
fun bookingDetails(

    onNextButtonClicked:() -> Unit = {},
    onCancelButtonClicked : () -> Unit = {},
    OnBookingStartDateChange : (String) -> Unit={},
    OnBookingEndDateChange : (String) -> Unit={},
    OnPaxChange : (String) -> Unit={},
    HotelId : String = " ",
    BookingStartDate : Date,
    BookingEndDate :Date,
    Price : Double,
    pax:Int,
    roomType:String
) {
    var TotalPrice by remember{ mutableStateOf(0.00) }
    var showDialog by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }

    Column {


        Row(
        ) {
            Image(
                painter = painterResource(R.drawable.nitro_wallpaper_02_3840x2400),
                contentDescription = null
            )
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp, start = 10.dp),
        verticalArrangement = Arrangement.Center,



        ) {

        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column {
                Text(text = "Hotel Id : ", fontSize = 21.sp)
            }
            Column {
                Text(text = HotelId, fontSize = 21.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column {
                Text(text = "Booking Start Date : ", fontSize = 21.sp)
            }
            Column {
                Button(
                    onClick = { showDialog = true }
                ) {
                    Text("Select start Date")
                }
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column {
                Text(text = "Booking End Date : ", fontSize = 21.sp)
            }
            Column{

                Button(
                    onClick = { showDialog2 = true }
                ) {
                    Text("Select End Date")
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column {
                Text(text = "Room Type: ", fontSize = 21.sp)
            }
            Column {
                Text(text =roomType,  fontSize = 21.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column {
                Text(text = "Room Type: ", fontSize = 21.sp)
            }
            Column {
                TextField(value =pax.toString(), onValueChange = OnPaxChange, label = {})
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column {
                Text(text = "Total Price : ", fontSize = 21.sp)
            }
            Column {
                TotalPrice = calculatePrice(STARTDATE = BookingStartDate, ENDDATE = BookingEndDate, PRICE = Price)
                Text(text = Price.toString(), fontSize = 21.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Divider(modifier = Modifier.padding(start = 2.dp, top = 20.dp,end = 10.dp), thickness = 3.dp)
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier.padding(start = 40.dp)
        ) {

            OutlinedButton(
                onClick = onCancelButtonClicked,
                modifier = Modifier
                    .padding(end = 100.dp)
                    .size(width = 100.dp, height = 50.dp)
            ) {
                Text(text = "Cancel")
            }
            OutlinedButton(
                onClick = onNextButtonClicked,
                modifier = Modifier.size(width = 100.dp, height = 50.dp)
            ) {
                Text(text = "Pay")

            }


        }
    }
    if(!showDialog){
        showDatePicker(context = LocalContext.current, onStartDateSelected = {BookingStartDate ->OnBookingStartDateChange(BookingStartDate.toString())})
    }
    else{
        showDialog = false
    }

    if(!showDialog2){
        showDatePicker(context = LocalContext.current, onStartDateSelected = {BookingEndDate ->OnBookingEndDateChange(BookingEndDate.toString())})
    }
    else{
        showDialog2 = false
    }
}

@Composable
fun calculatePrice(STARTDATE:Date,ENDDATE:Date,PRICE:Double):Double{
    return PRICE*(STARTDATE.time - ENDDATE.time)

}

@Preview
@Composable
fun BookingDetail() {
    
    TipTimeTheme {
        bookingDetails(onCancelButtonClicked = {}, onNextButtonClicked = {}, OnBookingEndDateChange = {}, OnBookingStartDateChange = {}, OnPaxChange = {}, BookingStartDate = Date(), BookingEndDate = Date(), roomType = "",Price=0.00,pax = 0)
    }
}

@Composable
fun showDatePicker(context:Context, onStartDateSelected: (Date) -> Unit){
    val year:Int
    val month: Int
    val day:Int
    val calendar = Calendar.getInstance()
    year= calendar.get(Calendar.YEAR)
    month=calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time=Date()
    val date = remember {
        mutableStateOf("")
    }
    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/$month/$year"
        },
        year,
        month,
        day
    )

    datePickerDialog.show()
}