package com.example.tiptime

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tiptime.Data.Hotel
import com.example.tiptime.Data.room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class hotelViewModel : ViewModel(){

    private val _uiState = MutableStateFlow(Hotel())
    val uiStateHotel: StateFlow<Hotel> = _uiState.asStateFlow()
    var HotelName by mutableStateOf("")
    var HotelAddress by mutableStateOf("")
    var roomType by mutableStateOf("")
    fun setHomeName(hotelName : String){
        _uiState.update {
                currentState->
            currentState.copy(
                HotelName = hotelName
            )
        }
    }


    fun setHomeAddress(hotelAddress : String){
       _uiState.update {
           currentState->
           currentState.copy(
               HotelAddress = hotelAddress
           )
       }
    }

    fun setHomeDes(hotelDes : String){
        _uiState.update {
                currentState->
            currentState.copy(
                HotelDesciption = hotelDes
            )
        }
    }



}