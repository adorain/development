package com.example.tiptime.Data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

data class Booking(

    val Booked_id : String,

    val HotelId : String,

    val BookedStartDate : Date,

    val BookedEndDate : Date,

    val Status : String,

    val PaymentMethod : String
)
