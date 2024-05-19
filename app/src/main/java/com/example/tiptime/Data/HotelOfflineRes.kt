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

    override fun insertHotel(
        hotelName: String,
        hotelAddress: String,
        hotelDescription: String

    ): Flow<List<Hotel>> = hotelDao.insertHotel()

    override fun getFavoriteHotels()  = hotelDao.getFavoriteHotels()
    override fun getHotelById(hotelName: String) = hotelDao.getHotelId(hotelName)
    override fun updateHotelStatus(hotelId: Int, newStatus: String) = hotelDao.updateHotelStatus(hotelId,newStatus)

    override fun getAllHotel() = hotelDao.getAllHotels()


    override suspend fun insertNewHotel(hotel: Hotel)  = hotelDao.insertHotel(hotel)


    override fun updateDescription(hotelId: Int, newDescription: String, newRating: Int) = hotelDao.updateDescription(hotelId, newDescription, newRating)

    override fun updateStaff(
        newStaffName: String,
        newStaffPhoneNumber: String,
        newStaffEmail: String,
        newStaffPassword: String
    ) =hotelDao.updateStaff(newStaffName, newStaffPhoneNumber, newStaffEmail, newStaffPassword)

    override fun updateHotelStatusToFavourite(hotelId: Int,Status:String) = updateHotelStatus(hotelId,Status)
}