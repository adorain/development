package com.example.tiptime.Data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date
@Entity(tableName = "payment" , foreignKeys = [ForeignKey(entity = Booking::class, parentColumns = ["Booked_Id"], childColumns = ["BookedId"])])
data class Payment(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "payment_id")
    val PaymentId : String,
    @ColumnInfo(name = "booked_id")
    val BookedId : String,
    @ColumnInfo(name =  "payment_date")
    val PaymentDate : Date
)
