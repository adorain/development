package com.example.tiptime

import com.example.tiptime.Data.Hotel
import com.example.tiptime.Data.room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class hotelViewModel {

    private val _uiState = MutableStateFlow(Hotel())
    val uiState: StateFlow<Hotel> = _uiState.asStateFlow()

}