package com.example.tiptime

import BookedViewModel
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tiptime.Data.ApplicationInventory
import com.example.tiptime.Data.RoomRepository

class BookedViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookedViewModel::class.java)) {
            val database = ApplicationInventory.getDatabase(context)
            val roomDao = database.roomDao()
            val bookingDao = database.bookingDao()
            val hotelDao = database.hotelDao()
            val roomRepository = RoomRepository(roomDao,bookingDao,hotelDao)
            @Suppress("UNCHECKED_CAST")
            return BookedViewModel(roomRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}