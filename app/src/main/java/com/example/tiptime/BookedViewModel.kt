import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiptime.Data.Booking
import com.example.tiptime.Data.BookingRepository
import com.example.tiptime.Data.Hotel
import com.example.tiptime.Data.HotelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BookedViewModel(
    private val bookingRepository: BookingRepository,
    private val hotelRepository: HotelRepository
) : ViewModel() {

    private val _bookings = MutableStateFlow<List<Pair<Booking, Hotel>>>(emptyList())
    val bookings: StateFlow<List<Pair<Booking, Hotel>>> get() = _bookings

    @RequiresApi(Build.VERSION_CODES.O)
    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    init {
        fetchBookings()
        insertSampleData() // Insert sample data when the ViewModel is created
    }

    private fun fetchBookings() {
        viewModelScope.launch {
            val bookings = bookingRepository.getAllBookings()
            val hotels = hotelRepository.getAllHotels()

            val bookingDetails = bookings.mapNotNull { booking ->
                val hotel = hotels.find { it.HotelId == booking.HotelId }
                hotel?.let { Pair(booking, it) }
            }
            _bookings.value = bookingDetails
        }
    }

    private fun insertSampleData() {
        viewModelScope.launch {
            bookingRepository.insertSampleData()
            fetchBookings() // Refresh the bookings after inserting sample data
        }
    }

    fun cancelBooking(bookingId: Int) {
        viewModelScope.launch {
            bookingRepository.updateBookingStatus(bookingId, "Canceled")
            fetchBookings() // Refresh the bookings list
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
