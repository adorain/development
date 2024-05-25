import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tiptime.Data.ApplicationInventory
import com.example.tiptime.Data.RoomRepository
import com.example.tiptime.EditRoomsViewModel

class EditRoomsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditRoomsViewModel::class.java)) {
            val database = ApplicationInventory.getDatabase(context)
            val roomDao = database.roomDao()
            val bookingDao = database.bookingDao()
            val hotelDao = database.hotelDao()
            val hotelUserDao = database.hotelUserDao()
            val repository = RoomRepository(roomDao, bookingDao,hotelDao,hotelUserDao)
            @Suppress("UNCHECKED_CAST")
            return EditRoomsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}