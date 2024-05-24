package com.example.tiptime

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.SemanticsProperties.Password
import androidx.lifecycle.ViewModel
import com.example.tiptime.Data.Booking
import com.example.tiptime.Data.Login
import com.example.tiptime.Data.User
import com.example.tiptime.Data.room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date

class UserRegister : ViewModel(){
    private val _uiState = MutableStateFlow(Login())
    val uiState: StateFlow<Login> = _uiState.asStateFlow()
    var userPassword by mutableStateOf("")
    var userEmail by mutableStateOf("")
    private val _uiRegisterState = MutableStateFlow(Login())
    val uiRegisterState: StateFlow<Login> = _uiRegisterState.asStateFlow()

    fun insertNewU_User(Password: String, Email: String) {
        val userData = Login(
            Password,
            Email,
        )

    }

    fun setUserPassword() : String{
        _uiRegisterState.update {
                uiRegisterState ->
            uiRegisterState.copy(
                Password = userPassword
            )
        }
        return userPassword
    }

    fun updateUserPassword(Password : String){
        userPassword = Password
    }

    fun setUserEmail() : String{
        _uiRegisterState.update {
                uiRegisterState ->
            uiRegisterState.copy(
                Email = userEmail
            )
        }
        return userEmail
    }

    fun updateUserEmail(Email : String){
        userEmail = Email
    }

}