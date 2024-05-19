package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NormalUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun normalUser(user: User)

    @Query("SELECT * FROM user WHERE UserName = :userName AND UserId = :userId LIMIT 1")
    fun getUser(userName: String, userId: String):String

    @Update
    suspend fun update(user: User)

    @Query("UPDATE User SET UserName = :newUserName, UserPhoneNumber = :newUserPhoneNumber,UserEmail = :newUserEmail, UserPassword = :newUserPassword")
    fun updateUser(newUserName: String, newUserPhoneNumber: String, newUserEmail: String,
                          newUserPassword: String)

}