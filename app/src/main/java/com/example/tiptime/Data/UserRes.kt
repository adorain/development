package com.example.tiptime.Data

interface UserRes {
    suspend fun updateUser(newUserName: String, newUserPhoneNumber: String, newUserEmail: String, newUserPassword: String, userId: String)
}
