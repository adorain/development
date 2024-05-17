package com.example.tiptime.Data

import kotlinx.coroutines.flow.Flow

class HotelRepository(private val hotelDao: HotelDao) {

    suspend fun getAllHotels(): List<Hotel> {
        return hotelDao.getAllHotelsBooked()
    }

    // Add any additional methods if required
}
