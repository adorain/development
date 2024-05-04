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
    var HotelId by mutableStateOf("")
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

    fun setPrice(PRICE: String){
        _uiBookingState.update {
            currentState ->
            currentState.copy(
                Price = PRICE.toDouble()
            )
        }
        updatePrice()

    }

    fun setBookingStartDate(STARTDATE: String){
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateFormat.parse(STARTDATE)
        _uiBookingState.update {
            uiBookingState -> uiBookingState.copy(
                BookedStartDate = date
            )

        }
        updateBookingStartDate()
    }
    fun setBookingEndDate(ENDDATE: String){
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateFormat.parse(ENDDATE)
        _uiBookingState.update {
                uiBookingState ->
            uiBookingState.copy(
                BookedEndDate = date
            )

        }
        updateBookingEndDate()
    }

    fun setPax(Pax:String){
        val paxString = Integer.parseInt(Pax)
        _uiBookingState.update {
                uiBookingState -> uiBookingState.copy(
                    Pax = paxString
                )
        }
        updatePax()
    }
    fun setHotelId(hotelId:String){
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

    private fun updateBookingStartDate() {
        BookedStartDate = uiBookingState.value.BookedStartDate
    }

    private fun updateBookingEndDate() {
        BookedEndDate = uiBookingState.value.BookedEndDate
    }

    private fun updatePax(){
        Pax = uiBookingState.value.Pax
    }

    fun updatePrice(){
        Price = uiBookingState.value.Price
    }



}