package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import java.util.Date

@Dao
interface RoomDao {
    @Insert
    suspend fun addNewRoom(room: Room)

    @Query("SELECT price FROM room WHERE hotel_id = :hotelId AND roomType = :roomType LIMIT 1")
    fun checkRoomPrice(hotelId: String, roomType: String): Double

    @Query("SELECT roomType FROM room WHERE hotel_id = :hotelId LIMIT 1")
    fun findRoomType(hotelId: String): String

    @Query("SELECT MIN(price) || ' - ' || MAX(price) FROM room WHERE hotel_id = :hotelId")
    fun getPriceRange(hotelId: String): String

    @Query("SELECT * FROM room JOIN Booking ON ROOMTYPE = roomType WHERE BookedStartDate <= :BookingStartDate AND BookedEndDate >= :BookingEndDate AND Status = 'Completed'")
    fun checkRoomStatus(hotelId: String , roomType: String , BookingStartDate : Date,BookingEndDate : Date):Boolean
}