package com.example.tiptime

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory


object AppViewModelProvider{
    val factory = viewModelFactory{
        initializer {
            hotelViewModel(
                inventoryApplication().container.hotelRes,
                /*inventoryApplication().container.bookingRes*/
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
    (this[AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)



