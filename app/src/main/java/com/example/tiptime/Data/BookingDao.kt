package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface BookingDao {
    @Insert
    suspend fun addNewBooking(booking: Booking)



    @Query("SELECT * FROM Booking WHERE BookedStartDate <= :BookingEndDate AND BookedEndDate >= :BookingStartDate AND HotelId = :hotelId")
    fun getBookingsForDate(hotelId: String, BookingStartDate: Date, BookingEndDate: Date): List<Booking>
  
    @Query("SELECT COUNT(*) FROM Booking WHERE HotelId = :hotelId AND ROOMTYPE = :roomType AND Status = 'Confirmed' AND (:BookingStartDate BETWEEN BookedStartDate AND BookedEndDate OR :BookingEndDate BETWEEN BookedStartDate AND BookedEndDate)")
    fun checkRoomStatus(hotelId: Int, roomType: String, BookingStartDate: String, BookingEndDate: String): Flow<Int>
