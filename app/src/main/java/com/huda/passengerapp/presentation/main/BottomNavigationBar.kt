package com.huda.passengerapp.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.huda.movie.ui.screen.main.BottomNavigationItem
import com.huda.movie.ui.screen.main.Screens
import com.huda.passengerapp.presentation.BookingScreen
import com.huda.passengerapp.presentation.HomeScreen
import com.huda.passengerapp.presentation.InboxScreen
import com.huda.passengerapp.presentation.MoreScreen
import com.huda.passengerapp.presentation.PromotionsScreen
import com.huda.passengerapp.ui.theme.ColorButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(navController: NavController) {
    var navigationSelectedItem by remember {
        mutableStateOf(0)
    }
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                BottomNavigationItem().bottomNavigationItems().forEachIndexed { index, navigationItem ->
                    val isSelected = index == navigationSelectedItem
                    NavigationBarItem(
                        selected = isSelected,
                        label = {
                            Text(
                                navigationItem.label,
                                fontSize = 10.sp)
                        },
                        icon = {
                            val iconPainter = painterResource(id = navigationItem.iconResId)
                            Icon(
                                painter = iconPainter,
                                modifier = Modifier
                                    .size(if(index==2) 35.dp else 20.dp),
                                tint = if(index == 2) ColorButton else Color.Black,
                                contentDescription = navigationItem.label
                            )
                        },
                        onClick = {
                            navigationSelectedItem = index
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier.padding(paddingValues = paddingValues)) {
            composable(Screens.Home.route) {
                HomeScreen(
                    navController
                )
            }
            composable(Screens.Promotions.route) {
                PromotionsScreen(
                    navController
                )
            }
            composable(Screens.Booking.route) {
                BookingScreen(
                    navController
                )
            }
            composable(Screens.Inbox.route) {
                InboxScreen(
                    navController
                )
            }
            composable(Screens.More.route) {
                MoreScreen(
                    navController
                )
            }
        }
    }
}