/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.tiptime

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tiptime.Data.Booking
import com.example.tiptime.Data.Hotel
import com.example.tiptime.ui.theme.TipTimeTheme
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Date

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)



        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    TipTimeTheme {
                        //val hotel :List<Hotel> = listOf()
                        ///HomeScreen(onSelectedHotel = {}, onSelectedHotelName = {}, onSelectedHotelAddress ={}, onSelectedHotelDes = {})
                        //booking(HotelAddress = "", HotelName = "", HotelId = "")
                        //HotelMainScreen()
                        TravelApp()
                        //NewUserPreview()
                        //val viewModel : hotelViewModel = viewModel(factory = AppViewModelProvider.factory)
                        //viewModel.insertNewHotel()
                        //val viewModel : RoomViewModel = viewModel(factory = AppViewModelProvider.factory)
                        //viewModel.addNewRoom()


                    }
                }
            }
        }
    }
}




@Preview
@Composable
fun TipTimeLayoutPreview() {
    TipTimeTheme {
       // LanscapeLayout(onCancelButtonClicked = {}, onNextButtonClicked = {}, HotelId = "", HotelAddress = "", HotelName = "", status = "")
    }
}








