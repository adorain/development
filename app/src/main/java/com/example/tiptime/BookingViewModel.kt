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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Stack


class BookingViewModel(private val bookingRes: BookingRes) : ViewModel(){

    private val _uiState = MutableStateFlow(room())
    val uiState: StateFlow<room> = _uiState.asStateFlow()
    var hotel_Id by mutableStateOf(0)
    var Price by mutableStateOf(0.00)
    var BookedStartDate by mutableStateOf(Date())
    var BookedEndDate by mutableStateOf(Date())
    var Pax by mutableStateOf(0)
    var roomtype by mutableStateOf("")
    private val _uiBookingState = MutableStateFlow(Booking())
    val uiBookingState : StateFlow<Booking> = _uiBookingState.asStateFlow()
    var status by mutableStateOf(true)

    fun insertNewBooking(){
        viewModelScope.launch(Dispatchers.IO) {


            bookingRes.addNewBooking(
            booking = Booking(
                Booked_id = uiBookingState.value.Booked_id,
                HotelId = hotel_Id,
                ROOMTYPE = roomtype,
                BookedStartDate = parseDate(BookedStartDate),
                BookedEndDate = parseDate(BookedEndDate),
                Pax = Pax,
                Status="Confirmed",
                Price = Price
            )
        ) }



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
        val diffInMillies = BookedEndDate.time - BookedEndDate.time
        val diffInDays = (diffInMillies / (1000 * 60 * 60 * 24)).toDouble()
        val totalPrice = Price * diffInDays
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
                BookedStartDate = BookedStartDate.toString()
            )

        }
        return BookedStartDate
    }
    fun setBookingEndDate():Date{

        _uiBookingState.update {
                uiBookingState ->
            uiBookingState.copy(
                BookedEndDate = BookedEndDate.toString()
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
    fun setHotelId(hotelId : Int){
        _uiBookingState.update {
            uiBookingState ->
            uiBookingState.copy(
                HotelId = hotelId.toInt()
            )
        }
        hotel_Id = hotelId.toInt()
    }



    fun updateBookingStartDate(bookedStartDate : String) {
        _uiBookingState.update {
                uiBookingState ->
            uiBookingState.copy(
                BookedStartDate = bookedStartDate
            )
        }
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.CHINA)
        val date = dateFormat.parse(bookedStartDate)
        BookedStartDate = date
    }

    fun updateBookingEndDate(bookedEndDate: String) {
        _uiBookingState.update {
                uiBookingState ->
            uiBookingState.copy(
                BookedEndDate = bookedEndDate
            )
        }
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.CHINA)
        val date = dateFormat.parse(bookedEndDate)
        BookedEndDate = date
    }

    fun updatePax(pax : String){
        _uiBookingState.update {
                uiBookingState ->
            uiBookingState.copy(
                Pax = pax.toInt()
            )
        }
        Pax = pax.toInt()
    }

    fun updatePrice(price : String){
        Price = price.toDouble()
    }
    fun updateRoomType(RoomType:String){
        roomtype = RoomType
    }

    fun setRoomType():String{
        _uiBookingState.update{
                currentState->
            currentState.copy(
                ROOMTYPE = roomtype
            )
        }
        return roomtype
    }
    fun setStatus(hotelId: Int,roomType : String,BookingStartDate:Date,BookingEndDate: Date){
        viewModelScope.launch (Dispatchers.IO){
            if(bookingRes.checkRoomStatus(hotelId, roomType, parseDate(BookingStartDate), parseDate(BookingEndDate)) == 0){
                status = true
            }else if (bookingRes.checkRoomStatus(hotelId, roomType, parseDate(BookingStartDate), parseDate(BookingEndDate)) > 0){
                status = false
            }


        }
    }

    fun updateStatus():Boolean{
        return status
    }

    fun parseDate(date : Date):String{
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(date)
    }

}