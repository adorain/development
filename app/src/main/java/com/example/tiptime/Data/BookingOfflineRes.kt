package com.example.tiptime.Data


class BookingOfflineRes(private val bookingDao: BookingDao):BookingRes {
    override fun addNewBooking(booking: Booking) = bookingDao.addNewBooking(booking)
}