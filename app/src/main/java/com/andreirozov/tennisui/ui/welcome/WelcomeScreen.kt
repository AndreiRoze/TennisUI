package com.andreirozov.tennisui.ui.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.andreirozov.tennisui.R
import com.andreirozov.tennisui.model.LevelItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(navHostController: NavHostController) {
    // List with levels
    val levels = remember {
        listOf(
            LevelItem("Beginner", Icons.Default.ThumbUp),
            LevelItem("Average", Icons.Default.ThumbUp),
            LevelItem("On experience", Icons.Default.ThumbUp),
            LevelItem("Professional", Icons.Default.ThumbUp),
        )
    }

    // Pager state
    val pagerState = rememberPagerState(
        pageCount = { levels.size }
    )

    // Get primary color, required for Canvas, because haven't access to colorScheme inside
    val primaryColor = MaterialTheme.colorScheme.primary

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .drawWithCache {
                    val path1 = Path().apply {
                        moveTo(
                            x = 0f,
                            y = size.height * 0.08f
                        )
                        quadraticBezierTo(
                            x1 = size.width * 0.3f,
                            y1 = size.height * 0.08f,
                            x2 = size.width * 0.4f,
                            y2 = 0f
                        )
                    }

                    val path2 = Path().apply {
                        moveTo(
                            x = 0f,
                            y = size.height * 0.8f
                        )
                        quadraticBezierTo(
                            x1 = size.width * 0.28f,
                            y1 = size.height * 0.85f,
                            x2 = size.width * 0.3f,
                            y2 = size.height
                        )
                    }

                    val path3 = Path().apply {
                        moveTo(
                            x = size.width,
                            y = size.height * 0.78f
                        )
                        quadraticBezierTo(
                            x1 = size.width * 0.8f,
                            y1 = size.height * 0.88f,
                            x2 = size.width,
                            y2 = size.height * 0.98f
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
                .padding(top = 64.dp, bottom = 8.dp)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(64.dp)
        ) {
            Text(
                text = "Choose your level",
                fontSize = 20.sp
            )

            WelcomePager(
                pagerState = pagerState,
                levels = levels
            )

            NextButton(
                navHostController = navHostController,
                level = levels[pagerState.currentPage]
            )
        }
    }
}

@Composable
private fun NextButton(
    navHostController: NavHostController,
    level: LevelItem
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FilledIconButton(
            modifier = Modifier.size(56.dp),
            shape = CircleShape,
            onClick = {
                navHostController.navigate("clubs/${level.name}")
            }
        ) {
            Icon(
                modifier = Modifier.offset(x = 2.dp),
                painter = painterResource(id = R.drawable.skip_icon),
                contentDescription = "Skip icon"
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    navHostController.navigate("clubs/none")
                }
                .padding(horizontal = 4.dp),
            text = "Skip for now",
            fontSize = 14.sp
        )
    }
}