package com.example.tiptime


import androidx.lifecycle.ViewModel
import com.example.tiptime.Data.Booking
import com.example.tiptime.Data.room
import com.example.tiptime.SqlliteManagement.BookingDb
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date


class BookingViewModel(val BookingDb : BookingDb) : ViewModel(){
    private val _uiState = MutableStateFlow(room(roomType = selectBooking()))
    val uiState: StateFlow<room> = _uiState.asStateFlow()
    fun insertNewBooking(Booking_id : String , Hotel_Id : String,ROOM_TYPE: String,BookedStartDate : Date, BookedEndDate:Date , STATUS:String,PRICE:Double){
        val bookingData = Booking(
            Booking_id,
            Hotel_Id,
            ROOM_TYPE,
            BookedStartDate,
            BookedEndDate,
            "Completed",
            PRICE
        )
        BookingDb.addNewBooking(booking = bookingData)
    }
    fun changeStatus(STARTDATE:Date,ENDDATE:Date):String{
        val differentDate = ENDDATE.time-STARTDATE.time
        if(differentDate>ENDDATE.time
            ){
            return "Expired"
        }
        return "Completed"
    }
    fun selectBooking():String{
        val roomtype : String = ""
        return roomtype
    }
}