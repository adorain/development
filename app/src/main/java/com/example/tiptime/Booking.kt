package com.example.tiptime



import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tiptime.Data.Hotel
import com.example.tiptime.Data.room
import com.example.tiptime.SqlliteManagement.RoomDb
import com.example.tiptime.ui.theme.TipTimeTheme


@Composable
fun booking (
    onNextButtonClicked:(String) -> Unit = {},
    onCancelButtonClicked : () -> Unit = {},
    HotelAddress:String,
    HotelName: String,
    HotelId : String,
    status: String
){
    var RoomType by remember {
        mutableStateOf("")
    }

    Column {
        Row (
            ){
            Image(painter = painterResource(R.drawable.nitro_wallpaper_02_3840x2400), contentDescription = null )
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 260.dp),

    ){
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = HotelName , color = Color.Black, fontSize = 35.sp )

        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(

        ) {
            Text(text = HotelAddress, fontSize = 15.sp , color = Color.Black,)
        }
    }

    Column (
        Modifier
            .fillMaxSize()
            .padding(top = 400.dp),

    ){

        Box(modifier = Modifier){
            HorizontalDivider(modifier = Modifier.padding(start = 2.dp ), thickness = 3.dp)
            Row {
                Column {
                    Text(text = "Single Room", color = Color.Black , fontSize = 20.sp,modifier = Modifier.padding(top = 35.dp))
                }
                Column(

                ) {
                    Text(text = checkAvailable(hotelId = HotelId, roomtype = "Single Room",status = status
                    ) { selectedRoomType ->
                        RoomType = selectedRoomType // Update RoomType
                    }, color = Color.Black , fontSize = 20.sp,modifier = Modifier.padding(top = 105.dp,start = 185.dp))



                }
            }


            HorizontalDivider(
                modifier = Modifier.padding(start = 2.dp , top = 90.dp),
                thickness = 3.dp
            )
            Row {
                Column {
                    Text(text = "Double Room", color = Color.Black, fontSize = 20.sp,modifier = Modifier.padding(top = 130.dp))
                }
                Column(

                ) {
                    Text(text = checkAvailable(hotelId = HotelId, roomtype = "Double Room",status = status
                    ) { selectedRoomType ->
                        RoomType = selectedRoomType // Update RoomType
                    }, color = Color.Black , fontSize = 20.sp,modifier = Modifier.padding(top = 205.dp, start = 178.dp))


                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(start = 2.dp , top = 190.dp),
                thickness = 3.dp
            )
            Row {
                Column {
                    Text(text = "King Room", color = Color.Black , fontSize = 20.sp,modifier = Modifier.padding(top = 230.dp))
                }
                Column(

                ) {
                    Text(text = checkAvailable(hotelId = HotelId, roomtype = "King Room",status = status
                    ) { selectedRoomType ->
                        RoomType = selectedRoomType // Update RoomType
                    }, color = Color.Black , fontSize = 20.sp,modifier = Modifier.padding(top = 10.dp,start = 200.dp))
                    


                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(start = 2.dp , top = 290.dp),
                thickness = 3.dp
            )



        }

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(modifier = Modifier
            .padding(start = 45.dp)
            .fillMaxWidth()) {
            OutlinedButton(
                onClick =  onCancelButtonClicked,modifier = Modifier
                    .padding(end = 100.dp)
                    .size(width = 100.dp, height = 50.dp)) {
                Text(text = "Cancel")
            }
            OutlinedButton(onClick = {onNextButtonClicked(RoomType)},modifier = Modifier.size(width = 100.dp, height = 50.dp)) {
                Text(text = "Next")
            }

        }
        Spacer(modifier = Modifier.height(50.dp))

    }

}
@Preview
@Composable
fun Bookings() {

    TipTimeTheme {
        booking(onCancelButtonClicked = {}, onNextButtonClicked = {}, HotelId = "", HotelAddress = "", HotelName = "", status = "")
    }
}

@Composable
fun checkAvailable(hotelId : String , roomtype: String,status:String,onRoomTypeSelected: (String) -> Unit ) : String{

    var price by remember { mutableStateOf("") }
    var canClick by remember{ mutableStateOf(false) }
    var selectedRoom by remember { mutableStateOf(false) }
    var textcolor by remember{ mutableStateOf(Color.Green) }
    if(status == "Available"){
        canClick =true
        Text(text = price, modifier = Modifier.clickable { selectedRoom = true
                                                                    onRoomTypeSelected(roomtype)
                                                         },color = textcolor)
        textcolor = if(selectedRoom){
            Color.Red
        }else{
            Color.Green
        }
    }
    else if (status == "UnAvailable"){
        canClick = false
        price = "UnAvailable"
        return price
    }
    return price

}
