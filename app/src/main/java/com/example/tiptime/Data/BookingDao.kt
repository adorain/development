package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.Date


@Dao
interface BookingDao {
    @Insert
    fun addNewBooking(booking: Booking)

    @Query("SELECT * FROM Booking WHERE BookedStartDate <= :BookingStartDate AND BookedEndDate >= :BookingEndDate AND Status = 'Completed' AND ROOMTYPE = :roomType" )
    fun checkRoomStatus(hotelId: String, roomType: String, BookingStartDate : Date, BookingEndDate : Date):Boolean

    @Query("SELECT * FROM Booking WHERE BookedStartDate <= :BookingEndDate AND BookedEndDate >= :BookingStartDate AND HotelId = :hotelId")
    fun getBookingsForDate(hotelId: String, BookingStartDate: Date, BookingEndDate: Date): List<Booking>
}