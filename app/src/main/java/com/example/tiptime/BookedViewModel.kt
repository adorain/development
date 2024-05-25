import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiptime.Data.Booking
import com.example.tiptime.Data.Hotel
import com.example.tiptime.Data.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BookedViewModel(
    private val roomRepository: RoomRepository,
    private val userId:String
) : ViewModel() {

    private val _bookings = MutableStateFlow<List<Pair<Booking, Hotel>>>(emptyList())
    val bookings: StateFlow<List<Pair<Booking, Hotel>>> get() = _bookings

    private val _hotelName = MutableStateFlow("")
    val hotelName: StateFlow<String> get() = _hotelName

    @RequiresApi(Build.VERSION_CODES.O)
    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")


    init {
        fetchBookings()
    }

    private fun fetchBookings() {
        viewModelScope.launch {
            val bookings = roomRepository.getBookingsForUser(userId)
            val hotels = withContext(Dispatchers.IO) { roomRepository.getAllHotelsBooked() }

            val bookingDetails = bookings.mapNotNull { booking ->
                val hotel = hotels.find { it.HotelId == booking.HotelId }
                hotel?.let { Pair(booking, it) }
            }
            _bookings.value = bookingDetails
        }
    }




    fun cancelBooking(bookingId: Int) {
        viewModelScope.launch {
            roomRepository.updateBookingStatus(bookingId, "Canceled")
        }
    }


    fun fetchHotelName(hotelId: Int) {
        viewModelScope.launch {
            _hotelName.value = withContext(Dispatchers.IO) {
                roomRepository.getHotelName(hotelId)
            }
        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateTotalPrice(booking: Booking): Double {
        val startDate = LocalDate.parse(booking.BookedStartDate, dateFormatter)
        val endDate = LocalDate.parse(booking.BookedEndDate, dateFormatter)
        val diffInDays = endDate.toEpochDay() - startDate.toEpochDay()
        return booking.Price * diffInDays
    }
}