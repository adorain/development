package com.example.tiptime.Data

import java.util.Date


interface BookingRes{
    fun addNewBooking(booking: Booking)

    fun checkRoomStatus(hotelId: String, roomType: String, BookingStartDate : Date, BookingEndDate : Date):Boolean
}