package com.example.tiptime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiptime.Data.room
import com.example.tiptime.Data.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class RoomStatus {
    AVAILABLE, OCCUPIED, UNDER_MAINTENANCE
}

class EditRoomsViewModel(val repository: RoomRepository) : ViewModel() {
    private val _rooms = MutableStateFlow<List<room>>(emptyList())
    val rooms: StateFlow<List<room>> get() = _rooms

    private val _selectedRoom = MutableStateFlow<room?>(null)
    val selectedRoom: StateFlow<room?> get() = _selectedRoom

    var availableRooms by mutableStateOf(0)
    var occupiedRooms by mutableStateOf(0)
    var underMaintenanceRooms by mutableStateOf(0)
    var selectedStartDate: String =""
    var selectedEndDate: String = ""
    var checkAv by mutableStateOf(false)
    var checkOc by mutableStateOf(false)
    var checkUnMa by mutableStateOf(false)

    init {
        fetchAllRooms()
    }


    private fun fetchAllRooms() {
        viewModelScope.launch {
            repository.getAllRooms().collect { fetchedRooms ->
                _rooms.value = fetchedRooms
            }
        }
    }

    // Fetch rooms from the repository
    fun fetchRoomsForDateRangeAndType(hotelId: Int, roomType: String, startDate: String, endDate: String) {
        viewModelScope.launch {
            val roomAvailability = withContext(Dispatchers.IO) {
                repository.getRoomAvailability(hotelId, roomType, startDate, endDate)
            }
            availableRooms = roomAvailability.availableRooms
            occupiedRooms = roomAvailability.occupiedRooms
            underMaintenanceRooms = roomAvailability.underMaintenanceRooms
        }
    }

    fun selectRoom(roomId: Int) {
        _selectedRoom.value = _rooms.value.find { it.roomId == roomId }
    }

    fun updateRoomAvailability(Status: RoomStatus) {
        when (Status) {
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
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.updateRoomStatus(room)
            }
        }
    }

    fun updateStartDate(date: Date) {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        selectedStartDate = sdf.format(date)
    }

    fun updateEndDate(date: Date) {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        selectedEndDate = sdf.format(date)
    }
}
