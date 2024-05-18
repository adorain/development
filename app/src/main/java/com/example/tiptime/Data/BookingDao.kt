package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface BookingDao {
    @Insert
    suspend fun addNewBooking(booking: Booking)

    @Query("SELECT * FROM Booking")
    suspend fun getAllBookings(): List<Booking>

    @Query("UPDATE Booking SET Status = :status WHERE Booked_id = :bookingId")
    suspend fun updateBookingStatus(bookingId: Int, status: String): Int

    @Query("SELECT * FROM Booking WHERE BookedStartDate <= :BookingStartDate AND BookedEndDate >= :BookingEndDate AND Status = 'Completed' AND ROOMTYPE = :roomType AND HotelId = :hotelId")
    fun checkRoomStatus(hotelId: Int, roomType: String, BookingStartDate: String, BookingEndDate: String):Boolean


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookings(rooms: List<Booking>)

}