package com.example.tiptime

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tiptime.Data.Room
import com.example.tiptime.Data.RoomRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class RoomViewModel (private val roomRes: RoomRes) : ViewModel(){

    private val _uiState = MutableStateFlow(Room())
    val uiState: StateFlow<Room> = _uiState.asStateFlow()
    var roomType by mutableStateOf("")
    var hotelId by mutableStateOf("")


    fun setPriceRange(hotelId:String):String{
        return roomRes.getPriceRange(hotelId)
    }



    fun checkRoomStatus(): String{
        return roomRes.checkRoomStatus(hotelId,roomType)
    }






}