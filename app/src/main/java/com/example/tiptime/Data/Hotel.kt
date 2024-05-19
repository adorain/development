package com.example.tiptime.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Hotel")
data class Hotel(
    @PrimaryKey(autoGenerate = true)
    val HotelId : Int = 0,

    val StaffId : String="",

    val UserId : String="",

    val HotelName : String="",
    val StaffName : String="",

    val HotelAddress : String="",
    val HotelDescription : String = "",
    val Rating : Int =0,
    val Pax : Int  = 0,
    val Type : String="",

    val Status: String="",

    val StaffPhoneNumber: String = "",
    val StaffEmail:String = "",
    val StaffPassword:String=" "

)
