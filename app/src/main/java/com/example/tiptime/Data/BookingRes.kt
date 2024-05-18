package com.example.tiptime.Data

import java.util.Date


interface BookingRes{
    suspend fun addNewBooking(booking: Booking)

    fun checkRoomStatus(hotelId: Int, roomType: String, BookingStartDate : String, BookingEndDate : String):Int

    fun getReservationsForDate(date: String): List<Booking>
    fun deleteBooking(booking: Booking)
}