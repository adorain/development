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


import Booked
import BookedFetch
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
import com.example.tiptime.Data.ApplicationInventory
import com.example.tiptime.Data.BookingOfflineRes
import com.example.tiptime.Data.BookingRepository
import com.example.tiptime.Data.HotelOfflineRes
import com.example.tiptime.Data.HotelRepository
import com.example.tiptime.ui.theme.TipTimeTheme
import com.example.tiptime.viewmodel.BookedViewModel
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private val roomViewModel: RoomViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TipTimeTheme {
                val roomViewModel: RoomViewModel = viewModel(factory = AppViewModelProvider.factory)
                EditBooking(
                    viewModel = roomViewModel,
                    hotelId = 1,
                    initialHotelName = "Hotel XYZ",
                    initialHotelAddress = "123 Main St",
                    initialRoomTypes = listOf("Single Room", "Double Room", "King Room")
                )
            }
        }
    }
}




@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun TipTimeLayoutPreview() {
    TipTimeTheme {

    }
}








