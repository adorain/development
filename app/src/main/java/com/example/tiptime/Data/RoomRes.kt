package com.example.tiptime.Data

import kotlinx.coroutines.flow.Flow
import java.util.Date


interface RoomRes {
    suspend fun insertRoom(room: room)
    fun checkRoomPrice(hotelId: Int): Flow<List<room>>
    fun findRoomType(hotelId: Int): String
    fun getPriceRange(hotelId: Int): Flow<String>


}