package com.example.tiptime.Data

import java.util.Date


interface RoomRes {
    fun checkRoomPrice(hotelId: Int, roomType: String): Double
    fun findRoomType(hotelId: Int): String
    fun getPriceRange(hotelId: Int): String


}