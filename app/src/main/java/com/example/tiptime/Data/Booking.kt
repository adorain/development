package com.example.tiptime.Data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "Booking" ,foreignKeys = [ForeignKey(entity = Hotel::class, parentColumns = ["HotelId"], childColumns = ["HotelId"])
    ,ForeignKey(entity = Room::class, parentColumns = ["roomId"], childColumns = ["RoomId"])])
data class Booking(
    @PrimaryKey(autoGenerate = true)
    val Booked_id : String = "",

    val HotelId : String = "",
    val roomId : String ="",
    val ROOMTYPE : String = "",
    val BookedStartDate : Date = Date(),

    val BookedEndDate : Date = Date(),
    val Pax : Int = 0,
    val Status : String = "",
    val Price : Double= 0.00

)
