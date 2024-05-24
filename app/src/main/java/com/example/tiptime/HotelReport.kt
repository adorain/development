import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.tiptime.Data.BookingStatistics
import com.example.tiptime.BookingViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "TravelGo"
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
    )
}

@Composable
fun HotelReport(bookingViewModel: BookingViewModel) {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    val context = LocalContext.current
    var startDate by remember { mutableStateOf(Date()) }
    var endDate by remember { mutableStateOf(Date()) }
    var showDate by remember { mutableStateOf(false) }
    var showDate2 by remember { mutableStateOf(false) }
    var showStartButtonText by remember { mutableStateOf(sdf.format(Date())) }
    var showEndButtonText by remember { mutableStateOf(sdf.format(Date())) }

    if (showDate) {
        showDatePicker(context = context, date = Date()) { selectedDate ->
            // Update start date and button text
            showDate = false
            showStartButtonText = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate)
            bookingViewModel.updateStartDate(SimpleDateFormat("ydd/MM/yyyy", Locale.getDefault()).format(selectedDate))
        }
    }

    if (showDate2) {
        showDatePicker(context = context, date = Date()) { selectedDate ->
            // Update end date and button text
            showDate2 = false
            showEndButtonText = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate)
            bookingViewModel.updateEndDate(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate))
        }
    }

    val statistics by bookingViewModel.statistics.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Hotel Report", modifier = Modifier.padding(bottom = 8.dp))
        Text(text = sdf.format(Date()), modifier = Modifier.padding(bottom = 8.dp))

        Row(modifier = Modifier.padding(bottom = 8.dp)) {
            Button(onClick = { showDate = true }) {
                Text("Select Start Date: $showStartButtonText")
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
        Row(modifier = Modifier.padding(bottom = 8.dp)) {
            Button(onClick = { showDate2 = true }) {
                Text("Select End Date: $showEndButtonText")
            }
        }

        Button(onClick = { bookingViewModel.fetchBookingStatistics() }) {
            Text("Show Report")
        }

        StatisticsTable(statistics)
    }
}

@Composable
fun StatisticsTable(statistics: List<BookingStatistics>) {
    Column {
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            Text(text = "Room Type", modifier = Modifier.weight(1f))
            Text(text = "Earnings", modifier = Modifier.weight(1f))
            Text(text = "Number of Rooms", modifier = Modifier.weight(1f))
        }
        HorizontalDivider()
        statistics.forEach { stat ->
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Text(text = stat.ROOMTYPE, modifier = Modifier.weight(1f))
                Text(text = stat.earnings.toString(), modifier = Modifier.weight(1f))
                Text(text = stat.numOfRooms.toString(), modifier = Modifier.weight(1f))
            }
            HorizontalDivider()
        }
    }
}

fun showDatePicker(context: Context, date: Date, onDateSelected: (Date) -> Unit) {
    val calendar = Calendar.getInstance()
    calendar.time = date
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    android.app.DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            calendar.set(selectedYear, selectedMonth, selectedDayOfMonth)
            onDateSelected(calendar.time)
        },
        year,
        month,
        day
    ).show()
}
