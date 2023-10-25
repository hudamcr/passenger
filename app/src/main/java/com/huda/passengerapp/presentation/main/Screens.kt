package com.huda.movie.ui.screen.main

sealed class Screens(val route : String) {
    object Login: Screens("login_route")
    object Main: Screens("main_route")
    object Home : Screens("home_route")
    object Promotions : Screens("promotions_route")
    object Booking : Screens("booking_route")
    object Inbox : Screens("inbox_route")
    object More : Screens("more_route")
}
