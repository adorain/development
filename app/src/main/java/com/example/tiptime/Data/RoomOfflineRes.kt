package com.example.tiptime.Data

import RoomDao
import kotlinx.coroutines.flow.Flow

class RoomOfflineRes(private val roomDao: RoomDao) : RoomRes {
    override fun checkRoomStatus(hotelId: String, roomType: String): String = roomDao.checkRoomStatus(hotelId, roomType)
    override fun findRoomType(hotelId: String): String = roomDao.findRoomType(hotelId)
    override fun getPriceRange(hotelId: String): String = roomDao.getPriceRange(hotelId)
    override fun getAllRooms(): Flow<List<Room>> = roomDao.getAllRooms()

    override fun getRoomsForDateRangeAndType(hotelId: String, roomType: String, startDate: Long, endDate: Long): List<Room> =
        roomDao.getRoomsForDateRangeAndType(hotelId, roomType, startDate, endDate)

    override suspend fun updateRoom(room: Room) = roomDao.updateRoom(room)
}
