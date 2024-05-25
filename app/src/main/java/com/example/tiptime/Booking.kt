package com.example.tiptime



import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.DecimalFormat


@Composable
fun booking (
    //userType: UserType,
    onNextButtonClicked:(String) -> Unit ,
    onCancelButtonClicked : () -> Unit ,
    HotelAddress:String,
    HotelName: String,
    HotelId : Int,
    onPriceSet:(Double) -> Unit
    //status: String


){

    var RoomType by remember {
        mutableStateOf("")
    }
    var count by remember{ mutableStateOf(0) }
    var count1 by remember{ mutableStateOf(0) }
    var count2 by remember{ mutableStateOf(0) }
    var selectedRoom by remember {
        mutableStateOf(false)
    }
    var textColor by remember {
        mutableStateOf(Color.Green)
    }
    var textColor1 by remember {
        mutableStateOf(Color.Green)
    }
    var textColor2 by remember {
        mutableStateOf(Color.Green)
    }
    var totalCount by remember{
        mutableStateOf(0)
    }

    var canClick by remember {
        mutableStateOf(false)
    }
    var price by remember { mutableStateOf(0.00) }
    var price1 by remember { mutableStateOf(0.00) }
    var price2 by remember { mutableStateOf(0.00) }
    var price3 by remember {
        mutableStateOf(0.00)
    }

    /*if(userType == UserType.user){

    }else if(
        userType == UserType.staff
    ){

    }

     */

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
                    Text(text = "King Room", color = Color.Black , fontSize = 20.sp,modifier = Modifier.padding(top = 35.dp))
                }
                Column(

                ) {
                    /*Text(text = checkAvailable(hotelId = HotelId, roomtype = "Single Room",/*status = status*/
                        onRoomTypeSelected = { selectedRoomType ->
                            RoomType = selectedRoomType // Update RoomType
                            count = 1
                        }), color = Color.Black , fontSize = 20.sp,modifier = Modifier.padding(top = 105.dp,start = 185.dp))

                     */
                    price = checkRoomPrice(hotelId = HotelId, roomType = "Single Room")
                    Text(text = price.toString(), color = textColor , fontSize = 20.sp,modifier = Modifier
                        .padding(top = 120.dp, start = 175.dp)
                        .clickable {
                            count++
                            totalCount += count
                            if (count == 1) {
                                price3 = price
                                RoomType = "Single Room"
                                count1 = 0
                                count2 = 0
                                textColor = Color.Red
                                canClick = true
                            } else if (count > 1) {
                                count = 0
                                RoomType = ""
                                textColor = Color.Green
                                canClick = false
                                price3 = 0.00
                            }

                        })



                }
            }


            HorizontalDivider(
                modifier = Modifier.padding(start = 2.dp , top = 90.dp),
                thickness = 3.dp
            )
            Row {
                Column {
                    Text(text = "Single Room", color = Color.Black, fontSize = 20.sp,modifier = Modifier.padding(top = 120.dp))
                }
                Column(

                ) {
                    /*Text(text = checkAvailable(hotelId = HotelId, roomtype = "Double Room",/*status = status*/
                        onRoomTypeSelected = { selectedRoomType ->
                            RoomType = selectedRoomType // Update RoomType
                            count = 1
                        }), color = Color.Black , fontSize = 20.sp,modifier = Modifier.padding(top = 205.dp, start = 178.dp))

                     */
                    price1 = checkRoomPrice(hotelId = HotelId, roomType = "Double Room")
                    Text(text = price1.toString()//checkRoomPrice(hotelId = HotelId, roomType = "Single Room").toString()
                        , color = textColor1 , fontSize = 20.sp,modifier = Modifier
                            .padding(top = 210.dp, start = 165.dp)
                            .clickable {
                                count1++
                                totalCount += count1
                                if (count1 == 1) {
                                    price3 = price1
                                    RoomType = "Double Room"
                                    count = 0
                                    count2 = 0
                                    textColor1 = Color.Red
                                    canClick = true
                                } else if (count1 > 1) {
                                    count1 = 0
                                    RoomType = ""
                                    textColor1 = Color.Green
                                    price3 = 0.00
                                    canClick = false
                                }

                            })


                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(start = 2.dp , top = 180.dp),
                thickness = 3.dp
            )
            Row {
                Column {
                    Text(text = "Double Room", color = Color.Black , fontSize = 20.sp,modifier = Modifier.padding(top = 210.dp))
                }
                Column(

                ) {
                    /*Text(text = checkAvailable(hotelId = HotelId, roomtype = "King Room",onRoomTypeSelected = { selectedRoomType ->
                        RoomType = selectedRoomType // Update RoomType
                        count = 1
                    }), color = Color.Black , fontSize = 20.sp,modifier = Modifier.padding(top = 10.dp,start = 200.dp))


                     */
                    price2 = checkRoomPrice(hotelId = HotelId, roomType = "King Room")
                    Text(text = price2.toString()// Update RoomType
                        , color = textColor2 , fontSize = 20.sp,modifier = Modifier
                            .padding(top = 30.dp, start = 155.dp)
                            .clickable {
                                count2++
                                totalCount += count2
                                if (count2 == 1) {
                                    price3 = price2
                                    RoomType = "King Room"
                                    count = 0
                                    count1 = 0
                                    textColor2 = Color.Red
                                    canClick = true
                                } else if (count2 > 1) {
                                    count2 = 0
                                    RoomType = ""
                                    textColor2 = Color.Green
                                    price3 = 0.00
                                    canClick = false
                                }

                            })



                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(start = 2.dp , top = 260.dp),
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
            OutlinedButton(onClick = {
                onNextButtonClicked(RoomType)
                onPriceSet(price3)
                                     },modifier = Modifier.size(width = 100.dp, height = 50.dp),enabled = canClick) {
                Text(text = "Next")
            }

        }
        Spacer(modifier = Modifier.height(50.dp))

        if(totalCount > 1){
            count1 = 0
            count2 = 0
            count = 0
            textColor = Color.Green
            textColor1 = Color.Green
            textColor2 = Color.Green
            RoomType = ""
            totalCount = 0
            canClick = false
        }










    }

}
@Preview
@Composable
fun Bookings() {

    TipTimeTheme {
        booking(HotelAddress = "", HotelName = "", HotelId = 0, onNextButtonClicked = {}, onCancelButtonClicked = {}, onPriceSet = {})
        //LanscapeLayout(onCancelButtonClicked = {}, onNextButtonClicked = {}, HotelId = "Hiiii", HotelAddress = "Heee", HotelName = "WWWWWW", status = "")
    }
}

