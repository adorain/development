package com.example.tiptime



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


@Composable
fun booking (
    //userType: UserType,
    onNextButtonClicked:(String) -> Unit = {},
    onCancelButtonClicked : () -> Unit = {},
    HotelAddress:String,
    HotelName: String,
    HotelId : Int,
    //status: String
    viewModel: RoomViewModel = viewModel(),

){

    var RoomType by remember {
        mutableStateOf("")
    }
    var count by remember{ mutableStateOf(0) }
    /*if(userType == UserType.user){

    }else if(
        userType == UserType.staff
    ){

    }

     */
    do{
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
                    /*Text(text = checkAvailable(hotelId = HotelId, roomtype = "Single Room",/*status = status*/
                        onRoomTypeSelected = { selectedRoomType ->
                            RoomType = selectedRoomType // Update RoomType
                            count = 1
                        }), color = Color.Black , fontSize = 20.sp,modifier = Modifier.padding(top = 105.dp,start = 185.dp))

                     */
                    Text(text = viewModel.checkRoomPrice(hotelId = HotelId, roomType = "Single Room").toString(), color = Color.Black , fontSize = 20.sp,modifier = Modifier.padding(top = 105.dp,start = 185.dp).clickable { count = 1
                        RoomType = "Single Room"
                    })



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
                    /*Text(text = checkAvailable(hotelId = HotelId, roomtype = "Double Room",/*status = status*/
                        onRoomTypeSelected = { selectedRoomType ->
                            RoomType = selectedRoomType // Update RoomType
                            count = 1
                        }), color = Color.Black , fontSize = 20.sp,modifier = Modifier.padding(top = 205.dp, start = 178.dp))

                     */
                    Text(text = viewModel.checkRoomPrice(hotelId = HotelId, roomType = "Double Room").toString()
                        , color = Color.Black , fontSize = 20.sp,modifier = Modifier.padding(top = 205.dp, start = 178.dp)
                            .clickable { count = 1
                                RoomType = "Double Room"
                            })


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
                    /*Text(text = checkAvailable(hotelId = HotelId, roomtype = "King Room",onRoomTypeSelected = { selectedRoomType ->
                        RoomType = selectedRoomType // Update RoomType
                        count = 1
                    }), color = Color.Black , fontSize = 20.sp,modifier = Modifier.padding(top = 10.dp,start = 200.dp))

                     */
                    Text(text = viewModel.checkRoomPrice(hotelId = HotelId, roomType = "King Room") .toString()// Update RoomType
                        , color = Color.Black , fontSize = 20.sp,modifier = Modifier.padding(top = 10.dp,start = 200.dp)
                            .clickable {
                                count = 1
                                RoomType = "King Room"
                            })
                    


                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(start = 2.dp , top = 290.dp),
                thickness = 3.dp
            )



        }

    }
    }while (!checkSelection(count))

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
fun checkSelection(count :Int):Boolean{
    if(count != 0 || count > 1){
        return false
    }
    return true
}

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
