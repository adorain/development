package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface BookingDao {
    @Insert
    fun addNewBooking(booking: Booking)
}