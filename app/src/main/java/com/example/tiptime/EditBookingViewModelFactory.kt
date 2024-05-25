package com.example.tiptime

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tiptime.Data.ApplicationInventory
import com.example.tiptime.Data.RoomRepository

class EditBookingViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditBookingViewModel::class.java)) {
            val database = ApplicationInventory.getDatabase(context)
            val roomDao = database.roomDao()
            val bookingDao = database.bookingDao()
            val hotelDao = database.hotelDao()
            val hotelUserDao=database.hotelUserDao()
            val repository = RoomRepository(roomDao, bookingDao, hotelDao,hotelUserDao)
            @Suppress("UNCHECKED_CAST")
            return EditBookingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
