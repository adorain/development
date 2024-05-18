package com.example.tiptime.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tiptime.Data.Booking
import com.example.tiptime.Data.BookingRes
import com.example.tiptime.Data.Hotel
import com.example.tiptime.Data.HotelRes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

class BookedViewModel(
    private val bookingRes: BookingRes,
    private val hotelRes: HotelRes
) : ViewModel() {

    val bookings: Flow<List<Booking>> = flow {
        emit(bookingRes.getAllBookings())
    }

    val hotels: Flow<List<Hotel>> = flow {
        emit(hotelRes.getAllHotelsBooked())
    }


    fun calculateDays(startDate: String, endDate: String): Long {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val start = dateFormat.parse(startDate)
        val end = dateFormat.parse(endDate)
        val diff = end.time - start.time
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
    }
}
