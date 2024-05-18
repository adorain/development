package com.example.tiptime.Data

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow

class HotelRepository(private val hotelDao: HotelDao) {

    suspend fun insertHotels(hotels: List<Hotel>) {
        hotelDao.insertHotels(hotels)
    }

    suspend fun getAllHotelsBooked():List<Hotel>{
        return hotelDao.getAllHotelsBooked()
    }



    // Add any additional methods if required
}