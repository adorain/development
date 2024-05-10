package com.example.tiptime.Data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(tableName = "Hotel")
data class Hotel(
    @PrimaryKey(autoGenerate = true)
    val HotelId : String = "",

    val StaffId : String="",

    val UserId : String="",

    val HotelName : String="",

    val HotelAddress : String="",
    val HotelDesciption : String = "",
    val Pax : Int  = 0,
    val Type : String="",

    val Status: String=""

)
