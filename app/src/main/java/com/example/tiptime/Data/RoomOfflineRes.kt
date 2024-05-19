package com.example.tiptime.Data

import java.util.Date

class RoomOfflineRes (private val roomDao: RoomDao):RoomRes{
    override suspend fun insertRoom(room: room)  = roomDao.addNewRoom(room)
    override fun findRoomType(hotelId: Int) = roomDao.findRoomType(hotelId)
    override fun getPriceRange(hotelId: Int) = roomDao.getPriceRange(hotelId)
    override fun checkRoomPrice(hotelId: Int) = roomDao.checkRoomPrice(hotelId)


}