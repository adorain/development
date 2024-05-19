// Start of file
package com.example.tiptime.Data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RoomRepository(private val roomDao: RoomDao, private val bookingDao: BookingDao,private val hotelDao: HotelDao) {

    suspend fun insertSampleData() {
        val sampleHotels = listOf(
            Hotel(HotelId = 1, HotelName = "Hotel One", HotelAddress = "Address One", Type = "Luxury", Status = "Available", StaffId = "Staff1", UserId = "User1", HotelDescription = "Description One"),
            Hotel(HotelId = 2, HotelName = "Hotel Two", HotelAddress = "Address Two", Type = "Luxury", Status = "Available", StaffId = "Staff1", UserId = "User1", HotelDescription = "Description One"),
            Hotel(HotelId = 3, HotelName = "Hotel Three", HotelAddress = "Address Three", Type = "Luxury", Status = "Available", StaffId = "Staff1", UserId = "User1", HotelDescription = "Description One")

        )

        val sampleRooms = listOf(
            room(roomId = 1, roomType = "Single", hotel_id = 1, Status = "Available", price = 100.0),
            room(roomId = 2, roomType = "Single", hotel_id = 1, Status = "Occupied", price = 100.0),
            room(roomId = 9, roomType = "Single", hotel_id = 1, Status = "Under Maintenance", price = 100.0),
            room(roomId = 3, roomType = "Double", hotel_id = 1, Status = "Under Maintenance", price = 150.0),
            room(roomId = 4, roomType = "Double", hotel_id = 1, Status = "Available", price = 150.0),
            room(roomId = 6, roomType = "Double", hotel_id = 1, Status = "Occupied", price = 150.0),
            room(roomId = 5, roomType = "King", hotel_id = 1, Status = "Available", price = 200.0),
            room(roomId = 7, roomType = "King", hotel_id = 1, Status = "Occupied", price = 200.0),
            room(roomId = 8, roomType = "King", hotel_id = 1, Status = "Under Maintenance", price = 200.0)
        )

        val sampleBookings = listOf(
            Booking(Booked_id = 1, HotelId = 1, ROOMTYPE = "Single", BookedStartDate = "15/05/2024", BookedEndDate = "20/05/2024", Pax = 2, Status = "Confirmed", Price = 100.0),
            Booking(Booked_id = 2, HotelId = 2, ROOMTYPE = "Double", BookedStartDate = "16/05/2024", BookedEndDate = "22/05/2024", Pax = 2, Status = "Confirmed", Price = 150.0),
            Booking(Booked_id = 4, HotelId = 3,ROOMTYPE = "King", BookedStartDate = "23/05/2024", BookedEndDate = "24/05/2024",Pax = 2,Status = "Confirmed",Price = 200.0),
            Booking(Booked_id = 3, HotelId = 1, ROOMTYPE = "Double", BookedStartDate = "17/05/2024", BookedEndDate = "23/05/2024", Pax = 2, Status = "Confirmed", Price = 150.0),
        )

        hotelDao.insertHotels(sampleHotels)
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

    suspend fun getRoomAvailability(hotelId: Int, roomType: String, startDate: String, endDate: String): RoomAvailability {
        val allRooms = roomDao.getAllRoomsForType(hotelId, roomType)
        val bookings = roomDao.getBookingsForDateRange(hotelId, roomType, startDate, endDate)
        val underMaintenanceRooms = allRooms.count { it.Status == "Under Maintenance" }
        val occupiedRooms = bookings.count()
        val availableRooms = allRooms.size - occupiedRooms - underMaintenanceRooms
        return RoomAvailability(availableRooms, occupiedRooms, underMaintenanceRooms)
    }

    suspend fun getHotelName(hotelId: Int): String {
        return hotelDao.getHotelById(hotelId).HotelName
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

}

data class RoomAvailability(
    val availableRooms: Int,
    val occupiedRooms: Int,
    val underMaintenanceRooms: Int
)
// End of file
