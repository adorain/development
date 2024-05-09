package com.example.tiptime

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tiptime.Data.Staff
import com.example.tiptime.Data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewHotelRegister : ViewModel(){
    private val _uiState = MutableStateFlow(Staff())
    val uiState: StateFlow<Staff> = _uiState.asStateFlow()
    var staffPassword by mutableStateOf("")
    var staffEmail by mutableStateOf("")
    var staffId by mutableStateOf("")
    var staffName by mutableStateOf("")
    var staffPhoneNumber by mutableStateOf("")
    var staffGender by mutableStateOf(' ')
    private val _uiRegisterState = MutableStateFlow(Staff())
    val uiRegisterState: StateFlow<Staff> = _uiRegisterState.asStateFlow()

    fun insertNewU_User(STAFFPassword: String, STAFFEmail: String, STAFFId: String, STAFFName: String,
                        STAFFPhoneNumber: String, STAFFGender: Char) {
        val userData = User(
            STAFFPassword,
            STAFFPhoneNumber,
            STAFFEmail,
            STAFFId,
            STAFFGender,
            STAFFName
        )

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

    fun setStaffGender() : Char{
        _uiRegisterState.update {
                uiRegisterState ->
            uiRegisterState.copy(
                StaffGender = staffGender
            )
        }
        return staffGender
    }

    fun updateUserGender(StaffGender : Char){
        staffGender = StaffGender
    }

}
