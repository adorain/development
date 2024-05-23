package com.example.tiptime

import androidx.annotation.DrawableRes

sealed class TravelBottomBar(
    val route: String,
    val title: String,
    @DrawableRes val iconRes: Int
) {
    object Home : TravelBottomBar(
        route = "home",
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
    object Settings : TravelBottomBar(
        route = "settings",
        title = "Settings",
        iconRes = R.drawable.settings
    )
}
