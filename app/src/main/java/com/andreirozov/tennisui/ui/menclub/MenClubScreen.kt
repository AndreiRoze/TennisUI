package com.andreirozov.tennisui.ui.menclub

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andreirozov.tennisui.R

@Composable
fun MenClubScreen(navHostController: NavHostController) {
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

    // Get primary color, required for Canvas, because haven't access to colorScheme inside
    val primaryColor = MaterialTheme.colorScheme.primary

    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    val path1 = Path().apply {
                        moveTo(
                            x = size.width * 0.7f,
                            y = 0f
                        )
                        quadraticBezierTo(
                            x1 = size.width * 0.72f,
                            y1 = size.height * 0.18f,
                            x2 = size.width,
                            y2 = size.height * 0.12f
                        )
                    }

                    val path2 = Path().apply {
                        moveTo(
                            x = 0f,
                            y = size.height * 0.35f
                        )
                        quadraticBezierTo(
                            x1 = size.width * 0.25f,
                            y1 = size.height * 0.42f,
                            x2 = 0f,
                            y2 = size.height * 0.5f
                        )
                    }

                    val path3 = Path().apply {
                        moveTo(
                            x = 0f,
                            y = size.height * 0.8f
                        )
                        quadraticBezierTo(
                            x1 = size.width * 0.3f,
                            y1 = size.height * 0.35f,
                            x2 = size.width,
                            y2 = size.height * 0.5f
                        )
                    }

                    onDrawBehind {
                        drawPath(
                            path = path1,
                            color = primaryColor,
                            style = Stroke(width = 3f)
                        )

                        drawPath(
                            path = path2,
                            color = primaryColor,
                            style = Stroke(width = 3f)
                        )

                        drawPath(
                            path = path3,
                            color = primaryColor,
                            style = Stroke(width = 3f)
                        )
                    }
                }
                .padding(it)
        ) {
            MenClubBottomSheet(
                modifier = Modifier.align(Alignment.BottomEnd),
                context = context,
                animationDuration = animationDuration,
                isVisible = isVisible
            )

            ButtonWidget(
                navHostController = navHostController,
                modifier = Modifier.align(Alignment.TopStart),
                animationDuration = animationDuration,
                isVisible = isVisible
            )
        }
    }
}

@Composable
private fun ButtonWidget(
    navHostController: NavHostController,
    modifier: Modifier,
    animationDuration: Int,
    isVisible: Boolean
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInHorizontally(
            animationSpec = tween(
                durationMillis = animationDuration,
                easing = LinearOutSlowInEasing
            ),
            initialOffsetX = { -(it * 2) }
        ),
        exit = slideOutVertically(
            animationSpec = tween(
                durationMillis = animationDuration,
                easing = LinearOutSlowInEasing
            ),
            targetOffsetY = { -(it * 2) }
        )
    ) {
        FilledIconButton(
            modifier = modifier
                .padding(16.dp)
                .size(56.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = {
                navHostController.navigateUp()
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.back_icon),
                contentDescription = "Menu icon"
            )
        }
    }
}