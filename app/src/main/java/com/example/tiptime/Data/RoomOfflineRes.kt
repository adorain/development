package com.example.tiptime.Data

import RoomDao
import androidx.room.Room


class RoomOfflineRes(private val roomDao: RoomDao) : RoomRes {
    override fun checkRoomStatus(hotelId: String, roomType: String): String = roomDao.checkRoomStatus(hotelId, roomType)
    override fun findRoomType(hotelId: String): String = roomDao.findRoomType(hotelId)
    override fun getPriceRange(hotelId: String): String = roomDao.getPriceRange(hotelId)
    override fun getAllRooms(hotelId: String): List<Room> = roomDao.getAllRooms(hotelId)
    override fun getRoomsForDateRangeAndType(hotelId: String, roomType: String, startDate: Long, endDate: Long): List<Room> =
        roomDao.getRoomsForDateRangeAndType(hotelId, roomType, startDate, endDate)
    override suspend fun updateRoom(room: Room) = roomDao.updateRoom(Room)
}

