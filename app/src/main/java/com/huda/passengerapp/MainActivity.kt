package com.huda.passengerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.huda.movie.ui.screen.main.Screens
import com.huda.passengerapp.presentation.LoginScreen
import com.huda.passengerapp.presentation.main.BottomNavigationBar
import com.huda.passengerapp.ui.theme.PassengerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PassengerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.Login.route,
                    ) {
                        composable(Screens.Login.route) {
                            LoginScreen(navController)
                        }
                        composable(Screens.Main.route) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PassengerAppTheme {
        Greeting("Android")
    }
}