package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface BookingDao {
    @Insert
    fun addNewBooking(booking: Booking)

    @Query("SELECT * FROM Booking WHERE BookedStartDate <= :BookingStartDate AND BookedEndDate >= :BookingEndDate AND Status = 'Completed' AND ROOMTYPE = :roomType AND HotelId = :hotelId")
    fun checkRoomStatus(hotelId: Int, roomType: String, BookingStartDate: String, BookingEndDate: String):Boolean
}