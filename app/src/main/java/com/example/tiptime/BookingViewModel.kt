package com.example.tiptime


import android.net.http.UrlRequest.Status
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tiptime.Data.Booking
import com.example.tiptime.Data.room
import com.example.tiptime.SqlliteManagement.BookingDb
import com.example.tiptime.SqlliteManagement.HotelDb
import com.example.tiptime.SqlliteManagement.RoomDb
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Stack


class BookingViewModel : ViewModel(){

    private val _uiState = MutableStateFlow(room())
    val uiState: StateFlow<room> = _uiState.asStateFlow()
    var hotel_Id by mutableStateOf("")
    var Price by mutableStateOf(0.00)
    var BookedStartDate by mutableStateOf(Date())
    var BookedEndDate by mutableStateOf(Date())
    var Pax by mutableStateOf(0)
    private val _uiBookingState = MutableStateFlow(Booking())
    val uiBookingState : StateFlow<Booking> = _uiBookingState.asStateFlow()
    fun insertNewBooking(Booking_id : String , Hotel_Id : String,ROOM_TYPE: String,BookedStartDate : Date, BookedEndDate:Date , Pax:Int, STATUS:String,PRICE:Double){
        val bookingData = Booking(
            Booking_id,
            Hotel_Id,
            ROOM_TYPE,
            BookedStartDate,
            BookedEndDate,
            Pax,
            "Completed",
            PRICE
        )

    }
    fun changeStatus(STARTDATE:Date,ENDDATE:Date):String{
        val differentDate = ENDDATE.time-STARTDATE.time
        if(differentDate>ENDDATE.time
            ){
            return "Expired"
        }
        return "Completed"
    }
    /*fun selectBooking():String{
        val hotelId : String = ""
        val roomtype : String = setRoomType(hotelId).toString()
        return roomtype.toString()
    }
    fun setRoomType(hotelId : String) {
        _uiState.update { currentState ->
            currentState.copy(
                roomType = setHotelId(hotelId)
            )
        }
    }

     */
    fun calculatePrice() : Double{
        val totalPrice = Price * (BookedStartDate.time - BookedEndDate.time)
        return totalPrice
    }

    fun setPrice() : Double{
        _uiBookingState.update {
            currentState ->
            currentState.copy(
                Price = Price.toDouble()
            )
        }
        return Price


    }

    fun setBookingStartDate():Date{

        _uiBookingState.update {
            uiBookingState -> uiBookingState.copy(
                BookedStartDate = BookedStartDate
            )

        }
        return BookedStartDate
    }
    fun setBookingEndDate():Date{

        _uiBookingState.update {
                uiBookingState ->
            uiBookingState.copy(
                BookedEndDate = BookedEndDate
            )

        }
        return BookedEndDate
    }

    fun setPax() : Int{
        _uiBookingState.update {
                uiBookingState -> uiBookingState.copy(
                    Pax = Pax
                )
        }
        return Pax
    }
    fun setHotelId(hotelId : String){
        _uiBookingState.update {
            uiBookingState ->
            uiBookingState.copy(
                HotelId = hotelId
            )
        }
    }
    /*fun ConfirmedBooking():String{
        val hotelId : String = ""
        val roomtype : String = setBooking(hotelId).toString()
        return roomtype.toString()
    }*/

    fun updateBookingStartDate(bookedStartDate : String) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateFormat.parse(bookedStartDate)
        BookedStartDate = date
    }

    fun updateBookingEndDate(bookedEndDate: String) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateFormat.parse(bookedEndDate)
        BookedEndDate = date
    }

    fun updatePax(pax : String){
        Pax = pax.toInt()
    }

    fun updatePrice(price : String){
        Price = price.toDouble()
    }


}