package com.example.tiptime

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tiptime.Data.Hotel




@Composable
fun favoritelayout(

    onNextButton:() -> Unit,

    onSelectedHotel:(Int)->Unit,
    onSelectedHotelName: (String) -> Unit,
    onSelectedHotelAddress: (String) -> Unit,
    onSelectedHotelDes: (String) -> Unit,
    PriceRange: String,
    viewModel: hotelViewModel = viewModel(factory = AppViewModelProvider.factory)
){

    val hotels = remember { mutableStateListOf<Hotel>() }
    LaunchedEffect(Unit) {

        hotels.clear()
        hotels.addAll(viewModel.favHotel.value)

    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn{
            items(hotels){
                hotel ->
                Hotels(
                    hotel = hotel,
                    onItemClick = {
                        onSelectedHotel(hotel.HotelId)
                        onSelectedHotelName(hotel.HotelName)
                        onSelectedHotelDes(hotel.HotelDescription)
                        onSelectedHotelAddress(hotel.HotelAddress)
                    },
                    PriceRange = PriceRange
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Button(onClick = { onNextButton() },modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .border(2.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(0)) {
                Text(text = "Add", fontSize = 21.sp)

            }
        }
    }

}

@Composable
fun Hotels(hotel: Hotel, onItemClick: () -> Unit,PriceRange:String) {

    var isChangeColor by remember { mutableStateOf(false) }
    var color by remember {
        mutableStateOf(Color.White)
    }
    val viewModelhotel: hotelViewModel = viewModel(factory = AppViewModelProvider.factory)
    val viewModelRoom: RoomViewModel = viewModel(factory = AppViewModelProvider.factory)
    var status by remember { mutableStateOf("") }
    Row {
        Image(painter = painterResource(R.drawable.down_icon), contentDescription = null,
            modifier = Modifier.clickable {
                isChangeColor = true
                viewModelhotel.markHotelAsFavourite(hotel.HotelId, hotel.Status)
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
                painterResource(R.drawable.nitro_wallpaper_02_3840x2400),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )

        }

        Column {

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

            if(isChangeColor){
                color = Color.Red
                status = "Favorite"
                viewModelhotel.markHotelAsFavourite(hotel.HotelId,"Favourite")
            }else{
                color = Color.White
                status = ""
                viewModelhotel.markHotelAsFavourite(hotel.HotelId,"")
            }

        }

    }
}