package com.example.tiptime

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiptime.Data.Room
import com.example.tiptime.Data.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

enum class RoomStatus {
    AVAILABLE, OCCUPIED, UNDER_MAINTENANCE
}


class EditRoomsViewModel(private val repository: RoomRepository) : ViewModel() {
    private val _rooms = MutableStateFlow<List<Room>>(emptyList())
    val rooms: StateFlow<List<Room>> get() = _rooms

    private val _selectedRoom = MutableStateFlow<Room?>(null)
    val selectedRoom: StateFlow<Room?> get() = _selectedRoom

    var availableRooms by mutableStateOf(0)
    var occupiedRooms by mutableStateOf(0)
    var underMaintenanceRooms by mutableStateOf(0)
    var selectedStartDate: Long = 0
    var selectedEndDate: Long = 0
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
                updateRoomCounts(fetchedRooms)
            }
        }
    }

    // Fetch rooms from the repository
    fun fetchRoomsForDateRangeAndType(hotelId: String, roomType: String, startDate: Long, endDate: Long) {
        viewModelScope.launch {
            val fetchedRooms = withContext(Dispatchers.IO) {
                repository.getRoomsForDateRangeAndType(hotelId, roomType, startDate, endDate)
            }
            _rooms.value = fetchedRooms
            updateRoomCounts(fetchedRooms)
        }
    }

    private fun updateRoomCounts(rooms: List<Room>) {
        availableRooms = rooms.count { it.Status == "Available" }
        occupiedRooms = rooms.count { it.Status == "Occupied" }
        underMaintenanceRooms = rooms.count { it.Status == "Under Maintenance" }
    }

    fun selectRoom(roomId: String) {
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

    fun updateRoomStatus(room: Room) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.updateRoomStatus(room)
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
