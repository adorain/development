package com.example.tiptime.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Staff")
data class Staff(
    @PrimaryKey(autoGenerate = true)
    val StaffId : String = "",
    val StaffName : String = "",
    val StaffPhoneNumber : String= "",
    val StaffEmail : String="",
    val StaffGender : Char='M',
    val StaffStatus: String = "",
    val StaffPassword : String = ""
)