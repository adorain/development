package com.example.tiptime.Data

import kotlinx.coroutines.flow.Flow

class RoomOfflineRes (private val roomDao: RoomDao):RoomRes{
    override fun findRoomType(hotelId: Int) = roomDao.findRoomType(hotelId)
    override fun getPriceRange(hotelId: Int) = roomDao.getPriceRange(hotelId)
    override fun checkRoomPrice(hotelId: Int, roomType: String) = roomDao.checkRoomPrice(hotelId,roomType)

    override fun getAllRooms(): Flow<List<room>> = roomDao.getAllRooms()
    override fun getAllRoomsByType(hotelId: Int, roomType: String)= roomDao.getAllRoomsByType(hotelId,roomType)


    override fun getAvailableRoomsForDateRange(hotelId: Int, roomType: String, startDate: String, endDate: String) =
        roomDao.getAvailableRoomsForDateRange(hotelId,roomType, startDate, endDate)

     override fun getBookingsForDateRange(hotelId: Int, roomType: String, startDate: String, endDate: String) =
         roomDao.getBookingsForDateRange(hotelId, roomType, startDate, endDate)
    override suspend fun updateRoom(room: room) = roomDao.updateRoom(room)
}