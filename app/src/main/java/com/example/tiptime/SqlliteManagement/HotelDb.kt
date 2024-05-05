package com.example.tiptime.SqlliteManagement

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tiptime.Data.Hotel
import java.util.Date


class HotelDb
    (context : Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    // below method is for creating a database by running a sqlite query
    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE" + TABLE_NAME + "("
                + ID_COLUMN + "TEXT PRIMARY KEY" +
                "FOREIGN KEY(" + STAFF_COLUMN + ") REFERENCES " + STAFF_TABLE_NAME + "(" + STAFF_ID_COLUMN + ")" +"TEXT"+
                "FOREIGN KEY(" + USER_COLUMN + ") REFERENCES " + USER_TABLE_NAME + "(" + USER_ID_COLUMN + "))"+"TEXT"+
                NAME_COL+ "TEXT" +
                ADDRESS_COL +"TEXT" +
                PAX_COL+"TEXT"+
                TYPE_COL+"TEXT"+
                STATUS_COL+"STATUS")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME)
        onCreate(db)
    }
    companion object{
        const val DB_NAME = "HotelDb"
        const val DB_VERSION = 1
        const val TABLE_NAME = "hotel"
        const val USER_TABLE_NAME = "user"
        const val STAFF_TABLE_NAME = "staff"
        const val ID_COLUMN = "hotel_id"
        const val STAFF_COLUMN = "staff_id"
        const val USER_COLUMN = "user_id"
        const val NAME_COL = "hotel_name"
        const val ADDRESS_COL = "hotel_address"
        const val TYPE_COL = "type"
        const val PAX_COL = "pax"
        const val STATUS_COL = "status"
        const val STAFF_ID_COLUMN = "id"
        const val USER_ID_COLUMN = " user_id"

    }

    fun addnewHotel(
        hotel: Hotel
    ){
        val db = this.writableDatabase
        val value = ContentValues()

        value.put(ID_COLUMN,hotel.HotelId)
        value.put(STAFF_COLUMN,hotel.StaffId)
        value.put(USER_COLUMN,hotel.UserId)
        value.put(NAME_COL,hotel.HotelName)
        value.put(ADDRESS_COL,hotel.HotelAddress)
        value.put(TYPE_COL,hotel.Type)
        value.put(STATUS_COL,hotel.Status)
        db.close()
    }

    fun getHotelId(hotelName : String) : String{
        val db = this.readableDatabase
        var hotelId: String = " "

        val cursorRoom: Cursor = db.rawQuery(
            "SELECT * FROM ${HotelDb.TABLE_NAME} WHERE ${HotelDb.NAME_COL} = ? LIMIT 1",
            arrayOf(hotelName)
        )
        if (cursorRoom.moveToFirst()) {
            val idColumnIndex = cursorRoom.getColumnIndex(RoomDb.ID_COLUMN)
            if (idColumnIndex != -1) {
                hotelId = cursorRoom.getString(idColumnIndex)
            }
        }

        cursorRoom.close()
        db.close()

        return hotelId
    }

    fun getHotelDetails(hotelAddress: String,STARTDATE:Date,ENDDATE:Date,Pax:Int): List<Hotel> {
        val hotels = mutableListOf<Hotel>() // List to store hotel objects
        val db = this.readableDatabase

        val cursor = db.rawQuery(
            "SELECT $ID_COLUMN, $NAME_COL, $ADDRESS_COL, $TYPE_COL, $STATUS_COL FROM $TABLE_NAME WHERE $ADDRESS_COL LIKE '%$hotelAddress%' AND $STATUS_COL = 'Available' ",
            null
        )

        while (cursor.moveToNext()) { // Loop through the cursor to get all hotels
            val hotel = Hotel(
                HotelId = cursor.getString(cursor.getColumnIndexOrThrow(ID_COLUMN)),
                HotelName = cursor.getString(cursor.getColumnIndexOrThrow(NAME_COL)),
                HotelAddress = cursor.getString(cursor.getColumnIndexOrThrow(ADDRESS_COL)),
                Type = cursor.getString(cursor.getColumnIndexOrThrow(TYPE_COL)),
                Status = cursor.getString(cursor.getColumnIndexOrThrow(STATUS_COL))
            )
            hotels.add(hotel) // Add each hotel to the list
        }

        cursor.close()
        db.close()

        return hotels
    }

    /*fun PaxRange(Pax:Int){
        val hotels = mutableListOf<Hotel>()
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT $ID_COLUMN, $NAME_COL, $ADDRESS_COL, $TYPE_COL, $STATUS_COL FROM $TABLE_NAME WHERE $PAX_COL >= $Pax AND $STATUS_COL = 'Available'",
            null
        )

        while (cursor.moveToNext()) { // Loop through the cursor to get all hotels
            val hotel = Hotel(
                HotelId = cursor.getString(cursor.getColumnIndexOrThrow(ID_COLUMN)),
                HotelName = cursor.getString(cursor.getColumnIndexOrThrow(NAME_COL)),
                HotelAddress = cursor.getString(cursor.getColumnIndexOrThrow(ADDRESS_COL)),
                Type = cursor.getString(cursor.getColumnIndexOrThrow(TYPE_COL)),
                Status = cursor.getString(cursor.getColumnIndexOrThrow(STATUS_COL))
            )
            hotels.add(hotel) // Add each hotel to the list
        }

        cursor.close()
        db.close()

        return hotels

    }

     */

}