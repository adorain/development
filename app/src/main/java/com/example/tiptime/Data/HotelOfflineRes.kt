package com.example.tiptime.Data

import java.util.Date

class HotelOfflineRes(private val hotelDao: HotelDao) : HotelRes {
    override fun getAvailableHotels(
        hotelAddress: String,
        startDate: Date,
        endDate: Date,
        pax: Int
    ): List<Hotel> = hotelDao.getAvailableHotels(hotelAddress,startDate,endDate,pax)

    override fun getFavoriteHotels(): List<Hotel> = hotelDao.getFavoriteHotels()
    override fun getHotelById(hotelName: String): String = hotelDao.getHotelId(hotelName)
    override fun updateHotelStatus(hotelId: String, newStatus: String) = hotelDao.updateHotelStatus(hotelId,newStatus)
}