// Start of file
package com.example.tiptime.Data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RoomRepository(private val roomDao: RoomDao, private val bookingDao: BookingDao,private val hotelDao: HotelDao,private val hotelUserDao: HotelUserDao) {


    fun getAllRooms(): Flow<List<room>> = roomDao.getAllRooms()

    suspend fun getRoomsForDateRangeAndType(hotelId: Int, roomType: String, startDate: Long, endDate: Long): List<room> {
        return roomDao.getRoomsForDateRangeAndType(hotelId, roomType, startDate, endDate)
    }

    suspend fun updateRoomStatus(room: room) {
        roomDao.updateRoom(room)
    }

    suspend fun getBookingsForUser(userId: String): List<Booking> {
        return bookingDao.getBookingsForUser(userId)
    }

    suspend fun getRoomAvailability(hotelId: Int, roomType: String, startDate: String, endDate: String): RoomAvailability {
        val allRooms = roomDao.getAllRoomsForType(hotelId, roomType)
        val bookings = roomDao.getBookingsForDateRange(hotelId, roomType, startDate, endDate)
        val underMaintenanceRooms = allRooms.count { it.Status == "Under Maintenance" }
        val occupiedRooms = bookings.count()
        val availableRooms = allRooms.size - occupiedRooms - underMaintenanceRooms
        return RoomAvailability(availableRooms, occupiedRooms, underMaintenanceRooms)
    }


    suspend fun getHotelName(hotelId: Int): String {
        val hotel = hotelDao.getHotelById(hotelId)
        Log.d("RoomRepository", "Retrieved hotel: $hotel")
        return hotel.HotelName ?: throw NullPointerException("Hotel not found for id: $hotelId")
    }






    suspend fun getHotelDetails(hotelId: Int): Pair<Hotel, List<String>> {
        val hotel = hotelDao.getHotelDetails(hotelId)
        val roomTypes = roomDao.getRoomTypes(hotelId)
        return Pair(hotel, roomTypes)
    }



    ///Booked
    suspend fun getAllBookings(): List<Booking> {
        return bookingDao.getAllBookings()
    }

    suspend fun updateBookingStatus(bookingId: Int, status: String) {
        bookingDao.updateBookingStatus(bookingId, status)
    }

    suspend fun getAllHotelsBooked(): List<Hotel> {
        return withContext(Dispatchers.IO) {
            hotelDao.getAllHotelsBooked()
        }
    }

    suspend fun updateHotelDetails(hotelId: Int,hotelName:String,hotelAddress:String,hotelDescription:String){
        roomDao.updateHotelDetails(hotelId, hotelName, hotelAddress,hotelDescription)
    }

    suspend fun updateRoomType(hotelId: Int, roomType: String){
        roomDao.updateRoomType(hotelId, roomType)
    }

    suspend fun getRoomPrice(hotelId: Int, roomType: String): Double {
        return withContext(Dispatchers.IO) {
            roomDao.getRoomPrice(hotelId, roomType)
        }
    }

    suspend fun updateRoomPrice(hotelId: Int, roomType: String, newPrice: Double) {
        withContext(Dispatchers.IO) {
            roomDao.updateRoomPrice(hotelId, roomType, newPrice)
        }
    }


    suspend fun addNewRoom(room: room) {
        withContext(Dispatchers.IO) {
            roomDao.addNewRoom(room)
        }
    }
}

data class RoomAvailability(
    val availableRooms: Int,
    val occupiedRooms: Int,
    val underMaintenanceRooms: Int
)
// End of file
