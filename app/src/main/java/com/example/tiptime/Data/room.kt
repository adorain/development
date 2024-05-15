package com.example.tiptime.Data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "room",foreignKeys = [ForeignKey(entity = Hotel::class, parentColumns = ["HotelId"], childColumns = ["hotel_id"])])
data class room(
    @PrimaryKey
    val roomType: String = "",
    val hotel_id: Int = 0,
    val price: Double = 0.00,
    val Status: String = ""
)

