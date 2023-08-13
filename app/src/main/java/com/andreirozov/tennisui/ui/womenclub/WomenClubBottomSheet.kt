package com.andreirozov.tennisui.ui.womenclub

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreirozov.tennisui.R

@Composable
fun WomenClubBottomSheet(
    modifier: Modifier,
    context: Context,
    animationDuration: Int,
    isVisible: Boolean
) {
    AnimatedVisibility(
        modifier = modifier.fillMaxWidth(),
        visible = isVisible,
        enter = slideInVertically(
            animationSpec = tween(
                durationMillis = animationDuration,
                easing = LinearOutSlowInEasing
            ),
            initialOffsetY = { it }
        ),
        exit = slideOutVertically(
            animationSpec = tween(
                durationMillis = animationDuration,
                easing = LinearOutSlowInEasing
            ),
            targetOffsetY = { it }
        )
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.height(360.dp),
                contentScale = ContentScale.FillHeight,
                painter = painterResource(id = R.drawable.women_club_image),
                contentDescription = "Women club player photo"
            )

            Card(
                shape = RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    HeaderWidget()

                    DescriptionWidget()

                    RowDataWidget(
                        animationDuration = animationDuration
                    )

                    ButtonWidget(
                        context = context,
                        animationDuration = animationDuration
                    )
                }
            }
        }
    }
}

@Composable
private fun HeaderWidget() {
    Text(
        text = "Women's club",
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun DescriptionWidget() {
    Text(
        modifier = Modifier.alpha(0.5f),
        text = "Take part in a 2-hour session where you can expect plenty of rallying followed by competitive point play team singles style",
        fontSize = 14.sp
    )
}

@Composable
private fun RowDataWidget(
    animationDuration: Int
) {
    // Visibility of boxes, required for animations
    var isVisible by rememberSaveable { mutableStateOf(false) }

    // Launch animation only when screen launched
    LaunchedEffect(key1 = Unit) {
        isVisible = true
    }

    Row(
        modifier = Modifier.height(120.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AnimatedVisibility(
            modifier = Modifier.weight(0.333f),
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
            Box(
                modifier = Modifier.border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(16.dp)
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(R.drawable.calendar_icon),
                        contentDescription = "Date icon"
                    )

                    Text(
                        text = "26 July",
                        fontSize = 14.sp
                    )
                }
            }
        }

        AnimatedVisibility(
            modifier = Modifier.weight(0.333f),
            visible = isVisible,
            enter = scaleIn(
                animationSpec = tween(
                    durationMillis = animationDuration,
                    delayMillis = animationDuration + 100,
                    easing = LinearOutSlowInEasing
                ),
                initialScale = 0f
            ),
            exit = scaleOut(
                animationSpec = tween(
                    durationMillis = animationDuration,
                    delayMillis = animationDuration + 100,
                    easing = LinearOutSlowInEasing
                ),
                targetScale = 0f
            )
        ) {
            Box(
                modifier = Modifier.border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(16.dp)
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(R.drawable.clock_icon),
                        contentDescription = "Clock icon"
                    )

                    Text(
                        text = "15:00-17:00",
                        fontSize = 14.sp
                    )
                }
            }
        }

        AnimatedVisibility(
            modifier = Modifier.weight(0.333f),
            visible = isVisible,
            enter = scaleIn(
                animationSpec = tween(
                    durationMillis = animationDuration,
                    delayMillis = animationDuration + 200,
                    easing = LinearOutSlowInEasing
                ),
                initialScale = 0f
            ),
            exit = scaleOut(
                animationSpec = tween(
                    durationMillis = animationDuration,
                    delayMillis = animationDuration + 200,
                    easing = LinearOutSlowInEasing
                ),
                targetScale = 0f
            )
        ) {
            Box(
                modifier = Modifier.border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(16.dp)
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(R.drawable.star_icon),
                        contentDescription = "Level icon"
                    )

                    Text(
                        text = "All levels",
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun ButtonWidget(
    context: Context,
    animationDuration: Int
) {
    // Visibility of button, required for animations
    var isVisible by rememberSaveable { mutableStateOf(false) }

    // Launch animation only when screen launched
    LaunchedEffect(key1 = Unit) {
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            animationSpec = tween(
                durationMillis = animationDuration,
                delayMillis = animationDuration + 200 + animationDuration,
                easing = LinearOutSlowInEasing
            ),
            initialOffsetY = { it * 2 }
        ),
        exit = slideOutVertically(
            animationSpec = tween(
                durationMillis = animationDuration,
                delayMillis = animationDuration + 200 + animationDuration,
                easing = LinearOutSlowInEasing
            ),
            targetOffsetY = { it * 2 }
        )
    ) {
        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(16.dp),
            onClick = {
                Toast.makeText(context, "Participate clicked", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text(
                text = "Participate $35",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}