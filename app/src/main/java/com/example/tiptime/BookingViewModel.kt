package com.example.tiptime


import android.net.http.UrlRequest.Status
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiptime.Data.Booking
import com.example.tiptime.Data.BookingRes
import com.example.tiptime.Data.room
import com.example.tiptime.SqlliteManagement.BookingDb
import com.example.tiptime.SqlliteManagement.HotelDb
import com.example.tiptime.SqlliteManagement.RoomDb
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Stack


class BookingViewModel(private val bookingRes: BookingRes) : ViewModel(){

    private val _uiState = MutableStateFlow(room())
    val uiState: StateFlow<room> = _uiState.asStateFlow()
    var hotel_Id by mutableStateOf("")
    var Price by mutableStateOf(0.00)
    var BookedStartDate by mutableStateOf(Date())
    var BookedEndDate by mutableStateOf(Date())
    var Pax by mutableStateOf(0)
    var roomtype by mutableStateOf("")
    private val _uiBookingState = MutableStateFlow(Booking())
    val uiBookingState : StateFlow<Booking> = _uiBookingState.asStateFlow()
    var status by mutableStateOf(false)
    fun insertNewBooking(){
        bookingRes.addNewBooking(
            Booking(
                Booked_id = uiBookingState.value.Booked_id,
                HotelId = hotel_Id,
                ROOMTYPE = roomtype,
                BookedStartDate = BookedStartDate,
                BookedEndDate = BookedEndDate,
                Pax = Pax,
                Status="Completed",
                Price = Price
            )
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
    fun updateRoomType(RoomType:String){
        roomtype = RoomType
    }

    fun setRoomType():String{
        _uiState.update{
                currentState->
            currentState.copy(
                roomType = roomtype
            )
        }
        return roomtype
    }
    fun setStatus(hotelId: String,roomType : String,BookingStartDate:Date,BookingEndDate: Date){
        viewModelScope.launch {
            status = bookingRes.checkRoomStatus(hotelId, roomType, BookingStartDate, BookingEndDate)
        }
    }

    fun updateStatus():Boolean{
        return status
    }

}