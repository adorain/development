//package com.example.tiptime
//
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import com.example.tiptime.Data.HotelRes
//import com.example.tiptime.Data.User
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.update
//
//class userViewModel (private val hotelRes: HotelRes) : ViewModel() {
//
//    private val _uiState = MutableStateFlow(User())
//    var UserName by mutableStateOf("")
//    var UserAddress by mutableStateOf("")
//    var UserPhoneNumber by mutableStateOf("")
//    var UserPassword by mutableStateOf("")
//
//    fun setUserName(userName: String) {
//        _uiState.update { currentState ->
//            currentState.copy(
//                UserName = userName
//            )
//        }
//    }
//
//
//    fun setUserPassword(userPassword: String) {
//        _uiState.update { currentState ->
//            currentState.copy(
//                UserPassword = userPassword
//            )
//        }
//    }
//
//    fun setUserPhoneNumber(userPhoneNumber: String) {
//        _uiState.update { currentState ->
//            currentState.copy(
//                UserPhoneNumber = userPhoneNumber
//            )
//        }
//    }
//
//    fun setUserEmail(userEmail: String) {
//        _uiState.update { currentState ->
//            currentState.copy(
//                UserEmail = userEmail
//            )
//        }
//    }
//
//
//}