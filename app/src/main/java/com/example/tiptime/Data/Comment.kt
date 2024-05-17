package com.example.tiptime.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Comment")
data class Comment (
    @PrimaryKey(autoGenerate = true)

    val rating: String = "",

    val commentText: String = ""
)