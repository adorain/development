package com.example.tiptime.Data

import kotlinx.coroutines.flow.Flow

class RoomOfflineRes (private val roomDao: RoomDao):RoomRes{
    override fun findRoomType(hotelId: Int) = roomDao.findRoomType(hotelId)
    override fun getPriceRange(hotelId: Int) = roomDao.getPriceRange(hotelId)
    override fun checkRoomPrice(hotelId: Int, roomType: String) = roomDao.checkRoomPrice(hotelId,roomType)

    override fun getAllRooms(): Flow<List<room>> = roomDao.getAllRooms()
    override fun getRoomsForDateRangeAndType(hotelId: Int, roomType: String, startDate: Long, endDate: Long): List<room> =
        roomDao.getRoomsForDateRangeAndType(hotelId, roomType, startDate, endDate)
    override suspend fun updateRoom(room: room) = roomDao.updateRoom(room)
}