package com.andreirozov.tennisui.ui.clubs

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreirozov.tennisui.model.TrainItem

@Composable
fun ClubsTrainsList(
    context: Context,
    animationDuration: Int,
    isVisible: Boolean,
    trains: List<TrainItem>
) {
    Column {
        AnimatedVisibility(
            visible = isVisible,
            enter = scaleIn(
                animationSpec = tween(
                    durationMillis = animationDuration,
                    delayMillis = animationDuration,
                    easing = LinearOutSlowInEasing
                ),
                initialScale = 0f
            ),
            exit = scaleOut(
                animationSpec = tween(
                    durationMillis = animationDuration,
                    delayMillis = animationDuration,
                    easing = LinearOutSlowInEasing
                ),
                targetScale = 0f
            )
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 4.dp),
                text = "Train",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        trains.forEachIndexed { index, train ->
            ClubsTrainItemWidget(
                context = context,
                animationDuration = animationDuration,
                isVisible = isVisible,
                train = train,
                index = index
            )
        }
    }
}

