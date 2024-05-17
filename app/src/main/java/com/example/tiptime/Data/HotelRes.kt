package com.example.tiptime.Data

import kotlinx.coroutines.flow.Flow
import java.util.Date



interface HotelRes {
    fun getAvailableHotels(hotelAddress: String, startDate: Date, endDate: Date, pax: Int): List<Hotel>

    fun insertHotel(hotelName: String, hotelAddress: String, hotelDescription: String):List<Hotel>
    fun getHotelById(hotelName: String): Int

    fun getFavoriteHotels(): Flow<List<Hotel>>
    fun updateHotelStatus(hotelId: Int, newStatus: String)

    fun getAllHotel(): Flow<List<Hotel>>
}