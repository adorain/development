package com.example.tiptime.Data

import androidx.room.Room


interface RoomRes {
    fun checkRoomStatus(hotelId: String, roomType: String): String
    fun findRoomType(hotelId: String): String
    fun getPriceRange(hotelId: String): String
    fun getAllRooms(hotelId: String):List<Room>
    fun getRoomsForDateRangeAndType(hotelId: String, roomType: String, startDate: Long, endDate: Long): List<Room>
    suspend fun updateRoom(room: Room)
}