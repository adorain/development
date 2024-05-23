package com.example.tiptime.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @PrimaryKey val userId: String,
    val userName: String,
    val userPhoneNumber: String,
    val userEmail: String,
    val userPassword: String
)
