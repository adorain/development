package com.example.tiptime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiptime.Data.HotelDao
import com.example.tiptime.Data.RoomDao
import com.example.tiptime.Data.RoomRes
import com.example.tiptime.Data.room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class RoomViewModel (private val roomRes: RoomRes) : ViewModel(){

    private val _uiState = MutableStateFlow(room())
    val uiState: StateFlow<room> = _uiState.asStateFlow()
    /*var roomType by mutableStateOf("")
    var hotelId by mutableStateOf("")

     */

    fun setPriceRange(hotelId:Int):String{
        return roomRes.getPriceRange(hotelId)
    }



    fun checkRoomPrice(hotelId:Int,roomType:String): Double{
        return roomRes.checkRoomPrice(hotelId,roomType)
    }
    fun updateHotelDetails(hotelId: Int, hotelName: String, hotelAddress: String, roomTypes: List<String>) {
        viewModelScope.launch {
            // Update hotel details
            roomRes.updateHotelDetails(hotelId, hotelName, hotelAddress)

            // Update room types
            roomTypes.forEach { roomType ->
                roomRes.updateRoomType(hotelId, roomType)
            }
        }
    }

}