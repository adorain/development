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
import com.example.tiptime.Data.Hotel


class EditBookingViewModel(private val roomRepository: RoomRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(room())
    val uiState: StateFlow<room> = _uiState.asStateFlow()

    private val _roomPrices = MutableStateFlow<Map<String, Double>>(emptyMap())
    val roomPrices: StateFlow<Map<String, Double>> = _roomPrices

    private val _hotelDetails = MutableStateFlow<Pair<Hotel, List<String>>?>(null)
    val hotelDetails: StateFlow<Pair<Hotel, List<String>>?> = _hotelDetails.asStateFlow()

    fun updateHotelDetails(hotelId: Int, hotelName: String, hotelAddress: String, hotelDescription: String, roomTypes: List<String>) {
        viewModelScope.launch {
            // Update hotel details
            roomRepository.updateHotelDetails(hotelId, hotelName, hotelAddress, hotelDescription)

            // Update room types
            roomTypes.forEach { roomType ->
                roomRepository.updateRoomType(hotelId, roomType)
            }
        }
    }

    fun fetchRoomPrices(hotelId: Int, roomTypes: List<String>) {
        viewModelScope.launch {
            val prices = roomTypes.associateWith { roomType ->
                roomRepository.getRoomPrice(hotelId, roomType)
            }
            _roomPrices.value = prices
        }
    }

    fun updateRoomPrice(hotelId: Int, roomType: String, newPrice: Double) {
        viewModelScope.launch {
            roomRepository.updateRoomPrice(hotelId, roomType, newPrice)
            fetchRoomPrices(hotelId, listOf(roomType)) // Refresh the prices
        }
    }

    fun fetchHotelDetails(hotelId: Int) {
        viewModelScope.launch {
            val (hotel, roomTypes) = roomRepository.getHotelDetails(hotelId)
            _hotelDetails.value = Pair(hotel, roomTypes)
        }
    }
}
