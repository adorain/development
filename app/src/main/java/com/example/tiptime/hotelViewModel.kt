package com.example.tiptime

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiptime.Data.Booking
import com.example.tiptime.Data.BookingRes
import com.example.tiptime.Data.Hotel
import com.example.tiptime.Data.HotelRes
import com.example.tiptime.Data.room
import kotlinx.coroutines.Dispatchers
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
    var _hotelList = MutableStateFlow<List<Hotel>>(emptyList())
    val bookings: StateFlow<List<Hotel>> get() = _hotelList
    var _favHotel = MutableStateFlow<List<Hotel>>(emptyList())
    val favHotel: StateFlow<List<Hotel>> get() = _favHotel
    var hotelList:List<Hotel> = listOf()
    var count by mutableStateOf(0)
    var status by mutableStateOf(false)
    var Fav : List<Hotel> = listOf()

    private val _filteredHotels = MutableLiveData<List<Hotel>>()
    val filteredHotels: LiveData<List<Hotel>> get() = _filteredHotels

    fun filterHotels(startDate: String?, endDate: String?, pax: Int?, searchText: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            val hotels = hotelRes.filterHotels(searchText)
            _filteredHotels.postValue(hotels)
            Log.d("fi",_filteredHotels.value?.size.toString())
        }
    }
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
        viewModelScope.launch {
            hotelRes.updateHotelStatus(hotelId,newStatus)
        }

    }

    fun updateSearchText(searchText:String){
        searchtext = searchText
    }

    fun updateStartDate(Startdate:String){
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val date = dateFormat.parse(Startdate)
        StartDate = date
    }

    fun updateEndDate(endDate: String){
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val date = dateFormat.parse(endDate)
        EndDate = date
    }

    fun updatePax(Pax:String){
        pax = Pax.toInt()
    }



    fun getAllHotel(){
        viewModelScope.launch (){
            hotelRes.getAllHotel().collect() { hotels ->
                _hotelList.value= hotels

            }

        }
        hotelList = _hotelList.value
    }

    fun getFavorite(){
        val currentHotels = bookings.value

        Fav = currentHotels.filter {
            hotels -> hotels.Status == "Favourite"
        }
        Log.d("Ha",Fav.size.toString())
    }

    fun insertNewHotel(){
        viewModelScope.launch {
            hotelRes.insertNewHotel(
                Hotel(
                    3,
                    "SD",
                    "UB",
                    "Ben",
                    "",
                    "Kuala Lumpur",
                    "Single and Double",
                    5,
                    0,"",""

                )
            )
        }


    }


    fun markHotelAsFavourite(hotelId: Int,Status:String) {
        viewModelScope.launch (Dispatchers.IO){
            hotelRes.updateHotelStatusToFavourite(hotelId, Status)

        }



        Log.d("hotel",_hotelList.value.toString())



    }



    fun checkFavourite(Status: String,hotelId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            hotelRes.checkStatus(Status,hotelId).collect(){
                count = it
            }
            if( count > 0){
                status = false
            }
        }
    }

    fun updateStatus(hotelId:Int,Status: String){
        _hotelList.update { currentHotels ->
            currentHotels.map { hotel ->
                if (hotel.HotelId == hotelId) {
                    hotel.copy(Status = Status)
                } else {
                    hotel
                }
            }
        }
    }

}
