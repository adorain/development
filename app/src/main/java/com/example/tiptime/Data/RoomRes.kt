package com.example.tiptime.Data

import kotlinx.coroutines.flow.Flow
import java.util.Date


interface RoomRes {
    fun checkRoomPrice(hotelId: Int, roomType: String): Flow<Double?>
    fun findRoomType(hotelId: Int): String
    fun getPriceRange(hotelId: Int): String


}