package com.andreirozov.tennisui.model

import androidx.compose.ui.graphics.vector.ImageVector

data class TrainItem(
    val name: String,
    val icon: ImageVector,
    val time: String,
    val price: Int
)