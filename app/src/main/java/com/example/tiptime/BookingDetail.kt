package com.example.tiptime


import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tiptime.Data.Booking
import com.example.tiptime.ui.theme.TipTimeTheme
import java.util.Calendar
import java.util.Date



@Composable
fun bookingDetails(

    onNextButtonClicked:() -> Unit ,
    onCancelButtonClicked : () -> Unit,
    OnBookingStartDateChange : (String) -> Unit,
    OnBookingEndDateChange : (String) -> Unit,
    OnPaxChange : (String) -> Unit={},
    HotelId : Int ,
    BookingStartDate : Date,
    BookingEndDate :Date,
    Price : Double,
    pax:Int,
    roomType:String,
    viewModel: BookingViewModel = viewModel()
) {

    var TotalPrice by remember { mutableStateOf(0.00) }
    var showDialog by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }
    var selectedStartDate by remember { mutableStateOf(BookingStartDate) }
    var selectedEndDate by remember { mutableStateOf(BookingEndDate) }
    var showStartButtonText by remember { mutableStateOf("Select start Date") }
    var showEndButtonText by remember { mutableStateOf("Select end Date") }
    var showNoPicker by remember { mutableStateOf(false) }
    do {


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
                    Text(text = HotelId.toString(), fontSize = 21.sp)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Column(modifier = Modifier.padding(top = 10.dp)) {
                    Text(text = "Booking Start Date : ", fontSize = 21.sp)
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
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Column(
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Text(text = "Booking End Date : ", fontSize = 21.sp)
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
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Column {
                    Text(text = "Room Type: ", fontSize = 21.sp)
                }
                Column {
                    Text(text = roomType, fontSize = 21.sp)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Column(modifier = Modifier.padding(top = 10.dp)) {
                    Text(text = "Pax: ", fontSize = 21.sp)
                }
                Column {
                        Button(
                            onClick = { showNoPicker = true },
                            modifier = Modifier.border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RectangleShape
                            ),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            shape = RoundedCornerShape(0)
                        ) {
                            Text(text = "$pax", color = Color.Black)
                        }

                    //TextField(value =pax.toString(), onValueChange = OnPaxChange, label = {}, textStyle = TextStyle(fontSize = 21.sp), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),modifier = Modifier.fillMaxWidth())

                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Column {

                    Text(text = "Total Price : ", fontSize = 21.sp)
                }
                Column {
                    TotalPrice = calculatePrice(
                        STARTDATE = BookingStartDate,
                        ENDDATE = BookingEndDate,
                        PRICE = Price
                    )
                    Text(text = Price.toString(), fontSize = 21.sp)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Divider(
                    modifier = Modifier.padding(start = 2.dp, top = 20.dp, end = 10.dp),
                    thickness = 3.dp
                )
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
                    onClick = {
                        viewModel.setStatus(HotelId,roomType, BookingStartDate, BookingEndDate)
                        onNextButtonClicked()
                    },
                    modifier = Modifier.size(width = 100.dp, height = 50.dp)
                ) {
                    Text(text = "Next")
                }


            }
        }
        if (showDialog) {
            showDatePicker(
                context = LocalContext.current,
                date = selectedStartDate,
                onDateSelected = { selectedDate ->
                    selectedStartDate = selectedDate
                    OnBookingStartDateChange(selectedDate.toString())
                }
            )
            showDialog = false
        }



        if (showDialog2) {
            showDatePicker(
                context = LocalContext.current,
                date = selectedEndDate,
                onDateSelected = { selectedDate ->
                    selectedEndDate = selectedDate
                    OnBookingEndDateChange(selectedDate.toString())
                }
            )
            showDialog2 = false
        }



        if (showNoPicker) {
            showNumberPicker(
                minValue = 0,
                maxValue = 20,
                initialValue = 0,
                onValueChange = { OnPaxChange(pax.toString()) },
                OnClose = { showNoPicker = false })
        }


        /*if (showDialog) {
        showDatePicker(context = LocalContext.current) { selectedDate ->
            OnBookingStartDateChange(selectedDate.toString())
            showDialog = false // Dismiss the dialog after selecting a date
        }
    }




    if (showDialog2) {
        showDatePicker(context = LocalContext.current) { selectedDate ->
            OnBookingEndDateChange(selectedDate.toString())
            showDialog2 = false // Dismiss the dialog after selecting a date
        }
    }

     */
        if (!checkPaxValidate(pax)) {
            Toast.makeText(
                LocalContext.current,
                "Please select a pax",
                Toast.LENGTH_LONG
            ).show()


            if (!checkEndDateValidate(selectedStartDate, selectedEndDate)) {
                Toast.makeText(
                    LocalContext.current,
                    "EndDate cannot be earlier than startDate",
                    Toast.LENGTH_LONG
                ).show()
            }

            if (!checkStartDateValidate(selectedStartDate)) {
                Toast.makeText(
                    LocalContext.current,
                    "Please select a date",
                    Toast.LENGTH_LONG
                ).show()
            }

            if(!checkPaxValidate(pax)&&!checkEndDateValidate(selectedStartDate, selectedEndDate)&&!checkStartDateValidate(selectedStartDate)){
                Toast.makeText(
                    LocalContext.current,
                    "Please select fill with correct format",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }while (!checkPaxValidate(pax)&&!checkEndDateValidate(selectedStartDate, selectedEndDate)&&!checkStartDateValidate(selectedStartDate))
}

@Composable
fun calculatePrice(STARTDATE:Date,ENDDATE:Date,PRICE:Double):Double{
    return PRICE*(STARTDATE.time - ENDDATE.time)

}

@Preview
@Composable
fun BookingDetail() {
    
    TipTimeTheme {
        landscapeLayout(onCancelButtonClicked = {}, onNextButtonClicked = {}, OnBookingEndDateChange = {}, OnBookingStartDateChange = {}, OnPaxChange = {}, BookingStartDate = Date(), BookingEndDate = Date(), roomType = "",Price=0.00,pax = 0 , HotelId = "")
    }
}

fun showDatePicker(context: Context, date: Date, onDateSelected: (Date) -> Unit) {
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

@Composable
fun showNumberPicker(
    minValue: Int,
    maxValue: Int,
    initialValue: Int,
    onValueChange: (Int) -> Unit,
    OnClose:()-> Unit,
) {
    var value by remember { mutableStateOf(initialValue) }
    Dialog(onDismissRequest = OnClose) {
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
            Button(onClick = OnClose) {
                onValueChange(value)
                Text(text = "Select")
            }
        }
    }

}

@Composable
fun landscapeLayout(
    onNextButtonClicked:() -> Unit ,
    onCancelButtonClicked : () -> Unit,
    OnBookingStartDateChange : (String) -> Unit,
    OnBookingEndDateChange : (String) -> Unit,
    OnPaxChange : (String) -> Unit={},
    HotelId : String ,
    BookingStartDate : Date,
    BookingEndDate :Date,
    Price : Double,
    pax:Int,
    roomType:String
){
    var TotalPrice by remember{ mutableStateOf(0.00) }
    var showDialog by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }
    var selectedStartDate by remember { mutableStateOf(BookingStartDate) }
    var selectedEndDate by remember { mutableStateOf(BookingEndDate) }
    var showStartButtonText by remember { mutableStateOf("Select start Date") }
    var showEndButtonText by remember { mutableStateOf("Select end Date") }
    var showNoPicker by remember { mutableStateOf(false) }
    Row {
        Column (
            modifier = Modifier.weight(1f)
        ){
            Image(
                painter = painterResource(R.drawable.nitro_wallpaper_02_3840x2400),
                contentDescription = null
            )
        }
        Column(Modifier.weight(1f)){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.Center,



                ) {

                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Column {
                        Text(text = "Hotel Id : ", fontSize = 13.sp)
                    }
                    Column {
                        Text(text = HotelId, fontSize = 13.sp)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Column (modifier =Modifier.padding(top = 10.dp)){
                        Text(text = "Booking Start Date : ", fontSize = 13.sp)
                    }
                    Column {
                        Button(
                            onClick = { showDialog = true },
                            modifier = Modifier.border(width = 1.dp, color = Color.Black, shape = RectangleShape).width(200.dp).height(35.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            shape = RoundedCornerShape(0)
                        ) {
                            Text(showStartButtonText,color = Color.Black)
                        }
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Column(
                        modifier =Modifier.padding(top = 10.dp)
                    ) {
                        Text(text = "Booking End Date : ", fontSize = 13.sp)
                    }
                    Column(
                        modifier =Modifier.padding(top =5.dp)
                    ){

                        Button(
                            onClick = { showDialog2 = true },modifier = Modifier.border(width = 1.dp, color = Color.Black, shape = RectangleShape).width(200.dp).height(35.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            shape = RoundedCornerShape(0)
                        ) {
                            Text(showEndButtonText,color = Color.Black, fontSize = 13.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Column {
                        Text(text = "Room Type: ", fontSize = 13.sp)
                    }
                    Column {
                        Text(text =roomType,  fontSize = 213.sp)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Column(modifier = Modifier.padding(top = 10.dp)) {
                        Text(text = "Pax: ", fontSize = 13.sp)
                    }
                    Column {
                        //TextField(value =pax.toString(), onValueChange = OnPaxChange, label = {}, textStyle = TextStyle(fontSize = 21.sp), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),modifier = Modifier.fillMaxWidth())
                        Button(onClick = { showNoPicker = true }, modifier = Modifier.border(width = 1.dp, color = Color.Black, shape = RectangleShape),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            shape = RoundedCornerShape(0)) {
                            Text(text = "$pax", color = Color.Black)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Column {

                        Text(text = "Total Price : ", fontSize = 13.sp)
                    }
                    Column {
                        TotalPrice = calculatePrice(STARTDATE = BookingStartDate, ENDDATE = BookingEndDate, PRICE = Price)
                        Text(text = Price.toString(), fontSize = 13.sp)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Divider(modifier = Modifier.padding(start = 2.dp, top = 20.dp,end = 10.dp), thickness = 3.dp)
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
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
                        Text(text = "Next")
                    }


                }
            }
            if (showDialog) {
                showDatePicker(
                    context = LocalContext.current,
                    date = selectedStartDate,
                    onDateSelected = { selectedDate ->
                        selectedStartDate = selectedDate
                        OnBookingStartDateChange(selectedDate.toString())
                    }
                )
                showDialog = false
            }



            if (showDialog2) {
                showDatePicker(
                    context = LocalContext.current,
                    date = selectedEndDate,
                    onDateSelected = { selectedDate ->
                        selectedEndDate = selectedDate
                        OnBookingEndDateChange(selectedDate.toString())
                    }
                )
                showDialog2 = false
            }



            if(showNoPicker){
                showNumberPicker(minValue = 0, maxValue = 20, initialValue = 0, onValueChange = {OnPaxChange(pax.toString())}, OnClose = {showNoPicker = false})
            }




            /*if (showDialog) {
                showDatePicker(context = LocalContext.current) { selectedDate ->
                    OnBookingStartDateChange(selectedDate.toString())
                    showDialog = false // Dismiss the dialog after selecting a date
                }
            }




            if (showDialog2) {
                showDatePicker(context = LocalContext.current) { selectedDate ->
                    OnBookingEndDateChange(selectedDate.toString())
                    showDialog2 = false // Dismiss the dialog after selecting a date
                }
            }

             */

        }
    }

}

fun checkStartDateValidate(STARTDATE: Date):Boolean{
    if(STARTDATE < Date()){
        return false
    }
    return true
}

fun checkEndDateValidate(STARTDATE: Date,ENDDATE: Date):Boolean{
    if(ENDDATE < STARTDATE){
        return false

    }
    return true
}

fun checkPaxValidate(Pax:Int):Boolean{
    if(Pax<= 0 ){
        return false
    }
    return true
}