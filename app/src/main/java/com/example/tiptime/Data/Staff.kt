package com.example.tiptime.Data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


data class Staff(
    val StaffId : String,
    val StaffName : String,
    val PhoneNumber : String,
    val StaffEmail : String,
    val Gender : Char
)
