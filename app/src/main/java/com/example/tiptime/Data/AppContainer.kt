package com.example.tiptime.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


interface AppContainer{
    val bookingRes: BookingRes
    val hotelRes : HotelRes
    val roomRes : RoomRes

}

class AppDataContainer(private val context: Context) : AppContainer {
    override val bookingRes: BookingRes by lazy {
        BookingOfflineRes(ApplicationInventory.getDatabase(context).bookingDao())
    }


    override val hotelRes: HotelRes by lazy {
        HotelOfflineRes(ApplicationInventory.getDatabase(context).hotelDao())
    }

    override val roomRes: RoomRes by lazy {
        RoomOfflineRes(ApplicationInventory.getDatabase(context).roomDao())
    }
}