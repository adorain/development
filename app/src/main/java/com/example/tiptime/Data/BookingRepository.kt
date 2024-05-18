package com.example.tiptime.Data

class BookingRepository(private val bookingDao: BookingDao) {

    suspend fun getAllBookings(): List<Booking> {
        return bookingDao.getAllBookings()
    }

    suspend fun updateBookingStatus(bookingId: Int, status: String) {
        bookingDao.updateBookingStatus(bookingId, status)
    }

    suspend fun insertSampleData() {
        val sampleHotels = listOf(
            Hotel(HotelId = 1, HotelName = "Hotel One", HotelAddress = "Address One", Type = "Luxury", Status = "Available", StaffId = "Staff1", UserId = "User1", HotelDesciption = "Description One", Pax = 2)
        )

        val sampleRooms = listOf(
            room(roomId = 1, roomType = "Single", hotel_id = 1, Status = "Available", price = 100.0),
            room(roomId = 2, roomType = "Single", hotel_id = 1, Status = "Occupied", price = 100.0),
            room(roomId = 3, roomType = "Double", hotel_id = 1, Status = "Under Maintenance", price = 150.0),
            room(roomId = 4, roomType = "Double", hotel_id = 1, Status = "Available", price = 150.0),
            room(roomId = 5, roomType = "King", hotel_id = 1, Status = "Available", price = 200.0)
        )

        val sampleBookings = listOf(
            Booking(Booked_id = 1, HotelId = 1, ROOMTYPE = "Single", BookedStartDate = "15/05/2024", BookedEndDate = "20/05/2024", Pax = 2, Status = "Confirmed", Price = 100.0),
            Booking(Booked_id = 2, HotelId = 1, ROOMTYPE = "Double", BookedStartDate = "16/05/2024", BookedEndDate = "22/05/2024", Pax = 2, Status = "Confirmed", Price = 150.0)
        )

        bookingDao.insertBookings(sampleBookings)
    }
}