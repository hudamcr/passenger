package com.huda.movie.ui.screen.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.huda.passengerapp.R

data class BottomNavigationItem(
    val label: String = "",
    val iconResId: Int = R.drawable.ic_home, // Change to your drawable resource ID
    val route: String = ""
) {

    //function to get the list of bottomNavigationItems
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Home",
                iconResId = R.drawable.ic_home,
                route = Screens.Home.route
            ),
            BottomNavigationItem(
                label = "Promotions",
                iconResId = R.drawable.ic_promotion,
                route = Screens.Promotions.route
            ),
            BottomNavigationItem(
                label = "Booking",
                iconResId = R.drawable.ic_add_circle,
                route = Screens.Booking.route
            ),
            BottomNavigationItem(
                label = "Inbox",
                iconResId = R.drawable.ic_inbox,
                route = Screens.Booking.route
            ),
            BottomNavigationItem(
                label = "More",
                iconResId = R.drawable.ic_more,
                route = Screens.Booking.route
            ),
        )
    }
}