package com.example.tiptime.Data

interface UserRes {
    fun updateUser(newUserName: String, newUserPhoneNumber: String, newUserEmail: String,
                    newUserPassword: String)

}