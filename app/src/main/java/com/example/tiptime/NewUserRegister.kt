package com.example.tiptime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiptime.Data.NormalUserDao
import com.example.tiptime.Data.UserOfflineRes
import kotlinx.coroutines.launch

class NewUserRegister(private val normalUserDao: NormalUserDao) : ViewModel() {
    private val userRes: UserOfflineRes = UserOfflineRes(normalUserDao)

    fun updateUser(name: String, phoneNumber: String, email: String, password: String, userId: String) {
        viewModelScope.launch {
            userRes.updateUser(name, phoneNumber, email, password, userId)
        }
    }
}

