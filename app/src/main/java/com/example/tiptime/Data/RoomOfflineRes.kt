package com.example.tiptime.Data

class RoomOfflineRes (private val roomDao: RoomDao):RoomRes{
    override fun findRoomType(hotelId: String): String = roomDao.findRoomType(hotelId)
    override fun getPriceRange(hotelId: String): String = roomDao.getPriceRange(hotelId)
    override fun checkRoomStatus(hotelId: String, roomType: String): String = roomDao.checkRoomStatus(hotelId,roomType)
}