/*@Composable
fun checkAvailable(hotelId : String , roomtype: String,/*status:String, */onRoomTypeSelected: (String) -> Unit ,viewModel: RoomViewModel = viewModel(factory = AppViewModelProvider.factory)) : String{
    var status by remember {
        mutableStateOf(viewModel.checkRoomStatus(hotelId,roomtype))
    }
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


 */

/*
@Composable
fun LanscapeLayout(
    onNextButtonClicked: (String) -> Unit = {},
    onCancelButtonClicked: () -> Unit = {},
    HotelAddress: String,
    HotelName: String,
    HotelId: String,
    status: String
) {
    var RoomType by remember {
        mutableStateOf("")
    }

    Row(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
            Image(
                painter = painterResource(R.drawable.nitro_wallpaper_02_3840x2400),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
        }


        Column(
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier

                    .fillMaxWidth()
            ) {
                Row {
                    Text(text = HotelName, color = Color.Black, fontSize = 35.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(text = HotelAddress, fontSize = 15.sp, color = Color.Black)
                }

            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    HorizontalDivider(thickness = 3.dp, modifier = Modifier.padding(top = 50.dp))
                    Row {
                        Column {
                            Text(
                                text = "Single Room",
                                color = Color.Black,
                                fontSize = 13.sp,
                                modifier = Modifier.padding(top = 70.dp)
                            )
                        }
                        Column{
                            Text(
                                text = checkAvailable(
                                    hotelId = HotelId, roomtype = "Single Room", status = status
                                ) { selectedRoomType ->
                                    RoomType = selectedRoomType // Update RoomType
                                },
                                color = Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(top = 135.dp, start = 185.dp)
                            )


                        }
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(start = 2.dp, top = 110.dp),
                        thickness = 3.dp
                    )
                    Row {
                        Column {
                            Text(
                                text = "Double Room",
                                color = Color.Black,
                                fontSize = 13.sp,
                                modifier = Modifier.padding(top = 130.dp)
                            )
                        }
                        Column(

                        ) {
                            Text(text = checkAvailable(
                                hotelId = HotelId, roomtype = "Double Room", status = status
                            ) { selectedRoomType ->
                                RoomType = selectedRoomType // Update RoomType
                            },
                                color = Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(top = 100.dp, start = 178.dp)
                            )


                        }
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(start = 2.dp, top = 175.dp),
                        thickness = 3.dp
                    )
                    Row {
                        Column {
                            Text(
                                text = "King Room",
                                color = Color.Black,
                                fontSize = 13.sp,
                                modifier = Modifier.padding(top = 200.dp)
                            )
                        }
                        Column(

                        ) {
                            Text(text = checkAvailable(
                                hotelId = HotelId, roomtype = "King Room", status = status
                            ) { selectedRoomType ->
                                RoomType = selectedRoomType // Update RoomType
                            },
                                color = Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(top = 10.dp, start = 230.dp)
                            )


                        }
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(start = 2.dp, top = 248.dp),
                        thickness = 3.dp
                    )

                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            // Buttons at the bottom
            Column(
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(modifier = Modifier.padding(start = 45.dp)) {
                    OutlinedButton(
                        onClick = onCancelButtonClicked,
                        modifier = Modifier
                            .padding(end = 100.dp)
                            .size(width = 100.dp, height = 50.dp)
                    ) {
                        Text(text = "Cancel")
                    }
                    OutlinedButton(
                        onClick = { onNextButtonClicked(RoomType) },
                        modifier = Modifier.size(width = 100.dp, height = 50.dp)
                    ) {
                        Text(text = "Next")
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}

 */

@Composable
fun checkRoomPrice(hotelId : Int, roomType:String, roomViewModel: RoomViewModel = viewModel(factory = AppViewModelProvider.factory)):Double{
    var price by remember {
        mutableStateOf(0.00)
    }
    roomViewModel.checkRoomPrice(hotelId , roomType)
    price = roomViewModel.price
    return price
}