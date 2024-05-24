package com.example.tiptime


import Booked
import TopAppBar
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tiptime.Data.ApplicationInventory
import com.example.tiptime.ui.theme.TipTimeTheme
import java.util.Date

enum class screen{
    signUp,home , booking , detail, summary,payment,test
}

enum class UserType{
    user , staff
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


    Scaffold(
        topBar = {
            if (navController.currentBackStackEntry?.destination?.route !in listOf(screen.booking.name, screen.detail.name,screen.summary.name,screen.payment.name)) {
                TopAppBar()
            }
        },
        bottomBar = {
            if (navController.currentBackStackEntry?.destination?.route !in listOf(screen.booking.name, screen.detail.name,screen.summary.name,screen.payment.name)) {
                TravelBottomNavigationBar(navController = navController)
            }

        }
    ) { innerPadding ->
        val uiState by viewModel.uiBookingState.collectAsState()
        val uiHotelState by viewModelhotel.uiStateHotel.collectAsState()
        NavHost(
            navController = navController,
            startDestination = screen.signUp.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = screen.signUp.name){
                val normalUserDao = ApplicationInventory.getDatabase(LocalContext.current).normalUserDao()
                NewUserContent(viewModel = NewUserRegister(normalUserDao = normalUserDao),
                    onSetEmail = {it},
                    onSetName = {it},
                    onSetNumber = {it},
                    onSetPassword = {it},
                    onClickedButton = {
                        navController.navigate(screen.home.name)
                        viewModelhotel.getAllHotel()

                    }

                )
            }
            composable(route = screen.home.name) {
                HomeScreen(
                    onSelectedHotel = {
                        viewModel.setHotelId(it)
                        navController.navigate(screen.booking.name)
                    },
                    onSelectedHotelAddress = { viewModelhotel.setHomeAddress(it) },
                    onSelectedHotelDes = { viewModelhotel.setHomeDes(it) },
                    onSelectedHotelName = { viewModelhotel.setHomeName(it) },
                    availableHotel = viewModelhotel.bookings.value
                    /*onSearch = {viewModelhotel.updateSearchText(it)},
                    onSelectedEndDate = {viewModelhotel.updateEndDate(it)},
                    onSelectedPax = {viewModelhotel.updatePax(it)},
                    onSelectedStartDate = {viewModelhotel.updateStartDate(it)},
                    hotel = viewModelhotel.getAllHotel(),
                    availableHotel = viewModelhotel.searchHotel(viewModelhotel.searchtext,viewModel.BookedStartDate,viewModel.BookedEndDate,viewModel.Pax)

                     */

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
                    HotelId = uiHotelState.HotelId,
                    HotelAddress = uiHotelState.HotelAddress,
                    onPriceSet = { viewModel.updateRoomPrice(it) }
                    //status = viewRoomViewModel.checkRoomStatus()

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
                    roomType = viewModel.setRoomType(),
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
                        navController.navigate(screen.home.name)
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
                    PriceRange = "", onNextButton = { navController.navigate(screen.home.name) },
                    favouriteHotel = viewModelhotel.Fav
                )
            }
            composable(route = TravelBottomBar.Booked.route) {
                Booked(
                    viewModel = viewModel(factory = BookedViewModelFactory(LocalContext.current))
                )
            }
            composable(route = TravelBottomBar.Settings.route) {
                //UserSettingContent(onLogout = { /*TODO*/ }, onCurrency = { /*TODO*/ }) {
                //}
            }


        }
    }
}

    private fun cancelOrderAndNavigateToStart(
        navController: NavController
    ) {

        navController.popBackStack(screen.home.name, inclusive = false)
    }

    private fun cancelBacktoBookingScreen(
        navController: NavController
    ) {
        navController.popBackStack(screen.booking.name, inclusive = false)
    }

    private fun cancelBacktoDetailsScreen(
        navController: NavController
    ) {
        navController.popBackStack(screen.detail.name, inclusive = false)
    }







