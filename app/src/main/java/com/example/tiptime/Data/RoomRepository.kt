// Start of file
package com.example.tiptime.Data

import kotlinx.coroutines.flow.Flow

class RoomRepository(private val roomDao: RoomDao, private val bookingDao: BookingDao) {

    suspend fun insertSampleData() {
        val sampleRooms = listOf(
            room(roomId = 1, roomType = "Single", hotel_id = 1, Status = "Available", price = 100.0),
            room(roomId = 2, roomType = "Single", hotel_id = 1, Status = "Occupied", price = 100.0),
            room(roomId = 3, roomType = "Double", hotel_id = 1, Status = "Under Maintenance", price = 150.0),
            room(roomId = 4, roomType = "Double", hotel_id = 1, Status = "Available", price = 150.0),
            room(roomId = 5, roomType = "King", hotel_id = 1, Status = "Available", price = 200.0)
        )

        val sampleBookings = listOf(
            Booking(Booked_id = 1, HotelId = 1, ROOMTYPE = "Single", BookedStartDate = "2024-05-15", BookedEndDate = "2024-05-20", Pax = 2, Status = "Confirmed", Price = 100.0),
            Booking(Booked_id = 2, HotelId = 1, ROOMTYPE = "Double", BookedStartDate = "2024-05-16", BookedEndDate = "2024-05-22", Pax = 3, Status = "Confirmed", Price = 150.0)
        )

        roomDao.insertRooms(sampleRooms)
        bookingDao.insertBookings(sampleBookings)
    }

    fun getAllRooms(): Flow<List<room>> = roomDao.getAllRooms()

    suspend fun getRoomsForDateRangeAndType(hotelId: Int, roomType: String, startDate: Long, endDate: Long): List<room> {
        return roomDao.getRoomsForDateRangeAndType(hotelId, roomType, startDate, endDate)
    }

    suspend fun updateRoomStatus(room: room) {
        roomDao.updateRoom(room)
    }
}
// End of file
