package com.example.tiptime.Data

class BookingOfflineRes(private val bookingDao: BookingDao) : BookingRes {
    override suspend fun addNewBooking(booking: Booking) = bookingDao.addNewBooking(booking)

    override suspend fun getAllBookings(): List<Booking> = bookingDao.getAllBookings()

    override suspend fun updateBookingStatus(bookingId: Int, status: String): Int = bookingDao.updateBookingStatus(bookingId, status)

    override fun checkRoomStatus(hotelId: Int, roomType: String, BookingStartDate: String, BookingEndDate: String): Boolean =
        bookingDao.checkRoomStatus(hotelId, roomType, BookingStartDate, BookingEndDate)

    override suspend fun insertBookings(bookings: List<Booking>) = bookingDao.insertBookings(bookings)
}
