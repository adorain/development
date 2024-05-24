package com.example.tiptime

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiptime.Data.NormalUserDao
import com.example.tiptime.Data.User
import com.example.tiptime.Data.UserOfflineRes
import kotlinx.coroutines.launch

class NewUserRegister(private val normalUserDao: NormalUserDao) : ViewModel() {
    private val userRes: UserOfflineRes = UserOfflineRes(normalUserDao)
    var name: String = ""
    var phoneNumber: String = ""
    var email: String = ""
    var password: String = ""

    fun updateUser(name: String, phoneNumber: String, email: String, password: String, userId: String) {
        viewModelScope.launch {
            Log.d("NewUserRegister", "Updating user: $name, $phoneNumber, $email, $password, $userId")
            userRes.updateUser(name, phoneNumber, email, password, userId)
        }
    }

    fun insertUser(name: String, phoneNumber: String, email: String, password: String, userId: String) {
        val user = User(userId =userId, userName = name, userPhoneNumber = phoneNumber, userEmail =  email, userPassword =  password)
        viewModelScope.launch {
            normalUserDao.insertUser(user)
            Log.d("NewUserRegister","UserInserted: $user")
        }
    }
}