package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

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

    @Query("SELECT * FROM hotel WHERE status = 'Favourite'")
    fun getFavoriteHotels(): Flow<List<Hotel>>

    @Query("UPDATE hotel SET status = :newStatus WHERE HotelId = :hotelId")
    fun updateHotelStatus(hotelId: Int, newStatus: String)

    @Query("SELECT * FROM hotel")
    fun getAllHotels(): Flow<List<Hotel>>


    @Query("SELECT * FROM hotel")
    fun insertHotel() : Flow<List<Hotel>>

    @Query("UPDATE Hotel SET HotelDescription = :newDescription, Rating = :newRating WHERE HotelId = :hotelId")
    fun updateDescription(hotelId: Int, newDescription: String, newRating: Int)

    @Query("UPDATE Hotel SET StaffName = :newStaffName, StaffPhoneNumber = :newStaffPhoneNumber,StaffEmail = :newStaffEmail, StaffPassword = :newStaffPassword")
    fun updateStaff(newStaffName: String, newStaffPhoneNumber: String, newStaffEmail: String,
                    newStaffPassword: String)
    @Query("UPDATE Hotel SET Status = :Status WHERE HotelId = :hotelId")
    fun updateHotelStatusToFavourite(hotelId: Int,Status:String)


    @Query("""
        SELECT * FROM Hotel h
        WHERE (:startDate IS NULL OR h.HotelId IN (
            SELECT HotelId FROM Booking
            WHERE (BookedStartDate >= :startDate AND BookedEndDate <= :endDate)))
        AND (:pax IS NULL OR h.Pax >= :pax)
        AND (:searchText IS NULL OR h.HotelName LIKE '%' || :searchText || '%')
    """)
    fun filterHotels(
        startDate: String?,
        endDate: String?,
        pax: Int?,
        searchText: String?
    ): List<Hotel>

    @Query("SELECT * FROM hotel WHERE HotelId = :hotelId")
    suspend fun getHotelById(hotelId: Int): Hotel

    @Query("SELECT * FROM Hotel WHERE HotelId = :hotelId")
    suspend fun getHotelDetails(hotelId: Int): Hotel

    @Query("SELECT * FROM hotel")
    fun getAllHotelsBooked(): List<Hotel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotels(hotels: List<Hotel>)


}
