package com.example.tiptime

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiptime.Data.Booking
import com.example.tiptime.Data.BookingRes
import com.example.tiptime.Data.BookingStatistics
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

class BookingViewModel(private val bookingRes: BookingRes) : ViewModel() {

    private val _uiState = MutableStateFlow(room())
    val uiState: StateFlow<room> = _uiState.asStateFlow()
    var hotel_Id by mutableStateOf(0)
    var Price by mutableStateOf(0.00)
    var BookedStartDate by mutableStateOf(Date())
    var BookedEndDate by mutableStateOf(Date())
    var Pax by mutableStateOf(0)
    var roomtype by mutableStateOf("")
    private val _uiBookingState = MutableStateFlow(Booking())
    val uiBookingState: StateFlow<Booking> = _uiBookingState.asStateFlow()
    var status by mutableStateOf(false)
    var count by mutableStateOf(0)
    private var allBooking: List<Booking> = listOf()
    var tempStart by mutableStateOf("")
    var tempEnd by mutableStateOf("")
    private var _bookingList = MutableStateFlow<List<Booking>>(emptyList())
    val bookingList: StateFlow<List<Booking>> get() = _bookingList.asStateFlow()
    var totalPrice by mutableStateOf(0.00)
    var StartDate by mutableStateOf(Date())
    var EndDate by mutableStateOf(Date())
    private val _statistics = MutableStateFlow<List<BookingStatistics>>(emptyList())
    val statistics: StateFlow<List<BookingStatistics>> = _statistics.asStateFlow()
    var User_Id by mutableStateOf("")

    fun insertNewBooking() {
        viewModelScope.launch(Dispatchers.IO) {
            bookingRes.addNewBooking(
                booking = Booking(
                    Booked_id = uiBookingState.value.Booked_id,
                    HotelId = hotel_Id,
                    ROOMTYPE = roomtype,
                    BookedStartDate = parseDate(BookedStartDate),
                    BookedEndDate = parseDate(BookedEndDate),
                    Pax = Pax,
                    Status = "Confirmed",
                    Price = Price,
                    userId = User_Id
                )
            )
        }
    }

    fun changeStatus(STARTDATE: Date, ENDDATE: Date): String {
        val differentDate = ENDDATE.time - STARTDATE.time
        if (differentDate > ENDDATE.time) {
            return "Expired"
        }
        return "Completed"
    }

    fun calculatePrice() {
        val diffInMillies = BookedEndDate.time - BookedStartDate.time
        val diffInDays = (diffInMillies / (1000 * 60 * 60 * 24)).toDouble()
        Log.d("", diffInDays.toString())
        totalPrice = Price * diffInDays
    }

    fun updateUserId(userID: String) {
        _uiBookingState.update { currentState ->
            currentState.copy(
                userId = userID
            )
        }
        User_Id = userID
    }

    fun setBookingStartDate(): Date {
        _uiBookingState.update { uiBookingState ->
            uiBookingState.copy(
                BookedStartDate = BookedStartDate.toString()
            )
        }
        return BookedStartDate
    }

    fun setBookingEndDate(): Date {
        _uiBookingState.update { uiBookingState ->
            uiBookingState.copy(
                BookedEndDate = BookedEndDate.toString()
            )
        }
        return BookedEndDate
    }

    fun setPax(): Int {
        _uiBookingState.update { uiBookingState ->
            uiBookingState.copy(
                Pax = Pax
            )
        }
        return Pax
    }

    fun setHotelId(hotelId: Int) {
        _uiBookingState.update { uiBookingState ->
            uiBookingState.copy(
                HotelId = hotelId
            )
        }
        hotel_Id = hotelId
    }

    fun updateBookingStartDate(bookedStartDate: String) {
        _uiBookingState.update { uiBookingState ->
            uiBookingState.copy(
                BookedStartDate = bookedStartDate
            )
        }
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.CHINA)
        val date = dateFormat.parse(bookedStartDate)
        BookedStartDate = date
    }

    fun updateBookingEndDate(bookedEndDate: String) {
        _uiBookingState.update { uiBookingState ->
            uiBookingState.copy(
                BookedEndDate = bookedEndDate
            )
        }
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.CHINA)
        val date = dateFormat.parse(bookedEndDate)
        BookedEndDate = date
    }

    fun updatePax(pax: String) {
        _uiBookingState.update { uiBookingState ->
            uiBookingState.copy(
                Pax = pax.toInt()
            )
        }
        Pax = pax.toInt()
    }

    fun updateRoomType(RoomType: String) {
        roomtype = RoomType
    }

    fun updateRoomPrice(roomPrice: Double) {
        Price = roomPrice
    }

    fun setRoomType(): String {
        _uiBookingState.update { currentState ->
            currentState.copy(
                ROOMTYPE = roomtype
            )
        }
        return roomtype
    }

    fun parseDate(date: Date): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(date)
    }

    fun addNewBooking() {
        viewModelScope.launch(Dispatchers.IO) {
            bookingRes.addNewBooking(
                booking = Booking(
                    Booked_id = uiBookingState.value.Booked_id,
                    HotelId = hotel_Id,
                    ROOMTYPE = roomtype,
                    BookedStartDate = parseDate(BookedStartDate),
                    BookedEndDate = parseDate(BookedEndDate),
                    Pax = Pax,
                    Status = "unpaid",
                    Price = Price
                )
            )
        }
    }

    fun getReservationsForDate(selectedDate: Date): StateFlow<List<Booking>> {
        val reservations = MutableStateFlow<List<Booking>>(emptyList())
        viewModelScope.launch(Dispatchers.IO) {
            val dateString = SimpleDateFormat("dd/MM/yyyy", Locale.US).format(selectedDate)
            val result = bookingRes.getReservationsForDate(dateString)
            reservations.value = result
        }
        return reservations.asStateFlow()
    }

    fun deleteBooking(booking: Booking) {
        viewModelScope.launch(Dispatchers.IO) {
            bookingRes.deleteBooking(booking)
        }
    }

    fun AllBooking() {
        if (allBooking.isEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                bookingRes.allBookingWithChecking().collect { bookings ->
                    allBooking = bookings
                    _bookingList.value = bookings
                    Log.d("", _bookingList.value.size.toString())
                }
            }
        } else {
            _bookingList.value = allBooking
            Log.d("", _bookingList.value.size.toString())
        }
    }

    fun convertDate(dateString: String): Date {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.parse(dateString)
    }

    fun updateStartDate(Startdate: String) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val date = dateFormat.parse(Startdate)
        StartDate = date
    }

    fun updateEndDate(endDate: String) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val date = dateFormat.parse(endDate)
        EndDate = date
    }

    fun fetchBookingStatistics() {
        viewModelScope.launch {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val startDateString = dateFormat.format(StartDate)
            val endDateString = dateFormat.format(EndDate)
            bookingRes.getBookingStatistics(startDateString, endDateString)
                .collect { stats ->
                    _statistics.value = stats
                }
        }
    }
}
