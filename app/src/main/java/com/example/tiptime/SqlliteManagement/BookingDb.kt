package com.example.tiptime.SqlliteManagement

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tiptime.Data.Booking
import com.example.tiptime.Data.PaymentMethod
import java.text.SimpleDateFormat
import java.util.Locale

class BookingDb (context : Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query= ("CREATE TABLE" + TABLE_NAME + "(" + ID_COLUMN + "TEXT PRIMARY KEY ," + HID_COLUMN + " TEXT NOT NULL," +"FOREIGN KEY("+ HID_COLUMN +") REFERENCES " + HOTEL_TABLE + "(" + HOTELID_COLUMN + ")"+ ROOM_COLUMN + "TEXT NOT NULL," + "FOREIGN KEY("+ ROOM_COLUMN +") REFERENCES " + ROOMTABLE + "(" + RTYPE + ")"+ PRICE_COLUMN+"TEXT NOT NULL,"+ STARTDATE_COLUMN + "TEXT NOT NULL,"+ END_DATE + "TEXT NOT NULL," + END_DATE + "TEXT NOT NULL,"+ STATUS_COLUMN +"TEXT NOT NULL)")
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
        const val ROOMTABLE = "Roomtype"
        const val RTYPE = "roomtype"
    }

    fun addNewBooking(
        booking: Booking
    ){
        val db = this.writableDatabase
        val value = ContentValues()

        value.put(ID_COLUMN,booking.Booked_id)
        value.put(HID_COLUMN,booking.HotelId)
        value.put(STARTDATE_COLUMN,booking.BookedStartDate.toString())
        value.put(END_DATE,booking.BookedEndDate.toString())
        value.put(PRICE_COLUMN ,booking.Price)
        value.put(ROOM_COLUMN,booking.ROOMTYPE)
        value.put(STATUS_COLUMN,booking.Status)
        db.insert(TABLE_NAME, null, value)
        db.close()
    }



    @SuppressLint("Range")
    fun getBookingById(bookingId: String): Booking? {
        val db = readableDatabase
        var booking: Booking? = null

        val cursor: Cursor = db.query(
            TABLE_NAME,
            null,
            "$ID_COLUMN = ?",
            arrayOf(bookingId),
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            val bookedId = cursor.getString(cursor.getColumnIndex(ID_COLUMN))
            val hotelId = cursor.getString(cursor.getColumnIndex(HID_COLUMN))
            val roomType = cursor.getString(cursor.getColumnIndex(ROOM_COLUMN))
            val startDateStr = cursor.getString(cursor.getColumnIndex(STARTDATE_COLUMN))
            val endDateStr = cursor.getString(cursor.getColumnIndex(END_DATE))
            val status = cursor.getString(cursor.getColumnIndex(STATUS_COLUMN))
            val price = cursor.getDouble(cursor.getColumnIndex(PRICE_COLUMN))



            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val startDate = dateFormat.parse(startDateStr)
            val endDate = dateFormat.parse(endDateStr)

            booking = Booking(bookedId, hotelId, roomType, startDate, endDate, 0, status, price)
        }

        cursor.close()
        db.close()

        return booking
    }

    fun deleteBookingById(bookingId: String): Boolean {
        val db = writableDatabase
        val result = db.delete(TABLE_NAME, "$ID_COLUMN=?", arrayOf(bookingId))
        db.close()
        return result != -1
    }

}