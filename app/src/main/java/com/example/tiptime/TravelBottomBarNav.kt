package com.example.tiptime

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun TravelBottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home, TravelBottomBar.UserHome.route),
        BottomNavItem("Favourite", Icons.Default.Favorite, TravelBottomBar.Favourite.route),
        BottomNavItem("Booked", Icons.Default.Check, TravelBottomBar.Booked.route),
        BottomNavItem("Settings", Icons.Default.Settings, TravelBottomBar.UserSettings.route)
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 8.dp
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route
        val viewModel : hotelViewModel = viewModel(factory = AppViewModelProvider.factory)

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true

                        if(item.route == TravelBottomBar.Favourite.route){
                            viewModel.getFavorite()
                        }
                        if(item.route == TravelBottomBar.UserHome.route){
                            viewModel.getAllHotel()
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}

@Composable
fun HotelBottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home, HotelBottomBar.Home.route),
        BottomNavItem("Reservate", Icons.Default.DateRange, HotelBottomBar.Reservation.route),
        BottomNavItem("Room", Icons.Default.Edit, HotelBottomBar.editRoom.route),
        BottomNavItem("Book", Icons.Default.Edit, HotelBottomBar.editBook.route),
        BottomNavItem("Settings", Icons.Default.Settings, HotelBottomBar.Settings.route)
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 8.dp
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}

data class BottomNavItem(val title: String, val icon: ImageVector, val route: String)
