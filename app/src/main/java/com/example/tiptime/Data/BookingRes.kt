package com.example.tiptime.Data

import kotlinx.coroutines.flow.Flow
import java.util.Date


interface BookingRes{
    suspend fun addNewBooking(booking: Booking)


    

    fun getReservationsForDate(hotelId: String, startDate: Date, endDate: Date): List<Booking>

    fun checkRoomStatus(hotelId: Int, roomType: String, BookingStartDate : String, BookingEndDate : String): Flow<Int>

}