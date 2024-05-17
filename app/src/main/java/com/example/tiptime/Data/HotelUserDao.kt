package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Query

/*
@Dao
interface HotelUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun hotelUser(staff: Staff)

    @Query("SELECT * FROM staff WHERE StaffName = :staffName AND StaffId = :staffId LIMIT 1")
    fun getStaff(staffName: String, staffId: String):String

}*/
@Dao
interface HotelUserDao {
    @Query("SELECT * FROM staff WHERE staffId = :staffId AND staffName = :staffName")
    fun getStaff(staffId: String, staffName: String): List<Staff>
}