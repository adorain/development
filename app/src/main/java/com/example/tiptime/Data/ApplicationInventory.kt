package com.example.tiptime.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Hotel::class , room::class , Booking::class,User::class], version = 12, exportSchema = false)
abstract class ApplicationInventory: RoomDatabase() {


        abstract fun hotelDao() : HotelDao
        abstract fun bookingDao():BookingDao
        abstract fun roomDao():RoomDao
        abstract fun normalUserDao(): NormalUserDao
        abstract fun hotelUserDao():HotelUserDao

    companion object {
            @Volatile
            private var Instance: ApplicationInventory? = null

            fun getDatabase(context: Context): ApplicationInventory {
                // if the Instance is not null, return it, otherwise create a new database instance.
                return Instance ?: synchronized(this) {
                    Room.databaseBuilder(context, ApplicationInventory::class.java, "hotel_database").fallbackToDestructiveMigration()
                        .fallbackToDestructiveMigration() // Use this to allow destructive migrations
                        .build().also { Instance = it }
                }
            }
        }

}