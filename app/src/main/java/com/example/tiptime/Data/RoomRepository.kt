// Start of file
package com.example.tiptime.Data

import android.util.Log
import com.example.tiptime.RoomViewModel
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Locale

class RoomRepository(private val roomDao: RoomDao, private val bookingDao: BookingDao,private val hotelDao:HotelDao) {

    private fun parseDateString(dateString: String): Long {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.parse(dateString)?.time ?: 0L
    }

    suspend fun insertSampleData() {
        val sampleHotels = listOf(
            Hotel(
                HotelId = 1,
                HotelName = "Hotel One",
                HotelAddress = "Address One",
                Type = "Luxury",
                Status = "Available",
                StaffId = "Staff1",
                UserId = "User1",
                HotelDesciption = "Description One",
                Pax = 2
            )
            // Add more hotels if needed
        )

        val sampleRooms = listOf(
            room(
                roomId = 1,
                roomType = "Single",
                hotel_id = 1,
                Status = "Available",
                price = 100.0
            ),
            room(roomId = 2, roomType = "Single", hotel_id = 1, Status = "Occupied", price = 100.0),
            room(
                roomId = 9,
                roomType = "Single",
                hotel_id = 1,
                Status = "Under Maintenance",
                price = 100.0
            ),
            room(
                roomId = 3,
                roomType = "Double",
                hotel_id = 1,
                Status = "Under Maintenance",
                price = 150.0
            ),
            room(
                roomId = 4,
                roomType = "Double",
                hotel_id = 1,
                Status = "Available",
                price = 150.0
            ),
            room(roomId = 6, roomType = "Double", hotel_id = 1, Status = "Occupied", price = 150.0),
            room(roomId = 5, roomType = "King", hotel_id = 1, Status = "Available", price = 200.0),
            room(roomId = 7, roomType = "King", hotel_id = 1, Status = "Occupied", price = 200.0),
            room(
                roomId = 8,
                roomType = "King",
                hotel_id = 1,
                Status = "Under Maintenance",
                price = 200.0
            ),

            )

        val sampleBookings = listOf(
            Booking(
                Booked_id = 1,
                HotelId = 1,
                ROOMTYPE = "Single",
                BookedStartDate = "15/05/2024",
                BookedEndDate = "20/05/2024",
                Pax = 2,
                Status = "Confirmed",
                Price = 100.0
            ),
            Booking(
                Booked_id = 2,
                HotelId = 1,
                ROOMTYPE = "Double",
                BookedStartDate = "16/05/2024",
                BookedEndDate = "22/05/2024",
                Pax = 2,
                Status = "Confirmed",
                Price = 150.0
            ),
            Booking(
                Booked_id = 3,
                HotelId = 1,
                ROOMTYPE = "King",
                BookedStartDate = "23/05/2024",
                BookedEndDate = "24/05/2024",
                Pax = 2,
                Status = "Confirmed",
                Price = 200.0
            )
        )

        hotelDao.insertHotels(sampleHotels)
        roomDao.insertRooms(sampleRooms)
        bookingDao.insertBookings(sampleBookings)
        Log.d("RoomRepository", "Sample data inserted")
    }


    fun getAllRooms(): Flow<List<room>> = roomDao.getAllRooms()


    suspend fun getRoomAvailability(
        hotelId: Int,
        roomType: String,
        startDate: String,
        endDate: String
    ): RoomAvailability {
        val allRooms = roomDao.getAllRoomsByType(hotelId, roomType)
        val bookings = roomDao.getBookingsForDateRange(hotelId, roomType, startDate, endDate)
        val underMaintenanceRooms = allRooms.count { it.Status == "Under Maintenance" }
        val occupiedRooms = bookings.count()
        val availableRooms = allRooms.count() - occupiedRooms - underMaintenanceRooms
        return RoomAvailability(availableRooms, occupiedRooms, underMaintenanceRooms)
    }


    data class RoomAvailability(
        val availableRooms: Int,
        val occupiedRooms: Int,
        val underMaintenanceRooms: Int
    )



    suspend fun updateRoomStatus(room: room) {
        roomDao.updateRoom(room)
    }

// End of file
}