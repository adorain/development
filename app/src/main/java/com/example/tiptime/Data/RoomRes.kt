package com.example.tiptime.Data

import kotlinx.coroutines.flow.Flow
import java.util.Date


interface RoomRes {
    fun checkRoomPrice(hotelId: Int, roomType: String): Double
    fun findRoomType(hotelId: Int): String
    fun getPriceRange(hotelId: Int): String
    fun getAllRooms(): Flow<List<room>>
    fun getRoomsForDateRangeAndType(hotelId: Int, roomType: String, startDate: Long, endDate: Long): List<room>
    suspend fun updateRoom(room: room)
}