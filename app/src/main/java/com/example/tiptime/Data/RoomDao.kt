package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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

    @Query("SELECT MIN(price) || ' - ' || MAX(price) FROM room WHERE hotel_id = :hotelId")
    fun getPriceRange(hotelId: Int): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRooms(rooms: List<room>)

    @Query("SELECT * FROM room")
    fun getAllRooms(): Flow<List<room>>

    @Query("""
        SELECT * FROM room WHERE hotel_id = :hotelId AND roomType = :roomType AND roomId IN (
            SELECT roomId FROM Booking WHERE 
            (BookedStartDate BETWEEN :startDate AND :endDate) OR 
            (BookedEndDate BETWEEN :startDate AND :endDate)
        )
    """)
    fun getRoomsForDateRangeAndType(hotelId: Int, roomType: String, startDate: Long, endDate: Long): List<room>

    @Update
    suspend fun updateRoom(room: room)

}