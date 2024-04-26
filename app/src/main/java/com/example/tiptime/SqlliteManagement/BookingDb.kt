package com.example.tiptime.SqlliteManagement

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tiptime.Data.Booking
import com.example.tiptime.Data.PaymentMethod

class BookingDb (context : Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query= ("CREATE TABLE" + TABLE_NAME + "(" + ID_COLUMN + "TEXT PRIMARY KEY" + "FOREIGN KEY("+ HID_COLUMN +") REFERENCES " + HOTEL_TABLE + "(" + HOTELID_COLUMN + ")"+ "TEXT" + "FOREIGN KEY("+ ROOM_COLUMN +") REFERENCES " + ROOMTABLE + "(" + RTYPE + ")"+ "TEXT" + PRICE_COLUMN+"TEXT"+ STARTDATE_COLUMN + "TEXT"+ END_DATE + "TEXT" + END_DATE + "TEXT"+ STATUS_COLUMN +"TEXT")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME)
        onCreate(db)
    }

    companion object{
        const val DB_NAME = "BookingDb"
        const val DB_VERSION = 1
        const val TABLE_NAME = "Booking"
        const val ID_COLUMN = "booking_id"
        const val HID_COLUMN = "hotel_id"
        const val STARTDATE_COLUMN ="start_date"
        const val END_DATE = "end_date"
        const val STATUS_COLUMN = "status"
        const val PRICE_COLUMN = "price"
        const val HOTEL_TABLE = HotelDb.TABLE_NAME
        const val HOTELID_COLUMN = HotelDb.ID_COLUMN
        const val ROOM_COLUMN = "room type"
        const val ROOMTABLE = "ROomtype"
        const val RTYPE = "roomtype"
    }

    fun addNewBooking(
        booking: Booking
    ){
        val db = this.writableDatabase
        val value = ContentValues()

        value.put(ID_COLUMN,booking.Booked_id)
        value.put(HID_COLUMN,booking.HotelId)
        value.put(STARTDATE_COLUMN,booking.BookedStartDate)
        value.put(END_DATE,booking.BookedEndDate)
        value.put(PRICE_COLUMN ,booking.Price)
        value.put(STATUS_COLUMN,booking.Status)

    }

}