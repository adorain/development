package com.example.tiptime.Data

import androidx.room.Entity

@Entity(tableName = "User")
data class User(

    val UserId: String = "",
    val UserName:String = "",
    val UserPhoneNumber: String = "",
    val UserEmail:String = "",
    val UserGender : Char='M',
    val UserStatus: String = "",
    val UserPassword: String = ""
)
