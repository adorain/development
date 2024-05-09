package com.example.tiptime

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tiptime.Data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewUserRegister : ViewModel(){
    private val _uiState = MutableStateFlow(User())
    val uiState: StateFlow<User> = _uiState.asStateFlow()
    var userPassword by mutableStateOf("")
    var userEmail by mutableStateOf("")
    var userId by mutableStateOf("")
    var userName by mutableStateOf("")
    var userPhoneNumber by mutableStateOf("")
    var userGender by mutableStateOf(' ')
    private val _uiRegisterState = MutableStateFlow(User())
    val uiRegisterState: StateFlow<User> = _uiRegisterState.asStateFlow()

    fun insertNewU_User(USERPassword: String, USEREmail: String, USERId: String, USERName: String,
                        USERPhoneNumber: String, USERGender: Char) {
        val userData = User(
            USERPassword,
            USERPhoneNumber,
            USEREmail,
            USERId,
            USERGender,
            USERName
        )

    }

    fun setNewUserPassword() : String{
        _uiRegisterState.update {
                uiRegisterState ->
            uiRegisterState.copy(
                UserPassword = userPassword
            )
        }
        return userPassword
    }

    fun updateUserPassword(UserPassword : String){
        userPassword = UserPassword
    }

    fun setUserEmail() : String{
        _uiRegisterState.update {
                uiRegisterState ->
            uiRegisterState.copy(
                UserEmail = userEmail
            )
        }
        return userEmail
    }

    fun updateUserEmail(UserEmail : String){
        userEmail = UserEmail
    }

    fun setUserPhoneNumber() : String{
        _uiRegisterState.update {
                uiRegisterState ->
            uiRegisterState.copy(
                UserPhoneNumber = userPhoneNumber
            )
        }
        return userPhoneNumber
    }

    fun updateUserPhoneNumber(UserPhoneNumber : String){
        userPhoneNumber = UserPhoneNumber
    }

    fun setUserName() : String{
        _uiRegisterState.update {
                uiRegisterState ->
            uiRegisterState.copy(
                UserName = userName
            )
        }
        return userName
    }

    fun updateUserName(UserName : String){
        userName = UserName
    }

    fun setUserId() : String{
        _uiRegisterState.update {
                uiRegisterState ->
            uiRegisterState.copy(
                UserId = userId
            )
        }
        return userId
    }

    fun updateUserId(UserId : String){
        userId = UserId
    }

    fun setUserGender() : Char{
        _uiRegisterState.update {
                uiRegisterState ->
            uiRegisterState.copy(
                UserGender = userGender
            )
        }
        return userGender
    }

    fun updateUserGender(UserGender : Char){
        userGender = UserGender
    }

}