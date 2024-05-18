import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tiptime.Data.BookingRes
import com.example.tiptime.Data.HotelRes
import com.example.tiptime.viewmodel.BookedViewModel

class BookingViewModelFactory(
    private val bookingRes: BookingRes,
    private val hotelRes: HotelRes
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookedViewModel(bookingRes, hotelRes) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
