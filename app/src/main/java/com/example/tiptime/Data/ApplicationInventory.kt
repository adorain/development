package com.example.tiptime.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [Hotel::class , room::class , Booking::class], version = 2, exportSchema = false)
abstract class ApplicationInventory: RoomDatabase() {


    abstract fun hotelDao() : HotelDao
    abstract fun bookingDao():BookingDao
    abstract fun roomDao():RoomDao

    companion object {
        @Volatile
        private var Instance: ApplicationInventory? = null

        fun getDatabase(context: Context): ApplicationInventory {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ApplicationInventory::class.java, "hotel_database")
                    .fallbackToDestructiveMigration()
                    .build().also { Instance = it }
            }
        }
    }

}