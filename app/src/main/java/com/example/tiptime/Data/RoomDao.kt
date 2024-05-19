package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    @Insert
    suspend fun addNewRoom(room: room)

    @Query("SELECT price FROM room WHERE hotel_id = :hotelId AND roomType = :roomType LIMIT 1")
    fun checkRoomPrice(hotelId: Int, roomType: String): Double

    @Query("SELECT roomType FROM room WHERE hotel_id = :hotelId LIMIT 1")
    fun findRoomType(hotelId: Int): String

    @Query("""
        SELECT * FROM room WHERE hotel_id = :hotelId AND roomType = :roomType AND roomId IN (
            SELECT roomId FROM booking WHERE 
            (BookedStartDate BETWEEN :startDate AND :endDate) OR 
            (BookedEndDate BETWEEN :startDate AND :endDate)
        )
    """)
    fun getRoomsForDateRangeAndType(hotelId: Int, roomType: String, startDate: Long, endDate: Long): List<room>


    @Query("SELECT MIN(price) || ' - ' || MAX(price) FROM room WHERE hotel_id = :hotelId")
    fun getPriceRange(hotelId: Int): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRooms(rooms: List<room>)

    @Query("SELECT * FROM room")
    fun getAllRooms(): Flow<List<room>>

    @Query("SELECT * FROM room WHERE hotel_id = :hotelId AND roomType = :roomType")
    fun getAllRoomsByType(hotelId: Int, roomType: String): List<room>

    @Query("""
        SELECT * FROM room WHERE hotel_id = :hotelId AND roomType = :roomType
    """)
    fun getAllRoomsForType(hotelId: Int, roomType: String): List<room>

    @Query("""
        SELECT * FROM room WHERE hotel_id = :hotelId AND roomType = :roomType
        AND roomId NOT IN (
            SELECT roomId FROM Booking WHERE
            (BookedStartDate <= :endDate AND BookedEndDate >= :startDate)
        )
    """)
    fun getAvailableRoomsForDateRange(hotelId: Int, roomType: String, startDate: String, endDate: String): List<room>

    @Query("""
        SELECT * FROM Booking WHERE hotelId = :hotelId AND ROOMTYPE = :roomType
        AND ((BookedStartDate BETWEEN :startDate AND :endDate) OR 
             (BookedEndDate BETWEEN :startDate AND :endDate) OR
             (BookedStartDate <= :startDate AND BookedEndDate >= :endDate))
    """)
    fun getBookingsForDateRange(hotelId: Int, roomType: String, startDate: String, endDate: String): List<Booking>


    @Query("SELECT * FROM room WHERE hotel_id = :hotelId AND roomType = :roomType")
    suspend fun getRoom(hotelId: Int, roomType: String): room


    @Update
    suspend fun updateRoom(room: room)

    @Query("UPDATE hotel SET HotelName = :hotelName, HotelAddress = :hotelAddress,HotelDescription =:hotelDescription WHERE HotelId = :hotelId")
    suspend fun updateHotelDetails(hotelId: Int, hotelName: String, hotelAddress: String,hotelDescription:String)

    @Query("UPDATE room SET roomType = :roomType WHERE hotel_Id = :hotelId AND roomType = :roomType")
    suspend fun updateRoomType(hotelId: Int, roomType: String)

    @Query("SELECT price FROM room WHERE hotel_id = :hotelId AND roomType = :roomType LIMIT 1")
    suspend fun getRoomPrice(hotelId: Int, roomType: String): Double

    @Query("UPDATE room SET price = :newPrice WHERE hotel_id = :hotelId AND roomType = :roomType")
    suspend fun updateRoomPrice(hotelId: Int, roomType: String, newPrice: Double)

    @Query("SELECT roomType FROM room WHERE hotel_id = :hotelId")
    suspend fun getRoomTypes(hotelId: Int): List<String>



}