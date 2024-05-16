package com.example.tiptime

import android.content.Context
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiptime.Data.Hotel
import com.example.tiptime.Data.RoomRes
import com.example.tiptime.Data.room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date


class RoomViewModel (private val roomRes: RoomRes) : ViewModel(){

    private val _uiState = MutableStateFlow(room())
    val uiState: StateFlow<room> = _uiState.asStateFlow()
    /*var roomType by mutableStateOf("")
    var hotelId by mutableStateOf("")
     */
    private val _price = MutableStateFlow(0.0)
    val price: StateFlow<Double> = _price.asStateFlow()

    fun setPriceRange(hotelId:Int):String{
        return roomRes.getPriceRange(hotelId)
    }



    fun checkRoomPrice(hotelId: Int, roomType: String) {
        viewModelScope.launch{
            roomRes.checkRoomPrice(hotelId, roomType).collect { newPrice ->
                if (newPrice != null) {
                    _price.value = newPrice
                }
            }
        }
    }

}