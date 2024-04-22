package com.example.tiptime.Data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "staff" , foreignKeys = [ForeignKey(entity = Login::class, parentColumns = ["Email"] , childColumns = ["StaffEmail"])])
data class Staff(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "staff_id")
    val StaffId : String,
    @ColumnInfo(name = "staff_name")
    val StaffName : String,
    @ColumnInfo(name = "phone_number")
    val PhoneNumber : String,
    @ColumnInfo(name = "staff_email")
    val StaffEmail : String,
    @ColumnInfo(name = "gender")
    val Gender : Char
)
