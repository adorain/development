package com.example.tiptime.Data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.text.DateFormat
import java.util.Date



@Entity(tableName = "Booking" ,/*foreignKeys = [ForeignKey(entity = Hotel::class, parentColumns = ["HotelId"], childColumns = ["HotelId"], onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE)

    ,ForeignKey(entity = room::class, parentColumns = ["roomType"], childColumns = ["ROOMTYPE"], onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE)]*/)
data class Booking(
    @PrimaryKey(autoGenerate = true)
    val Booked_id : Int = 0,

    val HotelId : Int = 0,
    val ROOMTYPE : String = "",
    val BookedStartDate : String = "",

    val BookedEndDate : String = "",
    val Pax : Int = 0,
    val Status : String = "",
    val Price : Double= 0.00

)
