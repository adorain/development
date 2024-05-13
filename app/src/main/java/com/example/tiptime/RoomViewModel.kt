package com.example.tiptime

import android.content.Context
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.tiptime.Data.RoomRes
import com.example.tiptime.Data.room
import com.example.tiptime.SqlliteManagement.RoomDb
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class RoomViewModel (private val roomRes: RoomRes) : ViewModel(){

    private val _uiState = MutableStateFlow(room())
    val uiState: StateFlow<room> = _uiState.asStateFlow()
    /*var roomType by mutableStateOf("")
    var hotelId by mutableStateOf("")

     */

    fun setPriceRange(hotelId:String):String{
        return roomRes.getPriceRange(hotelId)
    }



    fun checkRoomStatus(hotelId:String,roomType:String): String{
        return roomRes.checkRoomStatus(hotelId,roomType)
    }






}