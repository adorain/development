package com.example.tiptime.Data

import kotlinx.coroutines.flow.Flow
import java.util.Date



interface HotelRes {
    fun getAvailableHotels(hotelAddress: String, startDate: Date, endDate: Date, pax: Int): List<Hotel>

    fun insertHotel(hotelName: String, hotelAddress: String, hotelDescription: String): Flow<List<Hotel>>
    fun getHotelById(hotelName: String): Int

    fun getFavoriteHotels(): Flow<List<Hotel>>
    fun updateHotelStatus(hotelId: Int, newStatus: String)

    fun getAllHotel(): Flow<List<Hotel>>

    suspend fun insertNewHotel(hotel: Hotel)

    fun updateHotelStatusToFavourite(hotelId: Int,Status:String)

    /*fun insertNewHotelInfo(hotelName: String, hotelAddress: String, hotelDescription:String): List<Hotel>
    fun insertNewReview(comment: String, rating:Int): List<Hotel>*/

    fun updateDescription(hotelId: Int, newDescription: String, newRating: Int)

    fun updateStaff(
        newStaffName: String,
        newStaffPhoneNumber: String,
        newStaffEmail: String,
        newStaffPassword: String
    )
    fun filterHotels(
        searchText: String?
    ): List<Hotel>

    fun checkStatus(Status:String , hotelId :Int):Flow<Int>
}