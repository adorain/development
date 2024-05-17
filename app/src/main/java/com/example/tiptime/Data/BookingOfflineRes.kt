package com.example.tiptime.Data


class BookingOfflineRes(private val bookingDao: BookingDao):BookingRes {
    override suspend fun addNewBooking(booking: Booking) = bookingDao.addNewBooking(booking)

    override fun checkRoomStatus(hotelId: Int, roomType: String, BookingStartDate: String, BookingEndDate: String)  = bookingDao.checkRoomStatus(hotelId, roomType, BookingStartDate ,BookingEndDate)
}