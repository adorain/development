package com.example.tiptime.Data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date
@Entity(tableName = "booking" , foreignKeys = [ForeignKey(entity = Hotel::class, parentColumns = ["HotelId"], childColumns = ["HotelId"])])
data class Booking(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "booking_id")
    val Booked_id : String,
    @ColumnInfo(name = "hotel_id")
    val HotelId : String,
    @ColumnInfo(name = "start_date")
    val BookedStartDate : Date,
    @ColumnInfo(name = "end_date")
    val BookedEndDate : Date,
    @ColumnInfo(name = "status")
    val Status : String,
    @ColumnInfo(name = "paymentMethod")
    val PaymentMethod : String
)
