package com.example.tiptime


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
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.Data.Booking
import com.example.tiptime.ui.theme.TipTimeTheme
import java.util.Date
import javax.sql.DataSource



@Composable
fun bookingSummary(
    onNextButtonClicked:() -> Unit = {},
    onCancelButtonClicked : () -> Unit = {},
    HotelId : String = " ",
    BookingStartDate : Date,
    BookingEndDate :Date,
    Price : Double,
    pax:Int,
    roomType:String
) {
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
                Text(text = BookingStartDate.toString(), fontSize = 21.sp)
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column {
                Text(text = "Booking End Date : ", fontSize = 21.sp)
            }
            Column {
                Text(text = BookingEndDate.toString(), fontSize = 21.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column {
                Text(text = "Pax : ", fontSize = 21.sp)
            }
            Column {
                Text(text = pax.toString(), fontSize = 21.sp)
            }
        }


        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column {
                Text(text = "Room Type: ", fontSize = 21.sp)
            }
            Column {
                Text(text = roomType,  fontSize = 21.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Column {
                Text(text = "Total Price : ", fontSize = 21.sp)
            }
            Column {
                Text(text =Price.toString(), fontSize = 21.sp)
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
}

@Preview
@Composable
fun BookingSummary() {

    TipTimeTheme {
        LandscapeLayout( onCancelButtonClicked = {},
            onNextButtonClicked = {},
            BookingStartDate = Date(),
            BookingEndDate = Date(),
            Price = 0.00,
            HotelId = "",
            pax = 0,
            roomType = ""


        )
    }
}
@Composable
fun LandscapeLayout(
    onNextButtonClicked:() -> Unit = {},
    onCancelButtonClicked : () -> Unit = {},
    HotelId : String = " ",
    BookingStartDate : Date,
    BookingEndDate :Date,
    Price : Double,
    pax:Int,
    roomType:String
){
    Row (
        modifier = Modifier.fillMaxSize()
    ){
        Column(modifier = Modifier.weight(1f)) {
            Image(
                painter = painterResource(R.drawable.nitro_wallpaper_02_3840x2400),
                contentDescription = null
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.Center,



                ) {

                Spacer(modifier = Modifier.height(10.dp))
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
                    Column {
                        Text(text = "Booking Start Date : ", fontSize = 13.sp)
                    }
                    Column {
                        Text(text = BookingStartDate.toString(), fontSize = 13.sp)
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Column {
                        Text(text = "Booking End Date : ", fontSize = 13.sp)
                    }
                    Column {
                        Text(text = BookingEndDate.toString(), fontSize = 13.sp)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Column {
                        Text(text = "Pax : ", fontSize = 13.sp)
                    }
                    Column {
                        Text(text = pax.toString(), fontSize = 13.sp)
                    }
                }


                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Column {
                        Text(text = "Room Type: ", fontSize = 13.sp)
                    }
                    Column {
                        Text(text = roomType,  fontSize = 13.sp)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Column {
                        Text(text = "Total Price : ", fontSize = 13.sp)
                    }
                    Column {
                        Text(text =Price.toString(), fontSize = 13.sp)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Divider(modifier = Modifier.padding(start = 2.dp, top = 20.dp,end = 10.dp), thickness = 3.dp)
                }
                Spacer(modifier = Modifier.height(30.dp))
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
        }
    }

}