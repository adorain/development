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

    @Query("SELECT * FROM user WHERE UserName = :userName AND UserId = :userId LIMIT 1")
    fun getUser(userName: String, userId: String):String

    @Update
    suspend fun update(hotel: Hotel)

    /*@Query("SELECT * FROM hotel WHERE staffId = :staffId AND staffName = :staffName")
    fun getStaff(staffId: String, staffName: String): List<Hotel>*/

    @Query("UPDATE Hotel SET StaffName = :newStaffName, StaffPhoneNumber = :newStaffPhoneNumber,StaffEmail = :newStaffEmail, StaffPassword = :newStaffPassword")
    fun updateStaff(newStaffName: String, newStaffPhoneNumber: String, newStaffEmail: String,
                    newStaffPassword: String)


}
