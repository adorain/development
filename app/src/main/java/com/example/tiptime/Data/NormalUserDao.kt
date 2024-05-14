package com.example.tiptime.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NormalUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun normalUser(user: User)

    @Query("SELECT * FROM user WHERE UserName = :userName AND UserId = :userId LIMIT 1")
    fun getUser(userName: String, userId: String):String

}