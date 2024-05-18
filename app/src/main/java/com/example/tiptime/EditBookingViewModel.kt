package com.example.tiptime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiptime.Data.RoomRepository
import com.example.tiptime.Data.room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope


class EditBookingViewModel (private val roomRepository: RoomRepository):ViewModel(){
    private val _uiState = MutableStateFlow(room())
    val uiState: StateFlow<room> = _uiState.asStateFlow()

    fun updateHotelDetails(hotelId: Int, hotelName: String, hotelAddress: String, roomTypes: List<String>) {
        viewModelScope.launch {
            // Update hotel details
            roomRepository.updateHotelDetails(hotelId, hotelName, hotelAddress)

            // Update room types
            roomTypes.forEach { roomType ->
                roomRepository.updateRoomType(hotelId, roomType)
            }
        }
    }

}