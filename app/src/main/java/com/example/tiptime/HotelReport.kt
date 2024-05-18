import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.AlertDialogDefaults.titleContentColor
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tiptime.BookingViewModel
import com.example.tiptime.R
import com.example.tiptime.hotelViewModel
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelTopBar(
    currentScreen: String,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Icon Row
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.jt_logo),
                        contentDescription = "Icon",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

                // Text Row
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = currentScreen
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelBottomBar(
    currentScreen: String,
    modifier: Modifier = Modifier,
    onSectionSelected: (Int) -> Unit
) {
    BottomAppBar(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Section 1
            IconButton(
                onClick = { onSectionSelected(1) },
                modifier = Modifier.weight(1f).fillMaxHeight(),
                content = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = "Section 1",
                            modifier = Modifier.size(42.dp)
                        )
                        Text(
                            text = "Home",
                            fontSize = 15.sp
                        )
                    }
                }
            )

            // Section 2
            IconButton(
                onClick = { onSectionSelected(2) },
                modifier = Modifier.weight(1f).fillMaxHeight(),
                content = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.reservation),
                            contentDescription = "Section 2",
                            modifier = Modifier.size(42.dp)
                        )
                        Text(
                            text = "Reservation",
                            fontSize = 15.sp
                        )
                    }
                }
            )

            // Section 3
            IconButton(
                onClick = { onSectionSelected(3) },
                modifier = Modifier.weight(1f).fillMaxHeight(),
                content = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.room),
                            contentDescription = "Section 3",
                            modifier = Modifier.size(42.dp)
                        )
                        Text(
                            text = "Room",
                            fontSize = 15.sp
                        )
                    }
                }
            )

            // Section 4
            IconButton(
                onClick = { onSectionSelected(4) },
                modifier = Modifier.weight(1f).fillMaxHeight(),
                content = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.settings),
                            contentDescription = "Section 4",
                            modifier = Modifier.size(42.dp)
                        )
                        Text(text = "Settings",
                            fontSize = 15.sp)
                    }
                }
            )
        }
    }
}


//hotelDb: HotelDb
@Composable
fun HotelReport(viewModelHotel: hotelViewModel) {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    val context = LocalContext.current
    var startDate by remember { mutableStateOf(Date()) }
    var endDate by remember { mutableStateOf(Date()) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "hotelName", modifier = Modifier.padding(bottom = 8.dp))
        Text(text = sdf.format(Date()), modifier = Modifier.padding(bottom = 8.dp))

        Row(modifier = Modifier.padding(bottom = 8.dp)) {
            Button(onClick = {
                showDatePicker(context, startDate) { date ->
                    startDate = date
                    viewModelHotel.updateStartDate(sdf.format(date))
                }
            }) {
                Text("Select Start Date: ${sdf.format(startDate)}")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                showDatePicker(context, endDate) { date ->
                    endDate = date
                    viewModelHotel.updateEndDate(sdf.format(date))
                }
            }) {
                Text("Select End Date: ${sdf.format(endDate)}")
            }
        }

        // Display the statistics table
        StatisticsTable(viewModelHotel)
    }
}

@Composable
fun StatisticsTable(viewModelHotel: hotelViewModel) {
    // Dummy data for demonstration
    val statistics = listOf(
        RoomStatistics("Single", 1000, 10),
        RoomStatistics("Double", 2000, 5),
        RoomStatistics("Suite", 3000, 3)
    )

    Column {
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            Text(text = "Room Type", modifier = Modifier.weight(1f))
            Text(text = "Earnings", modifier = Modifier.weight(1f))
            Text(text = "Number of Rooms", modifier = Modifier.weight(1f))
        }
        HorizontalDivider()
        statistics.forEach { stat ->
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Text(text = stat.roomType, modifier = Modifier.weight(1f))
                Text(text = stat.earnings.toString(), modifier = Modifier.weight(1f))
                Text(text = stat.numOfRooms.toString(), modifier = Modifier.weight(1f))
            }
            HorizontalDivider()
        }
    }
}

data class RoomStatistics(val roomType: String, val earnings: Int, val numOfRooms: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelReservationScreen(navController: NavController, hotelViewModel: hotelViewModel, bookingViewModel: BookingViewModel) {
    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf(Date()) }
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Hotel Reservation") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                addBooking(
                    bookingViewModel,
                    1,
                    "Standard",
                    sdf.format(selectedDate),
                    sdf.format(Date(selectedDate.time + 86400000)),
                    1,
                    100.0
                )
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Reservation")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
                Text(text = sdf.format(Date()), modifier = Modifier.padding(bottom = 8.dp))

                Button(
                    onClick = {
                        showDatePicker(context, selectedDate) { date ->
                            selectedDate = date
                            hotelViewModel.updateStartDate(sdf.format(date))
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Select Date: ${sdf.format(selectedDate)}")
                }

                Button(
                    onClick = {
                        val dateString = sdf.format(selectedDate)
                        navController.navigate("reservationCheck/$dateString")
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text("Check Reservations")
                }
            }
        }
    )
}




