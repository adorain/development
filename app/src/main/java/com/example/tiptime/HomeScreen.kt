package com.example.tiptime

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tiptime.Data.Hotel
import com.example.tiptime.ui.theme.TipTimeTheme
import kotlinx.coroutines.flow.toCollection
import java.util.Calendar
import java.util.Date



@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    //userType: UserType,
    onSelectedHotel:(Int)->Unit,
    onSelectedHotelName: (String) -> Unit,
    onSelectedHotelAddress: (String) -> Unit,
    onSelectedHotelDes: (String) -> Unit,
/*
    onSelectedStartDate :(String)->Unit,
    onSearch :(String)->Unit,
    onSelectedEndDate :(String)->Unit,
    onSelectedPax:(String)->Unit,
    hotel : List<Hotel>,
    availableHotel : List<Hotel>,

 */
    viewModel: hotelViewModel = viewModel(factory = AppViewModelProvider.factory)

) {


    var chooseStartDate by remember {
        mutableStateOf(Date())
    }
    var chooseEndDate by remember {
        mutableStateOf(Date())
    }
    var searchQuery by remember { mutableStateOf("") }
    var pax by remember {
        mutableStateOf(0)
    }
    var showDialog by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }
    var showNumberPicker by remember { mutableStateOf(false) }

    var allHotelList = remember {
        mutableStateListOf<Hotel>()
    }
    var selectedDate by remember {
        mutableStateOf("Check in Date")
    }
    var selectedEndDate by remember {
        mutableStateOf("Check out Date")
    }
    val hotelList by viewModel.bookings.collectAsState()


    /*if(userType == UserType.user){

    }
    else if(userType == UserType.staff){}

     */


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top

    ) {

        Column(
            modifier = Modifier
                .border(3.dp, Color.Black)
                .width(350.dp)
        ) {


            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f),

                    label = { Text(text = "Search") }
                )
                Icon(
                    painter = painterResource(R.drawable.search_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .height(30.dp)
                        .clickable(onClick = {
                            viewModel.filterHotels(selectedDate,selectedEndDate,pax,searchQuery)
                            allHotelList.clear()
                            allHotelList.addAll(viewModel.filteredHotels.value ?: emptyList())
                        })

                )

            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.weight(0.5f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Button(
                        onClick = { showDialog = true }, modifier = Modifier
                            .width(200.dp)
                            .height(60.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(0)
                    ) {
                        Text(text = selectedDate, color = Color.Black)
                    }
                }
                Row(
                    modifier = Modifier
                        .height(53.dp)
                        .align(Alignment.CenterVertically)
                ) {
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
                    Button(
                        onClick = { showDialog2 = true }, modifier = Modifier
                            .width(200.dp)
                            .height(60.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(0)
                    ) {
                        Text(text = selectedEndDate, color = Color.Black)
                    }
                }
                Row(
                    modifier = Modifier
                        .height(53.dp)
                        .align(Alignment.CenterVertically)
                ) {
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
                    Button(
                        onClick = { showNumberPicker = true }, modifier = Modifier
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
        Column(
            Modifier
                .padding(start = 310.dp)
                .clickable {
                    allHotelList.clear()
                    allHotelList.addAll(hotelList)
                }
        ) {
            Text(text = "Clear",)
        }
        Spacer(modifier = Modifier.height(10.dp))


        /*hotelList.clear()
            hotelList.addAll(viewModel.hotelList)
            Log.d("HomeScreen", "Fetched hotels: ${viewModel.hotelList.size}")
            allHotelList.clear()
            allHotelList.addAll(hotelList)

             */





        LaunchedEffect(Unit) {
            allHotelList.clear()
            allHotelList.addAll(hotelList)
            Log.d("",allHotelList.size.toString())
        }
        LazyColumn(modifier = Modifier) {

            items(allHotelList) { hotels ->
                HotelItem(
                    hotel = hotels,
                    onItemClick = {
                        onSelectedHotel(hotels.HotelId)
                        onSelectedHotelName(hotels.HotelName)
                        onSelectedHotelDes(hotels.HotelDescription)
                        onSelectedHotelAddress(hotels.HotelAddress)
                    }
                )
            }
        }


        /*// Display list of hotels
        allHotelList.forEach { hotel ->

            HotelItem(
                hotel = hotel,
                onItemClick = {
                    onSelectedHotel(hotel.HotelId.toString())
                    onSelectedHotelName(hotel.HotelName)
                    onSelectedHotelDes(hotel.HotelDescription)
                    onSelectedHotelAddress(hotel.HotelAddress)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

         */


    }








    if (showDialog) {
        showdatePicker(context = LocalContext.current, onStartDateSelected = {
            chooseStartDate = it
            selectedDate = chooseStartDate.toString()
        })
        showDialog = false
    }



    if (showDialog2) {
        showdatePicker(context = LocalContext.current, onStartDateSelected = {
            chooseEndDate = it
            selectedEndDate = chooseEndDate.toString()
        })
        showDialog2 = false
    }



    if (showNumberPicker) {
        NumberPickerShow(
            minValue = 0,
            maxValue = 20,
            initialValue = 0,
            onValueChange = { pax = it },
            OnClose = { showNumberPicker = false },
        )


    }
}


@Composable
fun HotelItem(hotel: Hotel, onItemClick: () -> Unit) {
    var isChangeColor by remember{ mutableStateOf(false) }
    var color by remember {
        mutableStateOf(Color.White)
    }
    val viewModelhotel: hotelViewModel = viewModel(factory = AppViewModelProvider.factory)
    val viewModelRoom : RoomViewModel = viewModel(factory = AppViewModelProvider.factory)
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
                Text(text = hotel.HotelDescription)
            }

            Spacer(modifier = Modifier.height(30.dp))


        }


        if(isChangeColor){
            color = Color.Red
            viewModelhotel.markHotelAsFavourite(hotel.HotelId,"Favourite")
        }else{
            color = Color.White
            viewModelhotel.markHotelAsFavourite(hotel.HotelId,"")
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

    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            calendar.set(year, month, dayOfMonth)
            onStartDateSelected(calendar.time)
        },
        year,
        month,
        day
    )
    datePickerDialog.datePicker.minDate = calendar.timeInMillis
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
                Icon(painterResource( R.drawable.up_icon_154668), contentDescription = null)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value.toString())
            Spacer(modifier = Modifier.height(8.dp))
            IconButton(onClick = { if (value < maxValue) value++ }) {
                Icon(painterResource( R.drawable.down_icon), contentDescription = null)
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
    val hotel :List<Hotel> = listOf()
    TipTimeTheme{
        HomeScreen(onSelectedHotel = {}, onSelectedHotelName = {}, onSelectedHotelAddress ={}, onSelectedHotelDes = {})
    }
}



