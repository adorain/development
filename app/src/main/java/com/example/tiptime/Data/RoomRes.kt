package com.example.tiptime.Data

import java.util.Date


interface RoomRes {
    fun checkRoomPrice(hotelId: String, roomType: String): Double
    fun findRoomType(hotelId: String): String
    fun getPriceRange(hotelId: String): String


}