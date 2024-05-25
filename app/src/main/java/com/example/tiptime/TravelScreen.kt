package com.example.tiptime

import Booked
import EditRoomsViewModelFactory
import HotelReport
import TopAppBar
import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tiptime.Data.ApplicationInventory
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

enum class screen {
    userSelection, userLogin, signUp, hotelLogin, newHotel, home, booking, detail, summary, payment, reservations,addreservation
}

enum class UserType {
    user, staff
}

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun TravelApp(
    viewModel: BookingViewModel = viewModel(factory = AppViewModelProvider.factory),
    viewModelhotel: hotelViewModel = viewModel(factory = AppViewModelProvider.factory),
    viewRoomViewModel: RoomViewModel = viewModel(factory = AppViewModelProvider.factory),
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Log.d("TravelApp", "Current route: $currentRoute")

    val showTopBar = currentRoute in listOf(
        TravelBottomBar.UserHome.route,
        TravelBottomBar.Favourite.route,
        TravelBottomBar.Booked.route,
        TravelBottomBar.UserSettings.route,
        HotelBottomBar.Home.route,
        HotelBottomBar.Reservation.route,
        HotelBottomBar.editRoom.route,
        HotelBottomBar.editBook.route,
        HotelBottomBar.Settings.route
    )

    val showBottomBar = currentRoute in listOf(
        TravelBottomBar.UserHome.route,
        TravelBottomBar.Favourite.route,
        TravelBottomBar.Booked.route,
        TravelBottomBar.UserSettings.route
    )

    val showHotelBottomBar = currentRoute in listOf(
        HotelBottomBar.Home.route,
        HotelBottomBar.Reservation.route,
        HotelBottomBar.editRoom.route,
        HotelBottomBar.editBook.route,
        HotelBottomBar.Settings.route
    )

    Scaffold(
        topBar = {
            if (showTopBar) {
                TopAppBar()
            }
        },
        bottomBar = {
            if (showBottomBar) {
                TravelBottomNavigationBar(navController = navController)
            }
            else if (showHotelBottomBar){
                HotelBottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        val uiState by viewModel.uiBookingState.collectAsState()
        val uiHotelState by viewModelhotel.uiStateHotel.collectAsState()
        NavHost(
            navController = navController,
            startDestination = screen.userSelection.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = screen.userSelection.name) {
                UserSelectionContent { userType ->
                    when (userType) {
                        UserType.user -> navController.navigate(screen.userLogin.name)
                        UserType.staff -> navController.navigate(screen.hotelLogin.name)
                    }
                }
            }

            composable(route = screen.userLogin.name) {
                UserLoginScreen(context = LocalContext.current, navController = navController)
            }

            composable(route = screen.signUp.name) {
                val normalUserDao = ApplicationInventory.getDatabase(LocalContext.current).normalUserDao()
                val newUserRegister = NewUserRegister(normalUserDao = normalUserDao)
                NewUserContent(
                    viewModel = NewUserRegister(normalUserDao = normalUserDao),
                    onSetEmail = { newUserRegister.email = it },
                    onSetName = { newUserRegister.name = it },
                    onSetNumber = { newUserRegister.phoneNumber = it },
                    onSetPassword = { newUserRegister.password = it },
                    onClickedButton = { name, phoneNumber, email, password ->
                        newUserRegister.insertUser(name, phoneNumber, email, password, UUID.randomUUID().toString())
                        Log.d("TravelScreen", "User data passed: $name, $phoneNumber, $email, $password")
                        navController.navigate(TravelBottomBar.UserHome.route)
                        viewModelhotel.getAllHotel()
                    }
                )
            }

            composable(route = screen.hotelLogin.name) {
                HotelLoginScreen(navController = navController)
            }

            composable(route = screen.newHotel.name) {
                val hotelUserDao = ApplicationInventory.getDatabase(LocalContext.current).hotelUserDao()
                val newHotelRegister = NewHotelRegister(hotelUserDao = hotelUserDao)
                var currentStep by remember { mutableStateOf(1) }
                var staffName by remember { mutableStateOf("") }
                var staffPhoneNumber by remember { mutableStateOf("") }
                var staffEmail by remember { mutableStateOf("") }
                var staffPassword by remember { mutableStateOf("") }

                if (currentStep == 1) {
                    StaffDetailsForm(
                        viewModel = newHotelRegister,
                        onNextClicked = {
                            currentStep = 2
                        },
                        onSetName = { staffName = it },
                        onSetNumber = { staffPhoneNumber = it },
                        onSetEmail = { staffEmail = it },
                        onSetPassword = { staffPassword = it }
                    )
                } else {
                    HotelDetailsForm(
                        viewModel = newHotelRegister,
                        onSubmitClicked = { hotelName, hotelAddress, hotelDescription, type ->
                            newHotelRegister.insertStaff(
                                staffName, staffPhoneNumber, staffEmail, staffPassword,
                                hotelName, hotelAddress, hotelDescription, type
                            )
                            Log.d("TravelScreen", "Hotel data passed:$staffName, $staffPhoneNumber, $staffEmail, $staffPassword,$hotelName, $hotelAddress, $hotelDescription, $type")
                            navController.navigate(HotelBottomBar.Home.route)
                        },
                        onSetHotelName = { newHotelRegister.hotelName = it },
                        onSetHotelAddress = { newHotelRegister.hotelAddress = it },
                        onSetHotelDescription = { newHotelRegister.hotelDescription = it },
                        onSetHotelType = { newHotelRegister.hotelType = it }
                    )
                }
            }

            composable(route = TravelBottomBar.UserHome.route) {
                HomeScreen(
                    onSelectedHotel = {
                        viewModel.setHotelId(it)
                        navController.navigate(screen.booking.name)
                    },
                    onSelectedHotelAddress = { viewModelhotel.setHomeAddress(it) },
                    onSelectedHotelDes = { viewModelhotel.setHomeDes(it) },
                    onSelectedHotelName = { viewModelhotel.setHomeName(it) },
                    availableHotel = viewModelhotel.bookings.value
                )
            }

            composable(route = screen.booking.name) {
                booking(
                    onNextButtonClicked = {
                        viewModel.updateRoomType(it)
                        navController.navigate(screen.detail.name)
                    },
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(navController) },
                    HotelName = uiHotelState.HotelName,
                    HotelId = viewModel.hotel_Id,
                    HotelAddress = uiHotelState.HotelAddress,
                    onPriceSet = { viewModel.updateRoomPrice(it) }
                )
            }

            composable(route = screen.detail.name) {
                bookingDetails(
                    onCancelButtonClicked = { cancelBacktoBookingScreen(navController) },
                    onNextButtonClicked = {
                        viewModel.AllBooking()
                        viewModel.calculatePrice()
                        navController.navigate(screen.summary.name)
                    },
                    OnBookingStartDateChange = { viewModel.updateBookingStartDate(it) },
                    OnBookingEndDateChange = { viewModel.updateBookingEndDate(it) },
                    OnPaxChange = { viewModel.updatePax(it) },
                    HotelId = viewModel.hotel_Id,
                    Price = viewModel.Price,
                    roomType = viewModel.setRoomType()
                )
            }

            composable(route = screen.summary.name) {
                bookingSummary(
                    onNextButtonClicked = { navController.navigate(screen.payment.name) },
                    onCancelButtonClicked = { cancelBacktoDetailsScreen(navController) },
                    BookingStartDate = uiState.BookedStartDate,
                    BookingEndDate = uiState.BookedEndDate,
                    Price = viewModel.totalPrice,
                    pax = uiState.Pax,
                    roomType = uiState.ROOMTYPE,
                    HotelId = uiState.HotelId
                )
            }

            composable(route = screen.payment.name) {
                PaymentLayout(
                    onClickedButton = {
                        navController.navigate(TravelBottomBar.UserHome.route)
                        viewModel.insertNewBooking()
                    }
                )
            }

            composable(route = TravelBottomBar.Favourite.route) {
                favoritelayout(
                    onSelectedHotel = { viewModel.setHotelId(it) },
                    onSelectedHotelAddress = { viewModelhotel.setHomeAddress(it) },
                    onSelectedHotelDes = { viewModelhotel.setHomeDes(it) },
                    onSelectedHotelName = { viewModelhotel.setHomeName(it) },
                    PriceRange = "",
                    onNextButton = { navController.navigate(TravelBottomBar.UserHome.route) },
                    favouriteHotel = viewModelhotel.Fav
                )
            }

            composable(route = TravelBottomBar.Booked.route) {
                Booked(
                    viewModel = viewModel(factory = BookedViewModelFactory(LocalContext.current))
                )
            }

            composable(route = TravelBottomBar.UserSettings.route) {
                UserSettingContent(navController = navController)
            }

            composable(route = HotelBottomBar.Home.route) {
                HotelReport(viewModel)
            }
            composable(route = HotelBottomBar.Reservation.route) {
                HotelReservation(navController, viewModelhotel, viewModel)
            }
            composable(route = HotelBottomBar.editRoom.route) {
                EditRooms(
                    viewModel = viewModel(factory = EditRoomsViewModelFactory(LocalContext.current))
                )
            }
            composable(route = HotelBottomBar.editBook.route) {
                EditBooking(
                    viewModel = viewModel(factory = EditBookingViewModelFactory(LocalContext.current))
                )
            }
            composable(route = HotelBottomBar.Settings.route) {
                HotelSettingContent(navController = navController)
            }
            composable(route = screen.addreservation.name) {
                AddReservation(navController = navController)
            }
        }
    }
}

private fun cancelOrderAndNavigateToStart(navController: NavController) {
    navController.popBackStack(TravelBottomBar.UserHome.route, inclusive = false)
}

private fun cancelBacktoBookingScreen(navController: NavController) {
    navController.popBackStack(screen.booking.name, inclusive = false)
}

private fun cancelBacktoDetailsScreen(navController: NavController) {
    navController.popBackStack(screen.detail.name, inclusive = false)
}
