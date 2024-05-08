package com.example.tiptime.Data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

data class User(

    val UserId: String = "",
    val UserName:String = "",
    val UserPhoneNumber: String = "",
    val UserEmail:String = "",
    val UserGender : Char='M',
    val UserStatus: String = "",
    val UserPassword: String = ""
)
