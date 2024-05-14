package com.example.tiptime.Data

interface UserRes {
    fun getUserName(userName: String, userId: String): List<User>
}