package com.andreirozov.tennisui.ui.clubs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andreirozov.tennisui.R
import com.andreirozov.tennisui.model.ClubItem
import com.andreirozov.tennisui.model.TrainItem

@Composable
fun ClubsScreen(navHostController: NavHostController, level: String) {
    // Visibility of widgets, required for animations
    var isVisible by rememberSaveable { mutableStateOf(false) }

    // Duration of animations
    val animationDuration = 500

    // Launch animation only when screen launched
    LaunchedEffect(key1 = Unit) {
        isVisible = true
    }

    // Get context, required for toast
    val context = LocalContext.current

    // List with clubs
    val clubs = remember {
        listOf(
            ClubItem(
                name = "Women's\nClub",
                level = "All levels",
                eventsCount = 3,
                imageId = R.drawable.women_club_card_image
            ), ClubItem(
                name = "Men's\nClub",
                level = "All levels",
                eventsCount = 2,
                imageId = R.drawable.men_club_card_image
            )
        )
    }

    // List with trains
    val trains = remember {
        listOf(
            TrainItem("Yoga & Tennis", Icons.Default.ThumbUp, "Jul 13 | 10:00am - 11:00am", 10),
            TrainItem("Beginner Bootcamp", Icons.Default.ThumbUp, "Jul 25 | 12:00pm - 3:00pm", 10),
            TrainItem("Men's league", Icons.Default.ThumbUp, "Jul 27 | 10:00am - 2:00pm", 30)
        )
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(vertical = 16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ClubsHeader(
                context = context
            )

            ClubsPager(
                navHostController = navHostController,
                animationDuration = animationDuration,
                isVisible = isVisible,
                clubs = clubs
            )

            ClubsTrainsList(
                context = context,
                animationDuration = animationDuration,
                isVisible = isVisible,
                trains = trains
            )
        }
    }
}

