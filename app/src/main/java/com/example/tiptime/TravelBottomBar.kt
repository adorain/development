package com.example.tiptime

import androidx.annotation.DrawableRes

sealed class TravelBottomBar(
    val route: String,
    val title: String,
    @DrawableRes val iconRes: Int
) {
    object UserHome : TravelBottomBar(
        route = "userhome",
        title = "Home",
        iconRes = R.drawable.home
    )
    object Favourite : TravelBottomBar(
        route = "favourite",
        title = "Favourite",
        iconRes = R.drawable.favourite
    )
    object Booked : TravelBottomBar(
        route = "booked",
        title = "Booked",
        iconRes = R.drawable.booked
    )
    object UserSettings : TravelBottomBar(
        route = "usersettings",
        title = "Settings",
        iconRes = R.drawable.settings
    )
}


sealed class HotelBottomBar(
    val route: String,
    val title: String,
    @DrawableRes val iconRes: Int
) {
    object Home : HotelBottomBar(
        route = "home",
        title = "Home",
        iconRes = R.drawable.home
    )
    object Reservation : HotelBottomBar(
        route = "reservation",
        title = "Reservation",
        iconRes = R.drawable.reservation
    )
    object editRoom : HotelBottomBar(
        route = "editRoom",
        title = "editRoom",
        iconRes = R.drawable.room
    )
    object editBook : HotelBottomBar(
        route = "editBook",
        title = "editBook",
        iconRes = R.drawable.room
    )
    object Settings : HotelBottomBar(
        route = "settings",
        title = "Settings",
        iconRes = R.drawable.settings
    )
}
