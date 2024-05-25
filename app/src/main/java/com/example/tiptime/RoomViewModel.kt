package com.example.tiptime

import android.content.Context
import android.util.Log
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
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
    private var _RoomList = MutableStateFlow<List<room>>(emptyList())
    val RoomList: StateFlow<List<room>> get() = _RoomList
    var priceRange = MutableStateFlow("")
    var price : Double = 0.00

    /*var roomType by mutableStateOf("")
    var hotelId by mutableStateOf("")

     */

    fun setPriceRange(hotelId:Int):String{
        viewModelScope.launch(Dispatchers.IO) {
            roomRes.getPriceRange(hotelId).collect{
                priceRange.value = it
            }

        }
        return priceRange.value

    }





    fun checkRoomPrice(hotelId: Int, roomType: String) {
        viewModelScope.launch(Dispatchers.IO) {
            roomRes.checkRoomPrice(hotelId).collect { newPrice ->
                _RoomList.value = newPrice
            }
        }

        _RoomList.value.forEach { room ->
            if (room.roomType == roomType && room.hotel_id == hotelId) {
                price = room.price
                // Found the room type, you can access its properties here
                Log.d("RoomViewModel", "Room Type: ${room.roomType}, Price: ${room.price}, Status: ${room.Status}")
            }
//            if (room.roomType != roomType && room.hotel_id == hotelId) {
//                price = 0.00
//                // Found the room type, you can access its properties here
//                Log.d("RoomViewModel", "Room Type: ${room.roomType}, Price: ${room.price}, Status: ${room.Status}")
//            }
//            else{
//                price=0.00
//            }
        }

    }


 fun addNewRoom(){
        viewModelScope.launch (Dispatchers.IO){
            roomRes.insertRoom(
                room(
                    2,
                    "King Room",
                    2,
                    30.00,
                    "Available"
                )
            )
        }
  }


}