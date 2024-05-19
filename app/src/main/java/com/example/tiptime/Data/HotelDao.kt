package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface HotelDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHotel(hotel: Hotel)

    @Query("SELECT * FROM hotel WHERE HotelName = :hotelName LIMIT 1")
    fun getHotelId(hotelName: String): Int


    @Query("SELECT * FROM hotel WHERE HotelAddress LIKE '%' || :hotelAddress || '%' " +
            "AND status = 'Available' AND pax >= :pax " +
            "AND NOT EXISTS (SELECT * FROM booking " +
            "WHERE hotel.HotelId = booking.HotelId " +
            "AND booking.BookedStartDate < :endDate " +
            "AND booking.BookedEndDate > :startDate)")
    fun getAvailableHotels(hotelAddress: String, startDate: String, endDate: String, pax: Int): List<Hotel>

    @Query("SELECT * FROM hotel WHERE status = 'Favorite'")
    fun getFavoriteHotels(): Flow<List<Hotel>>

    @Query("UPDATE hotel SET status = :newStatus WHERE HotelId = :hotelId")
    fun updateHotelStatus(hotelId: Int, newStatus: String)

    @Query("SELECT * FROM hotel")
    fun getAllHotels(): Flow<List<Hotel>>

}
