package com.example.tiptime.Data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Room",foreignKeys = [ForeignKey(entity = Hotel::class, parentColumns = ["HotelId"], childColumns = ["hotel_id"])])
data class Room(
    @PrimaryKey    val roomId:String = "",
    val roomType: String = "",
    val hotel_id: String= "",
    val price: Double = 0.00,
    var Status: String = ""

)

