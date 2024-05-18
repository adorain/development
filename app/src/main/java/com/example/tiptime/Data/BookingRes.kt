package com.example.tiptime.Data

interface BookingRes {
    suspend fun addNewBooking(booking: Booking)
    suspend fun getAllBookings(): List<Booking>
    suspend fun updateBookingStatus(bookingId: Int, status: String): Int
    fun checkRoomStatus(hotelId: Int, roomType: String, BookingStartDate: String, BookingEndDate: String): Boolean
    suspend fun insertBookings(bookings: List<Booking>)
}
