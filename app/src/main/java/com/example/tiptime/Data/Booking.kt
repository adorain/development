package com.example.tiptime.Data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.text.DateFormat
import java.util.Date


@Entity(tableName = "Booking" ,foreignKeys = [ForeignKey(entity = Hotel::class, parentColumns = ["HotelId"], childColumns = ["HotelId"])
    ,ForeignKey(entity = room::class, parentColumns = ["roomType"], childColumns = ["ROOMTYPE"])])
data class Booking(
    @PrimaryKey(autoGenerate = true)
    val Booked_id : String = "",

    val HotelId : String = "",
    val ROOMTYPE : String = "",
    val BookedStartDate : Date = Date(),

    val BookedEndDate : Date = Date(),
    val Pax : Int = 0,
    val Status : String = "",
    val Price : Double= 0.00

)
