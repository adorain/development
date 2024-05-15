package com.example.tiptime.Data

import java.util.Date

class RoomOfflineRes (private val roomDao: RoomDao):RoomRes{
    override fun findRoomType(hotelId: Int) = roomDao.findRoomType(hotelId)
    override fun getPriceRange(hotelId: Int) = roomDao.getPriceRange(hotelId)
    override fun checkRoomPrice(hotelId: Int, roomType: String) = roomDao.checkRoomPrice(hotelId,roomType)


}