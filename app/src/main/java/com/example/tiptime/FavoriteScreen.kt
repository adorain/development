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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.Data.Hotel
import com.example.tiptime.SqlliteManagement.HotelDb
import com.example.tiptime.SqlliteManagement.RoomDb


@Composable
fun favoritelayout(
    onSelectedHotel:(String)->Unit,
    onSelectedHotelName: (String) -> Unit,
    onSelectedHotelAddress: (String) -> Unit,
    onSelectedHotelDes: (String) -> Unit,
    PriceRange: String
){
    val db = HotelDb(LocalContext.current)
    val hotels = remember { mutableStateListOf<Hotel>() }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        hotels.addAll(db.getFavoriteHotel())
        Column{
            hotels.forEach { hotel ->
                Hotels(
                    hotel = hotel,
                    onItemClick = {
                        onSelectedHotel(hotel.HotelId)
                        onSelectedHotelName(hotel.HotelName)
                        onSelectedHotelDes(hotel.HotelDesciption)
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
            Button(onClick = { /*TODO*/ },modifier = Modifier
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
    val RoomDb = RoomDb(LocalContext.current)
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
                    Text(text = PriceRange)
                }
            }

        }


    }
}