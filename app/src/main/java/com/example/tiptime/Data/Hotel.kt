package com.example.tiptime.Data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(tableName = "hotel" , foreignKeys = [ForeignKey(entity = User::class , parentColumns = ["UserId"], childColumns = ["UserId"]),ForeignKey(entity = Staff::class, parentColumns = ["StaffId"], childColumns = ["StaffId"])])
data class Hotel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "hotel_id")
    val HotelId : String,
    @ColumnInfo(name = "staff_id")
    val StaffId : String,
    @ColumnInfo(name = "user_id")
    val UserId : String,
    @ColumnInfo(name =  "hotel_name")
    val HotelName : String,
    @ColumnInfo(name = "hotel_address")
    val HotelAddress : String,
    @ColumnInfo(name = "pax")
    val Pax : Int,
    @ColumnInfo(name = "type")
    val Type : String,


)
