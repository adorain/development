package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface HotelUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun hotelUser(hotel: Hotel)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStaff(hotel: Hotel)

    @Update
    suspend fun update(hotel: Hotel)

    @Query("UPDATE Hotel SET StaffName = :newStaffName, StaffPhoneNumber = :newStaffPhoneNumber, StaffEmail = :newStaffEmail, StaffPassword = :newStaffPassword")
    fun updateStaff(newStaffName: String, newStaffPhoneNumber: String, newStaffEmail: String, newStaffPassword: String)


}
