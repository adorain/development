package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface RoomDao {
    @Insert
    suspend fun addNewRoom(room: room)

    @Query("SELECT * FROM room WHERE hotel_id = :hotelId")
    fun checkRoomPrice(hotelId: Int): Flow<List<room>>

    @Query("SELECT roomType FROM room WHERE hotel_id = :hotelId LIMIT 1")
    fun findRoomType(hotelId: Int): String

    @Query("SELECT MIN(price) || ' - ' || MAX(price) FROM room WHERE hotel_id = :hotelId")
    fun getPriceRange(hotelId: Int): Flow<String>

}