package com.andreirozov.tennisui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andreirozov.tennisui.ui.clubs.ClubsScreen
import com.andreirozov.tennisui.ui.menclub.MenClubScreen
import com.andreirozov.tennisui.ui.theme.TennisUITheme
import com.andreirozov.tennisui.ui.welcome.WelcomeScreen
import com.andreirozov.tennisui.ui.womenclub.WomenClubScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TennisUIApp()
        }
    }
}

@Composable
fun TennisUIApp() {
    // navHostController
    val navHostController = rememberNavController()

    TennisUITheme {
        NavHost(
            navController = navHostController,
            startDestination = "welcome"
        ) {
            composable("welcome") {
                WelcomeScreen(navHostController = navHostController)
            }

            composable("clubs/{level}") { backStackEntry ->
                ClubsScreen(
                    navHostController = navHostController,
                    level = backStackEntry.arguments?.getString("level") ?: "none"
                )
            }

            composable("menclub") {
                MenClubScreen(navHostController = navHostController)
            }

            composable("womenclub") {
                WomenClubScreen(navHostController = navHostController)
            }
        }
    }
}