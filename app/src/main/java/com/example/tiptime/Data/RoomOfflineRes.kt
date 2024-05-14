package com.example.tiptime.Data

import java.util.Date

class RoomOfflineRes (private val roomDao: RoomDao):RoomRes{
    override fun findRoomType(hotelId: String) = roomDao.findRoomType(hotelId)
    override fun getPriceRange(hotelId: String) = roomDao.getPriceRange(hotelId)
    override fun checkRoomPrice(hotelId: String, roomType: String) = roomDao.checkRoomPrice(hotelId,roomType)


}