package com.huda.passengerapp.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.huda.movie.ui.screen.main.Screens
import com.huda.passengerapp.presentation.main.BottomNavigationBar
import com.huda.passengerapp.ui.theme.ColorButton
import com.huda.passengerapp.ui.theme.ColorPrimary

@Composable
fun LoginScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .background(ColorPrimary)
        )

        // White Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Welcome to Passenger App",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Sign in to begin your journey",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(50.dp))

                BasicTextField(
                    value = email,
                    onValueChange = { email = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .padding(vertical = 16.dp, horizontal = 16.dp),
                )

                Text(
                    text = "Example: huda@gmail.com",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ColorButton
                    ),
                    onClick = {
                        navController.navigate(Screens.Main.route)
                      },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(Color.Transparent, shape = RoundedCornerShape(10.dp))
                ) {
                    Text(
                        text = "Sign In",
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Don't have an account?",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    border = BorderStroke(1.dp, ColorButton),
                    onClick = { /* Handle create new account */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(Color.Transparent, shape = RoundedCornerShape(10.dp)),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White,
                        contentColor = ColorButton,
                    )
                ) {
                    Text(text = "Create New Account")
                }
            }
        }
    }
}
