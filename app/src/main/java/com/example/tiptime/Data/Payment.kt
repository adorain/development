package com.example.tiptime.Data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

data class Payment(

    val PaymentId : String,

    val BookedId : String,

    val PaymentDate : Date
)
