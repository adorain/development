package com.example.tiptime.Data

import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import java.util.Date



interface HotelRes {
    fun getAvailableHotels(hotelAddress: String, startDate: Date, endDate: Date, pax: Int): List<Hotel>


    fun getHotelById(hotelName: String): Int

    fun getFavoriteHotels(): List<Hotel>
    fun updateHotelStatus(hotelId: Int, newStatus: String)

    fun getAllHotel():List<Hotel>
}