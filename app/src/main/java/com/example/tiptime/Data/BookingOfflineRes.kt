package com.example.tiptime.Data

import java.util.Date


class BookingOfflineRes(private val bookingDao: BookingDao):BookingRes {
    override fun addNewBooking(booking: Booking) = bookingDao.addNewBooking(booking)

    override fun checkRoomStatus(hotelId: String, roomType: String, BookingStartDate: Date, BookingEndDate: Date)  = bookingDao.checkRoomStatus(hotelId, roomType, BookingStartDate ,BookingEndDate)

    override fun getReservationsForDate(hotelId: String, startDate: Date, endDate: Date): List<Booking> =
        bookingDao.getBookingsForDate(hotelId, startDate, endDate)
}