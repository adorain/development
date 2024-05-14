package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HotelUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun hotelUser(staff: Staff)

    @Query("SELECT * FROM staff WHERE StaffName = :staffName AND StaffId = :staffId LIMIT 1")
    fun getStaff(staffName: String, staffId: String):String

}