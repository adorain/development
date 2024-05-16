package com.example.tiptime

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tiptime.Data.Booking
import com.example.tiptime.Data.Login
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HotelRegister : ViewModel(){
    private val _uiState = MutableStateFlow(Login())
    val uiState: StateFlow<Login> = _uiState.asStateFlow()
    var hotelPassword by mutableStateOf("")
    var hotelEmail by mutableStateOf("")
    private val _uiRegisterState = MutableStateFlow(Login())
    val uiRegisterState: StateFlow<Login> = _uiRegisterState.asStateFlow()

    fun insertNewH_User(Password: String, Email: String) {
        val userData = Login(
            Password,
            Email,
        )

    }



    fun setHotelPassword() : String{
        _uiRegisterState.update {
                uiRegisterState ->
            uiRegisterState.copy(
                Password = hotelPassword
            )
        }
        return hotelPassword
    }

    fun updateHotelPassword(Password : String){
        hotelPassword = Password
    }

    fun setHotelEmail() : String{
        _uiRegisterState.update {
                uiRegisterState ->
            uiRegisterState.copy(
                Email = hotelEmail
            )
        }
        return hotelEmail
    }

    fun updateHotelEmail(Email : String){
        hotelEmail = Email
    }

}
