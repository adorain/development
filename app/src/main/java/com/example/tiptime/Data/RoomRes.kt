package com.example.tiptime.Data

interface RoomRes {
    fun checkRoomStatus(hotelId: String, roomType: String): String
    fun findRoomType(hotelId: String): String
    fun getPriceRange(hotelId: String): String
}