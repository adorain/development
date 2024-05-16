package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.Date


@Dao
interface BookingDao {
    @Insert
    fun addNewBooking(booking: Booking)

    @Query("SELECT * FROM Booking WHERE BookedStartDate <= :BookingStartDate AND BookedEndDate >= :BookingEndDate AND Status = 'Completed' AND ROOMTYPE = :roomType")
    fun checkRoomStatus(hotelId: String, roomType: String, BookingStartDate : Date, BookingEndDate : Date):Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookings(bookings: List<Booking>)
}