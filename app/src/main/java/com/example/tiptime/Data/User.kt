package com.example.tiptime.Data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(tableName = "user" , foreignKeys = [ForeignKey(entity = Login::class, parentColumns = ["Email"], childColumns = ["UserEmail"])])
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val UserId : String,
    @ColumnInfo(name = "name")
    val Name:String,
    @ColumnInfo(name = "phone_number")
    val PhoneNumber : String,
    @ColumnInfo(name = "user_email")
    val UserEmail :String,
    @ColumnInfo(name = "gender")
    val Gender : Char,
    @ColumnInfo(name = "status")
    val Status : String
)
