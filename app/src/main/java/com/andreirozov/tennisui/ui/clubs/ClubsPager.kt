package com.andreirozov.tennisui.ui.clubs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.andreirozov.tennisui.model.ClubItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClubsPager(
    navHostController: NavHostController,
    animationDuration: Int,
    isVisible: Boolean,
    clubs: List<ClubItem>
) {
    // Pager state
    val pagerState = rememberPagerState(
        pageCount = { clubs.size }
    )

    // Get primary color, required for Canvas, because haven't access to colorScheme inside
    val primaryColor = MaterialTheme.colorScheme.primary

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInHorizontally(
            animationSpec = tween(
                durationMillis = animationDuration,
                easing = LinearOutSlowInEasing
            ),
            initialOffsetX = { it }
        ),
        exit = slideOutHorizontally(
            animationSpec = tween(
                durationMillis = animationDuration,
                easing = LinearOutSlowInEasing
            ),
            targetOffsetX = { it }
        )
    ) {
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fixed(240.dp),
            pageSpacing = 16.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { page ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp)
                    .clickable {
                        navHostController.navigate(if (clubs[page].name == "Women's\nClub") "womenclub" else "menclub")
                    },
                shape = RoundedCornerShape(32.dp)
            ) {
                Box(
                    modifier = Modifier
                        .drawWithCache {
                            val path1 = Path().apply {
                                if (clubs[page].name == "Women's\nClub") {
                                    moveTo(
                                        x = 0f,
                                        y = size.height * 0.5f
                                    )
                                    quadraticBezierTo(
                                        x1 = size.width * 0.6f,
                                        y1 = size.height * 0.7f,
                                        x2 = size.width,
                                        y2 = size.height * 0.5f
                                    )
                                } else {
                                    moveTo(
                                        x = size.width * 0.65f,
                                        y = 0f
                                    )
                                    quadraticBezierTo(
                                        x1 = size.width * 0.18f,
                                        y1 = size.height * 0.27f,
                                        x2 = size.width,
                                        y2 = size.height * 0.75f
                                    )
                                }
                            }

                            val path2 = Path().apply {
                                if (clubs[page].name == "Women's\nClub") {
                                    moveTo(
                                        x = 0f,
                                        y = size.height * 0.78f
                                    )
                                    quadraticBezierTo(
                                        x1 = size.width * 0.55f,
                                        y1 = size.height * 0.7f,
                                        x2 = size.width * 0.4f,
                                        y2 = size.height
                                    )
                                } else {
                                    moveTo(
                                        x = 0f,
                                        y = size.height * 0.8f
                                    )
                                    quadraticBezierTo(
                                        x1 = size.width * 0.42f,
                                        y1 = size.height * 0.55f,
                                        x2 = size.width * 0.56f,
                                        y2 = size.height
                                    )
                                }
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
                            }
                        }
                ) {
                    Image(
                        modifier = Modifier
                            .height(340.dp)
                            .align(Alignment.BottomEnd)
                            .padding(end = 8.dp),
                        contentScale = ContentScale.FillHeight,
                        painter = painterResource(id = clubs[page].imageId),
                        contentDescription = "Club player photo"
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 32.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = clubs[page].name,
                            fontSize = 32.sp,
                            lineHeight = 32.sp
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Text(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(4.dp),
                            text = clubs[page].level,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            text = "${clubs[page].eventsCount} events",
                            fontSize = 16.sp,
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
        }
    }
}