package com.example.tiptime.Data


import kotlinx.coroutines.flow.Flow
import java.util.Date

class RoomRepository(private val roomDao: RoomDao, private val bookingDao: BookingDao) {

    suspend fun insertSampleData() {
        val sampleRooms = listOf(
            Room(roomId = "1", roomType = "Single", hotel_id = "1", Status = "Available", price = 100.0),
            Room(roomId = "2", roomType = "Single", hotel_id = "1", Status = "Occupied", price = 100.0),
            Room(roomId = "3", roomType = "Double", hotel_id = "1", Status = "Under Maintenance", price = 150.0),
            Room(roomId = "4", roomType = "Double", hotel_id = "1", Status = "Available", price = 150.0),
            Room(roomId = "5", roomType = "King", hotel_id = "1", Status = "Available", price = 200.0)
        )

        val sampleBookings = listOf(
            Booking(Booked_id = "1", HotelId = "1", ROOMTYPE = "Single", BookedStartDate = Date(2024, 5, 15), BookedEndDate = Date(2024, 5, 20), Pax = 2, Status = "Confirmed", Price = 100.0),
            Booking(Booked_id = "2", HotelId = "1", ROOMTYPE = "Double", BookedStartDate = Date(2024, 5, 16), BookedEndDate = Date(2024, 5, 22), Pax = 3, Status = "Confirmed", Price = 150.0)
        )

        roomDao.insertRooms(sampleRooms)
        bookingDao.insertBookings(sampleBookings)
    }


    fun getAllRooms(): Flow<List<Room>> = roomDao.getAllRooms()

    suspend fun getRoomsForDateRangeAndType(hotelId: String, roomType: String, startDate: Long, endDate: Long): List<Room> {
        return roomDao.getRoomsForDateRangeAndType(hotelId, roomType, startDate, endDate)
    }

    suspend fun updateRoomStatus(room: Room) {
        roomDao.updateRoom(room)
    }
}
