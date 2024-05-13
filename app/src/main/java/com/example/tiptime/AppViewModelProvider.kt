package com.example.tiptime

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tiptime.Data.ApplicationInventory

object AppViewModelProvider{
    val factory = viewModelFactory{
        initializer {
            hotelViewModel(
                inventoryApplication().container.hotelRes
            )
        }


        initializer {
            BookingViewModel(inventoryApplication().container.bookingRes)
        }

        initializer {
            RoomViewModel(inventoryApplication().container.roomRes)
        }
    }
}


fun CreationExtras.inventoryApplication(): InventoryApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)