package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NormalUserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE userName = :userName AND userId = :userId LIMIT 1")
    suspend fun getUser(userName: String, userId: String): User?


    @Update
    suspend fun updateUser(user: User)

    @Query("UPDATE users SET userName = :newUserName, userPhoneNumber = :newUserPhoneNumber, userEmail = :newUserEmail, userPassword = :newUserPassword WHERE userId = :userId")
    suspend fun updateUserDetails(userId: String, newUserName: String, newUserPhoneNumber: String, newUserEmail: String, newUserPassword: String)



}


