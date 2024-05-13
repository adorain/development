package com.example.tiptime

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
class EditRoomsViewModel:ViewModel() {
    var selectedDate by mutableStateOf(LocalDate.now())
        private set

    var selectedRoom by mutableStateOf("")
        internal set

    var isMonthDropdownExpanded by mutableStateOf(false)
        private set

    var isYearDropdownExpanded by mutableStateOf(false)
        private set

    var roomAvailable by mutableStateOf(true)
        internal set

    var checkAv by mutableStateOf(false)
        private set

    var checkOc by mutableStateOf(false)
        private set

    var checkUnMa by mutableStateOf(false)
        private set

    var selectedStartDate by mutableStateOf(Date())
    var selectedEndDate by mutableStateOf(Date())

    fun onDateSelected(date: LocalDate) {
        selectedDate = date
    }

    fun toggleMonthDropdown() {
        isMonthDropdownExpanded = !isMonthDropdownExpanded
    }

    fun toggleYearDropdown() {
        isYearDropdownExpanded = !isYearDropdownExpanded
    }

    fun selectRoom(roomName: String) {
        selectedRoom = roomName
        roomAvailable = true // Reset availability
    }

    fun updateRoomAvailability(status: RoomStatus) {
        when (status) {
            RoomStatus.AVAILABLE -> {
                checkAv = true
                checkOc = false
                checkUnMa = false
            }
            RoomStatus.OCCUPIED -> {
                checkAv = false
                checkOc = true
                checkUnMa = false
            }
            RoomStatus.UNDER_MAINTENANCE -> {
                checkAv = false
                checkOc = false
                checkUnMa = true
            }
        }
    }

    fun updateStartDate(date: Date) {
        selectedStartDate = date
    }

    fun updateEndDate(date: Date) {
        selectedEndDate = date
    }
}

enum class RoomStatus {
    AVAILABLE,
    OCCUPIED,
    UNDER_MAINTENANCE
}