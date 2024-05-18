package com.example.tiptime.Data

import kotlinx.coroutines.flow.Flow
import java.util.Date


class HotelOfflineRes(private val hotelDao: HotelDao) : HotelRes {
    override fun getAvailableHotels(
        hotelAddress: String,
        startDate: Date,
        endDate: Date,
        pax: Int
    ): List<Hotel> = hotelDao.getAvailableHotels(hotelAddress,startDate.toString(),endDate.toString(),pax)

    override fun getFavoriteHotels(): List<Hotel> = hotelDao.getFavoriteHotels()
    override fun getHotelById(hotelName: String): Int = hotelDao.getHotelId(hotelName)
    override fun updateHotelStatus(hotelId: Int, newStatus: String) = hotelDao.updateHotelStatus(hotelId,newStatus)


    override fun getAllHotelsBooked() = hotelDao.getAllHotelsBooked()

    override fun getAllHotel(): Flow<List<Hotel>> = hotelDao.getAllHotels()
}