package com.example.tiptime.SqlliteManagement

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tiptime.Data.room

class RoomDb(context : Context?) :
    SQLiteOpenHelper(context, RoomDb.DB_NAME, null, RoomDb.DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query =("CREATE TABLE" + TABLE_NAME + "(" + TYPE_COLUMN + "TEXT PRIMARY KEY" + ID_COLUMN + "TEXT"  + "FOREIGN KEY("+ RoomDb.ID_COLUMN +") REFERENCES " + RoomDb.HOTEL_TABLE + "(" + RoomDb.HOTELID_TABLE + ")"+ "TEXT" + STATUS_COLUMN + "TEXT"+ PRICE_COLUMN + "TEXT")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS" + RoomDb.TABLE_NAME)
        onCreate(db)
    }
    companion object{
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
    ){
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(TYPE_COLUMN,room.roomType)
        value.put(ID_COLUMN,room.hotel_id)
        value.put(PRICE_COLUMN,room.price)
        value.put(STATUS_COLUMN,room.Status)
        db.insert(BookingDb.TABLE_NAME, null, value)
        db.close()
    }
    fun availableRoom(roomtype:String,hotelid:String):String{
        val status :String
        val db = this.readableDatabase

        // on below line we are creating a cursor with query to read data from database.
        val cursorRoom: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME JOIN ${ID_COLUMN} ${TABLE_NAME} ON (${HOTELID_TABLE} ${HOTEL_TABLE}) WHERE $ID_COLUMN=""" , null)
    }
}