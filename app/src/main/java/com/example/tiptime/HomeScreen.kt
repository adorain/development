package com.example.tiptime

import android.content.Context
import android.widget.DatePicker
import android.widget.NumberPicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.tiptime.Data.Hotel
import com.example.tiptime.SqlliteManagement.HotelDb
import com.example.tiptime.SqlliteManagement.RoomDb
import com.example.tiptime.ui.theme.TipTimeTheme
import java.util.Calendar
import java.util.Date



@Composable
fun HomeScreen(
    onSelectedHotel:(String)->Unit,
    onSelectedHotelName: (String) -> Unit,
    onSelectedHotelAddress: (String) -> Unit,
    onSelectedHotelDes: (String) -> Unit

){
    val hotelDb = HotelDb(context = LocalContext.current)
    var chooseStartDate by remember {
        mutableStateOf(Date())
    }
    var chooseEndDate by remember {
        mutableStateOf(Date())
    }
    var searchQuery by remember { mutableStateOf("") }
    val hotels = remember { mutableStateListOf<Hotel>() }
    var pax by remember {
        mutableStateOf(0)
    }
    var showDialog by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }
    var showNumberPicker by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top

    ){

        Column(
            modifier = Modifier
                .border(3.dp, Color.Black)
                .width(350.dp)
        ) {

            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center
            ){
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it},
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f),

                    label = { Text(text = "Search")}
                )
                Icon(
                    painter = painterResource(R.drawable.money),
                    contentDescription =null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .height(30.dp)
                        .clickable {
                            hotels.clear()
                            hotels.addAll(
                                hotelDb.getHotelDetails(
                                    searchQuery,
                                    chooseStartDate,
                                    chooseEndDate,
                                    pax
                                )
                            )


                        }
                )

            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ){

                Column(
                    modifier = Modifier.weight(0.5f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Button(onClick = { showDialog =true } , modifier = Modifier
                        .width(200.dp)
                        .height(60.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(0)
                    ) {
                        Text(text = "Check in Date : ", color = Color.Black)
                    }
                }
                Row(modifier = Modifier
                    .height(53.dp)
                    .align(Alignment.CenterVertically)) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(2.dp)
                            .background(Color.Black)
                    )

                }
                Column(
                    modifier = Modifier.weight(0.5f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Button(onClick = { showDialog2 = true } , modifier = Modifier
                        .width(200.dp)
                        .height(60.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(0)
                    ) {
                        Text(text = "Check Out Date : ",color = Color.Black)
                    }
                }
                Row(modifier = Modifier
                    .height(53.dp)
                    .align(Alignment.CenterVertically)) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(2.dp)
                            .background(Color.Black)
                    )

                }
                Column(
                    modifier = Modifier.weight(0.5f),
                    horizontalAlignment = Alignment.End
                ) {
                    Button(onClick = { showNumberPicker = true }, modifier = Modifier
                        .width(200.dp)
                        .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(0)
                    ) {
                        Text(text = "Pax : $pax", color = Color.Black)
                    }
                }

            }
        }


        LaunchedEffect(searchQuery, chooseStartDate, chooseEndDate, pax) {
            hotels.clear()
            hotels.addAll(
                hotelDb.getHotelDetails(
                    searchQuery,
                    chooseStartDate,
                    chooseEndDate,
                    pax
                )
            )
        }
                        // Display list of hotels
        hotels.forEach { hotel ->
            HotelItem(
                hotel = hotel,
                onItemClick = {
                    onSelectedHotel(hotel.HotelId)
                    onSelectedHotelName(hotel.HotelName)
                    onSelectedHotelDes(hotel.HotelDesciption)
                    onSelectedHotelAddress(hotel.HotelAddress)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }





    if(showDialog){
        showdatePicker(context = LocalContext.current, onStartDateSelected = {chooseStartDate})
        showDialog = false
    }



    if(showDialog2){
        showdatePicker(context = LocalContext.current, onStartDateSelected = {chooseEndDate})
        showDialog2 = false
    }



    if(showNumberPicker){
        NumberPickerShow(
            minValue = 0,
            maxValue = 20,
            initialValue = 0,
            onValueChange = {pax},
            OnClose = { showNumberPicker = false},
        )


    }
}

@Composable
fun HotelItem(hotel: Hotel, onItemClick: () -> Unit) {
    val RoomDb = RoomDb(LocalContext.current)
    val HotelDb = HotelDb(LocalContext.current)
    var isChangeColor by remember{ mutableStateOf(false) }
    var color by remember {
        mutableStateOf(Color.White)
    }
    var status by remember{ mutableStateOf("") }
    Row {
        Image(painter = painterResource(R.drawable.down_icon), contentDescription = null,
            modifier = Modifier.clickable {
                isChangeColor = true
            }
            )
    }
    Row(

        modifier = Modifier
            .border(3.dp, Color.Black)
            .width(350.dp)
            .clickable(onClick = onItemClick)
    ) {
        Column(
            modifier = Modifier
                .width(160.dp)
                .height(100.dp)
        ) {
            Image(
                painterResource(R.drawable.nitro_wallpaper_02_3840x2400) ,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )

        }

        Column{

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = hotel.HotelName)
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = hotel.HotelAddress)
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = hotel.HotelDesciption)
            }

            Spacer(modifier = Modifier.height(30.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)){
                    Text(text = "Rating:")
                }
                Column(modifier = Modifier.weight(1f)){
                    Text(text = RoomDb.PriceRange(hotel.HotelId))
                }
            }

        }


        if(isChangeColor){
            color = Color.Red
            status = "Favorite"
            HotelDb.updateHotelStatus(hotel.HotelId,status)
        }else{
            color = Color.White
            status = ""
            HotelDb.updateHotelStatus(hotel.HotelId,"")
        }
    }
}


@Composable
fun showdatePicker(context: Context, onStartDateSelected: (Date) -> Unit){
    val year:Int
    val month: Int
    val day:Int
    val calendar = Calendar.getInstance()
    year= calendar.get(Calendar.YEAR)
    month=calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time= Date()
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

@Composable
fun NumberPickerShow(
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

@Preview
@Composable
fun HomePreview(){
    TipTimeTheme{
        HomeScreen(onSelectedHotel = {}, onSelectedHotelName = {}, onSelectedHotelAddress ={} ) {

        }
    }
}