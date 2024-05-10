package com.example.tiptime.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Room")
data class room(
    @PrimaryKey
    val roomType: String = "",
    val hotel_id: String= "",
    val price: Double = 0.00,
    val Status: String = ""
)
