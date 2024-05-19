package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface BookingDao {
    @Insert
    suspend fun addNewBooking(booking: Booking)

    @Query("SELECT COUNT(*) FROM Booking WHERE HotelId = :hotelId AND ROOMTYPE = :roomType AND Status = 'Confirmed' AND (:BookingStartDate BETWEEN BookedStartDate AND BookedEndDate OR :BookingEndDate BETWEEN BookedStartDate AND BookedEndDate)")
    fun checkRoomStatus(hotelId: Int, roomType: String, BookingStartDate: String, BookingEndDate: String): Flow<Int>

    @Query("SELECT * FROM booking WHERE BookedStartDate = :date")
    fun getBookingsForDate(date: String): List<Booking>

    @Delete
    fun deleteBooking(booking: Booking)

    @Query("SELECT * FROM Booking WHERE Status = 'Confirmed'")
    fun allBookingWithChecking():Flow<List<Booking>>


}