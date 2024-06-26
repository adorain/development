package com.example.tiptime

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
    viewModel: hotelViewModel = viewModel(factory = AppViewModelProvider.factory),
    favouriteHotel:List<Hotel>
){

    val hotels = remember { mutableStateListOf<Hotel>() }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn{
            items(favouriteHotel){
                hotel ->
                Hotels(
                    hotel = hotel,
                    onItemClick = {
                        onSelectedHotel(hotel.HotelId)
                        onSelectedHotelName(hotel.HotelName)
                        onSelectedHotelDes(hotel.HotelDescription)
                        onSelectedHotelAddress(hotel.HotelAddress)
                    },

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
                Text(text = "Add", fontSize = 21.sp,color = Color.Black)

            }
        }
    }

}

@Composable
fun Hotels(hotel: Hotel, onItemClick: () -> Unit) {
    val viewModelHotel: hotelViewModel = viewModel(factory = AppViewModelProvider.factory)
    var isFavorite by remember { mutableStateOf(hotel.Status == "Favourite") }


    Row(
        modifier = Modifier
            .border(3.dp, Color.Black)
            .width(350.dp)
            .clickable(onClick = onItemClick)
    ) {
        Column(
            modifier = Modifier
                .width(160.dp)
                .height(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.muda),
                contentDescription = null,
                modifier = Modifier.size(180.dp)
            )
        }

        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
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

            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        isFavorite = !isFavorite
                        viewModelHotel.markHotelAsFavourite(
                            hotel.HotelId,
                            if (isFavorite) "Favourite" else ""
                        )
                        viewModelHotel.updateStatus(
                            hotel.HotelId,
                            if (isFavorite) "Favourite" else ""
                        )
                    },
                    shape = RoundedCornerShape(0),
                    modifier = Modifier
                        .size(20.dp)
                        .border(2.dp, Color.Black, RectangleShape),
                    colors = ButtonDefaults.buttonColors(containerColor = if (isFavorite) Color.Red else Color.White),
                ) {

                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}