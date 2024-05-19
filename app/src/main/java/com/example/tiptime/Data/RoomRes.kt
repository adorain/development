package com.example.tiptime.Data

import kotlinx.coroutines.flow.Flow
import java.util.Date


interface RoomRes {
    fun checkRoomPrice(hotelId: Int, roomType: String): Double
    fun findRoomType(hotelId: Int): String
    fun getPriceRange(hotelId: Int): String
    fun getAllRooms(): Flow<List<room>>

    fun getAllRoomsByType(hotelId: Int, roomType: String): List<room>



    fun getAvailableRoomsForDateRange(hotelId: Int, roomType: String, startDate: String, endDate: String): List<room>

    fun getBookingsForDateRange(hotelId: Int, roomType: String, startDate: String, endDate: String): List<Booking>

    // New methods for updating hotel details and room types
    suspend fun updateHotelDetails(hotelId: Int, hotelName: String, hotelAddress: String,hotelDescription:String)
    suspend fun updateRoomType(hotelId: Int, roomType: String)
    suspend fun updateRoom(room: room)
}