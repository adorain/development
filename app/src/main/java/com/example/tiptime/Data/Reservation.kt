package com.example.tiptime.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Reservation")
data class Reservation(
    @PrimaryKey(autoGenerate = true)
    val Booked_id: Int = 0,

    val HotelId: Int = 0,
    val ROOMTYPE: String = "",
    val BookedStartDate: String = "",

    val BookedEndDate: String = "",
    val Pax: Int = 0,
    val Status: String = "",
    val Price: Double= 0.00

)