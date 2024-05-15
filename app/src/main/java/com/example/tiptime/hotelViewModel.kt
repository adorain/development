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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date

class hotelViewModel(
    private val hotelRes: HotelRes,
    private val bookingRes: BookingRes
) : ViewModel() {

    private val _uiState = MutableStateFlow(Hotel())
    val uiStateHotel: StateFlow<Hotel> = _uiState.asStateFlow()

    var StartDate by mutableStateOf(Date())
    var EndDate by mutableStateOf(Date())
    var pax by mutableStateOf(0)
    var searchtext by mutableStateOf("")
    var HotelName by mutableStateOf("")
    var HotelAddress by mutableStateOf("")
    var roomType by mutableStateOf("")
    var bookings by mutableStateOf(listOf<Booking>())

    fun setHomeName(hotelName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                HotelName = hotelName
            )
        }
    }

    fun setHomeAddress(hotelAddress: String) {
        _uiState.update { currentState ->
            currentState.copy(
                HotelAddress = hotelAddress
            )
        }
    }

    fun setHomeDes(hotelDes: String) {
        _uiState.update { currentState ->
            currentState.copy(
                HotelDesciption = hotelDes
            )
        }
    }

    fun searchHotel(SearchText: String, STARTDATE: Date, ENDDATE: Date, Pax: Int): Boolean {
        return hotelRes.getAvailableHotels(SearchText, STARTDATE, ENDDATE, Pax).isNotEmpty()
    }

    fun updateHotelStatus(hotelId: String, newStatus: String) {
        hotelRes.updateHotelStatus(hotelId, newStatus)
    }

    fun updateSearchText(searchText: String) {
        this.searchtext = searchText
    }

    fun updateStartDate(Startdate: String) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateFormat.parse(Startdate)
        StartDate = date
    }

    fun updateEndDate(EndDate: String) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateFormat.parse(EndDate)
        this.EndDate = date
    }

    fun updatePax(Pax: String) {
        this.pax = Pax.toInt()
    }

    fun getAllHotel(): List<Hotel> {
        return hotelRes.getAllHotel()
    }

    fun getBookingsForDate(date: Date, hotelId: String) {
        viewModelScope.launch {
            val bookingsList = bookingRes.getReservationsForDate(hotelId, date, date)
            bookings = bookingsList
        }
    }
    suspend fun getBookingsForDate(hotelId: String, startDate: Date, endDate: Date): List<Booking> {
        return withContext(Dispatchers.IO) {
            bookingRes.getReservationsForDate(hotelId, startDate, endDate)
        }
    }

}
