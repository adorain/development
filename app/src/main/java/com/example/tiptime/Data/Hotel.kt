package com.example.tiptime.Data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

data class Hotel(

    val HotelId : String = "",

    val StaffId : String="",

    val UserId : String="",

    val HotelName : String="",

    val HotelAddress : String="",

    val Type : String="",

    val Status: String=""

)
