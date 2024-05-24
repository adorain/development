package com.example.tiptime

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiptime.Data.Hotel
import com.example.tiptime.Data.HotelDao
import com.example.tiptime.Data.HotelRes
import com.example.tiptime.Data.HotelUserDao
import com.example.tiptime.Data.StaffRes
import com.example.tiptime.Data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class NewHotelRegister(private val hotelUserDao: HotelUserDao) : ViewModel(){
    private val _uiState = MutableStateFlow(Hotel())
    val uiState: StateFlow<Hotel> = _uiState.asStateFlow()
    private val _uiRegisterState = MutableStateFlow(Hotel())
    val uiRegisterState: StateFlow<Hotel> = _uiRegisterState.asStateFlow()

    var staffPassword by mutableStateOf("")
    var staffEmail by mutableStateOf("")
    var staffId by mutableStateOf(UUID.randomUUID().toString())
    var staffName by mutableStateOf("")
    var staffPhoneNumber by mutableStateOf("")
    var hotelName by mutableStateOf("")
    var hotelAddress by mutableStateOf("")
    var hotelDescription by mutableStateOf("")
    var hotelType by mutableStateOf("")

//    fun insertNewU_User(STAFFPassword: String, STAFFEmail: String, STAFFId: String, STAFFName: String,
//                        STAFFPhoneNumber: String, STAFFGender: Char) {
//        val userData = User(
//            STAFFPassword,
//            STAFFPhoneNumber,
//            STAFFEmail,
//            STAFFId,
//            STAFFGender,
//            STAFFName
//        )
//
//    }

    fun insertStaff(
        name: String, phoneNumber: String, email: String, password: String,
        hotelName: String, hotelAddress: String, hotelDescription: String, type: String
    ) {
        viewModelScope.launch {
            val staff = Hotel(
                StaffId = UUID.randomUUID().toString(),
                UserId = UUID.randomUUID().toString(),
                StaffName = name,
                StaffPhoneNumber = phoneNumber,
                StaffEmail = email,
                StaffPassword = password,
                HotelName = hotelName,
                HotelAddress = hotelAddress,
                HotelDescription = hotelDescription,
                Type = type,
                Rating = 5,
                Status = "Available"
            )
            hotelUserDao.hotelUser(staff)
        }
    }

    fun setNewStaffPassword() : String{
        _uiRegisterState.update {
                uiRegisterState ->
            uiRegisterState.copy(
                StaffPassword = staffPassword
            )
        }
        return staffPassword
    }

    fun updateStaffPassword(StaffPassword : String){
        staffPassword = staffPassword
    }

    fun setStaffEmail() : String{
        _uiRegisterState.update {
                uiRegisterState ->
            uiRegisterState.copy(
                StaffEmail = staffEmail
            )
        }
        return staffEmail
    }

    fun updateStaffEmail(StaffEmail : String){
        staffEmail = StaffEmail
    }



    fun setStaffPhoneNumber() : String{
        _uiRegisterState.update {
                uiRegisterState ->
            uiRegisterState.copy(
                StaffPhoneNumber = staffPhoneNumber
            )
        }
        return staffPhoneNumber
    }

    fun updateStaffPhoneNumber(StaffPhoneNumber : String){
        staffPhoneNumber = StaffPhoneNumber
    }

    fun setStaffName() : String{
        _uiRegisterState.update {
                uiRegisterState ->
            uiRegisterState.copy(
                StaffName = staffName
            )
        }
        return staffName
    }

    fun updateStaffName(StaffName : String){
        staffName = StaffName
    }

    fun setStaffId() : String{
        _uiRegisterState.update {
                uiRegisterState ->
            uiRegisterState.copy(
                StaffId = staffId
            )
        }
        return staffId
    }

    fun updateStaffId(StaffId : String){
        staffId = StaffId
    }
    /*
        fun setStaffGender() : Char{
            _uiRegisterState.update {
                    uiRegisterState ->
                uiRegisterState.copy(
                    StaffGender = staffGender
                )
            }
            return staffGender
        }


     */


    fun updateStaff(newStaffName: String, newStaffPhoneNumber: String, newStaffEmail: String, newStaffPassword: String) {
        viewModelScope.launch {
            hotelUserDao.updateStaff(newStaffName, newStaffPhoneNumber, newStaffEmail, newStaffPassword)
        }
    }

}


