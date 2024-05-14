package com.example.tiptime

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiptime.Data.room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

enum class RoomStatus {
    AVAILABLE, OCCUPIED, UNDER_MAINTENANCE
}

class EditRoomsViewModel : ViewModel() {
    var rooms = listOf<com.example.tiptime.Data.room>()
    var selectedRoom by mutableStateOf("")
    var availableRooms by mutableStateOf(0)
    var occupiedRooms by mutableStateOf(0)
    var underMaintenanceRooms by mutableStateOf(0)
    var selectedStartDate: Long = 0
    var selectedEndDate: Long = 0
    var checkAv by mutableStateOf(false)
    var checkOc by mutableStateOf(false)
    var checkUnMa by mutableStateOf(false)
    var roomAvailable by mutableStateOf(false)

    // Fetch rooms from the repository
    fun fetchRoomsForDateRangeAndType(hotelId: String, roomType: String, startDate: Long, endDate: Long) {
        // Assuming you have a repository to fetch data from the database
        viewModelScope.launch {
            rooms = withContext(Dispatchers.IO) {
                // Replace this with the actual repository call
                // repository.getRoomsForDateRangeAndType(hotelId, roomType, startDate, endDate)
                listOf()  // Example placeholder
            }
            updateRoomCounts()
        }
    }

    fun updateRoomCounts() {
        availableRooms = rooms.count { it.Status == "Available" }
        occupiedRooms = rooms.count { it.Status == "Occupied" }
        underMaintenanceRooms = rooms.count { it.Status == "Under Maintenance" }
    }

    fun selectRoom(roomId: String) {
        selectedRoom = roomId
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

    fun updateRoomStatus(room: room) {
        // Assuming you have a repository to update the room status in the database
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                // Replace this with the actual repository call to update the room status
                // repository.updateRoomStatus(room)
            }
        }
    }

    fun updateStartDate(date: Date) {
        selectedStartDate = date.time
    }

    fun updateEndDate(date: Date) {
        selectedEndDate = date.time
    }
}
