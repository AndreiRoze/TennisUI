package com.andreirozov.tennisui.ui.clubs

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreirozov.tennisui.model.TrainItem

@Composable
fun ClubsTrainItemWidget(
    context: Context,
    animationDuration: Int,
    isVisible: Boolean,
    train: TrainItem,
    index: Int
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            animationSpec = tween(
                durationMillis = animationDuration,
                delayMillis = animationDuration + animationDuration + 200 * index,
                easing = LinearOutSlowInEasing
            ),
            initialOffsetY = { it * 2 }
        ) + fadeIn(
            animationSpec = tween(
                durationMillis = animationDuration,
                delayMillis = animationDuration + animationDuration + 200 * index,
                easing = LinearOutSlowInEasing
            ),
            initialAlpha = 0f
        ),
        exit = slideOutVertically(
            animationSpec = tween(
                durationMillis = animationDuration,
                delayMillis = animationDuration + animationDuration + 200 * index,
                easing = LinearOutSlowInEasing
            ),
            targetOffsetY = { it * 2 }
        ) + fadeOut(
            animationSpec = tween(
                durationMillis = animationDuration,
                delayMillis = animationDuration + animationDuration + 200 * index,
                easing = LinearOutSlowInEasing
            ),
            targetAlpha = 0f
        )
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    Toast
                        .makeText(context, "Train clicked", Toast.LENGTH_SHORT)
                        .show()
                }
                .padding(horizontal = 16.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = train.icon, contentDescription = "Train icon"
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = train.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.alpha(0.5f),
                    text = train.time,
                    fontSize = 14.sp,
                )
            }

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$${train.price}",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 14.sp,
                )
            }
        }
    }
}