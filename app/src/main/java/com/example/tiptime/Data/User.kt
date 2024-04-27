package com.example.tiptime.Data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

data class User(

    val UserId : String = "",
    val Name:String = "",
    val PhoneNumber : String = "",
    val UserEmail :String = "",
    val Gender : Char = 'M',
    val Status : String = ""
)
