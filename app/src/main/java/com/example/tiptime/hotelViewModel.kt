package com.example.tiptime

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiptime.Data.Booking
import com.example.tiptime.Data.BookingRes
import com.example.tiptime.Data.Hotel
import com.example.tiptime.Data.HotelRes
import com.example.tiptime.Data.room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class hotelViewModel (private val hotelRes: HotelRes) : ViewModel(){

    private val _uiState = MutableStateFlow(Hotel())
    val uiStateHotel: StateFlow<Hotel> = _uiState.asStateFlow()
    var StartDate by mutableStateOf(Date())
    var EndDate by mutableStateOf(Date())
    var pax by mutableStateOf(0)
    var searchtext by mutableStateOf("")
    var HotelName by mutableStateOf("")
    var HotelAddress by mutableStateOf("")
    var HotelDescription by mutableStateOf("")
    var roomType by mutableStateOf("")
    var hotelList = MutableStateFlow(mutableListOf<Hotel>())

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
                HotelDescription = hotelDes
            )
        }
    }

    fun searchHotel(SearchText:String,STARTDATE:Date,ENDDATE:Date,Pax: Int):Boolean{
        return hotelRes.getAvailableHotels(SearchText,STARTDATE,ENDDATE,Pax).isNotEmpty()
    }

    fun updateHotelStatus(hotelId: Int, newStatus: String){
        hotelRes.updateHotelStatus(hotelId,newStatus)
    }

    fun updateSearchText(searchText:String){
        searchtext = searchText
    }

    fun updateStartDate(Startdate:String){
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateFormat.parse(Startdate)
        StartDate = date
    }

    fun updateEndDate(endDate: String){
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateFormat.parse(endDate)
        EndDate = date
    }

    fun updatePax(Pax:String){
        pax = Pax.toInt()
    }



    fun getAllHotel(){
        viewModelScope.launch {
            hotelRes.getAllHotel().collect { hotels ->
                hotelList.value= hotels.toMutableList()
            }
        }
    }

    fun getFavorite(){
        viewModelScope.launch {
            hotelRes.getFavoriteHotels().collect{hotels->
                hotelList.value = hotels.toMutableList()
            }
        }
    }

}
