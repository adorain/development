package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
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

    @Query("SELECT * FROM Booking")
    suspend fun getAllBookings(): List<Booking>

    @Query("UPDATE Booking SET Status = :status WHERE Booked_id = :bookingId")
    suspend fun updateBookingStatus(bookingId: Int, status: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookings(rooms: List<Booking>)

    @Query("SELECT ROOMTYPE, SUM(Price) AS earnings, COUNT(*) AS numOfRooms FROM Booking WHERE BookedStartDate BETWEEN :startDate AND :endDate GROUP BY ROOMTYPE")
    fun getBookingStatistics(startDate: String, endDate: String): Flow<List<BookingStatistics>>
}