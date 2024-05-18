package com.example.tiptime

import androidx.annotation.DrawableRes

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
    object Room : HotelBottomBar(
        route = "room",
        title = "Room",
        iconRes = R.drawable.room
    )
    object Settings : HotelBottomBar(
        route = "settings",
        title = "Settings",
        iconRes = R.drawable.settings
    )
}
