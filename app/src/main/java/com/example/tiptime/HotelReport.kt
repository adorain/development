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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.AlertDialogDefaults.titleContentColor
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tiptime.HotelReservation
import com.example.tiptime.R
import com.example.tiptime.SqlliteManagement.HotelDb
import com.example.tiptime.ui.theme.TipTimeTheme
import java.util.Date

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
@Preview
@Composable
fun HotelReport() {
    var currentPage by remember { mutableStateOf("Report") }

    Scaffold(
        topBar = {
            HotelTopBar(
                currentScreen = currentPage
            )
        },
        bottomBar = {
            HotelBottomBar(
                currentScreen = currentPage,
                onSectionSelected = { section ->
                    when (section) {
                        1 -> {/* Handle navigation for section 1 */}
                        2 -> {/* Handle navigation for section 2 */}
                        3 -> {/* Handle navigation for section 3 */}
                        4 -> {/* Handle navigation for section 4 */}
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Welcome, Hotel name!")
            }
        }
    }
}




