package com.example.tiptime.SqlliteManagement

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tiptime.Data.room

class RoomDb(context : Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            ("CREATE TABLE" + TABLE_NAME + "(" + TYPE_COLUMN + "TEXT PRIMARY KEY ," + ID_COLUMN + "TEXT NOT NULL," + "FOREIGN KEY(" + ID_COLUMN + ") REFERENCES " + HOTEL_TABLE + "(" + HOTELID_TABLE + ")" + STATUS_COLUMN + "TEXT NOT NULL," + PRICE_COLUMN + "TEXT NOT NULL)")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME)
        onCreate(db)
    }

    companion object {
        const val DB_NAME = "RoomDb"
        const val DB_VERSION = 1
        const val TABLE_NAME = "Room"
        const val ID_COLUMN = "hotel_id"
        const val TYPE_COLUMN = "roomtype"
        const val STATUS_COLUMN = "status"
        const val HOTEL_TABLE = "hotel"
        const val PRICE_COLUMN = "price"
        const val HOTELID_TABLE = "hotel_id"
    }

    fun addNewRoom(
        room: room
    ) {
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(TYPE_COLUMN, room.roomType)
        value.put(ID_COLUMN, room.hotel_id)
        value.put(PRICE_COLUMN, room.price)
        value.put(STATUS_COLUMN, room.Status)
        db.insert(BookingDb.TABLE_NAME, null, value)
        db.close()
    }

    fun checkRoomStatus(hotelId: String, roomType: String): String {
        val db = this.readableDatabase
        var status = ""

        val cursorRoom: Cursor = db.rawQuery(
            "SELECT $STATUS_COLUMN FROM $TABLE_NAME WHERE $ID_COLUMN = ? AND $TYPE_COLUMN = ? LIMIT 1",
            arrayOf(hotelId, roomType)
        )

        if (cursorRoom.moveToFirst()) {
            val statusColumnIndex = cursorRoom.getColumnIndex(STATUS_COLUMN)
            if (statusColumnIndex != -1) {
                status = cursorRoom.getString(statusColumnIndex)
            } else {
                status = "Status column not found"
            }
        }

        cursorRoom.close()
        db.close()

        return status
    }

    fun findRoomType(hotelId: String): String {
        val db = this.readableDatabase
        var roomType: String = " "

        val cursorRoom: Cursor = db.rawQuery(
            "SELECT $TYPE_COLUMN FROM $TABLE_NAME WHERE $HOTELID_TABLE = ? LIMIT 1",
            arrayOf(hotelId)
        )

        if (cursorRoom.moveToFirst()) {
            val typeColumnIndex = cursorRoom.getColumnIndex(TYPE_COLUMN)
            if (typeColumnIndex != -1) {
                roomType = cursorRoom.getString(typeColumnIndex)
            }
        }

        cursorRoom.close()
        db.close()

        return roomType
    }

    fun PriceRange(hotelId : String) : String{
        var minPrice = 0.0
        var maxPrice = 0.0
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT MIN(${PRICE_COLUMN}), MAX(${PRICE_COLUMN}) FROM $TABLE_NAME WHERE ID_COLUMN = ?",
            arrayOf(hotelId)
        )

        if (cursor.moveToFirst()) {
            minPrice = cursor.getDouble(0)
            maxPrice = cursor.getDouble(1)
        }
        cursor.close()
        db.close()
        return "$minPrice - $maxPrice"

    }
}
