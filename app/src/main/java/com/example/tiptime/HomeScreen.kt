package com.example.tiptime

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.nio.file.WatchEvent

@Preview
@Composable
fun homeScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
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
                    value = "",
                    onValueChange = {},
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
                    Button(onClick = { /*TODO*/ } , modifier = Modifier
                        .width(200.dp)
                        .height(60.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(0)
                    ) {
                        Text(text = "Check in Date : ")
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
                    Button(onClick = { /*TODO*/ } , modifier = Modifier
                        .width(200.dp)
                        .height(60.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(0)
                    ) {
                        Text(text = "Check Out Date : ")
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
                    Button(onClick = { /*TODO*/ }, modifier = Modifier
                        .width(200.dp)
                        .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(0)
                    ) {
                        Text(text = "Pax : ")
                    }
                }

            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.padding(start = 340.dp)
        ) {
            Image(painter = painterResource(R.drawable.down_icon), contentDescription = null, modifier = Modifier
                .width(10.dp)
            )
        }

        Row(
            modifier = Modifier
                .border(3.dp, Color.Black)
                .width(350.dp)
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
                    Text(text = "Hotel Name :")
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Address")
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Description")
                }

                Spacer(modifier = Modifier.height(30.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)){
                        Text(text = "Rating:")
                    }
                    Column(modifier = Modifier.weight(1f)){
                        Text(text = "Price")
                    }
                }

            }


        }
    }

    }

