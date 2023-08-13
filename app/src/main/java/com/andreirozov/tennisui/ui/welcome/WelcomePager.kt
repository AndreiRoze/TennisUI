package com.andreirozov.tennisui.ui.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.andreirozov.tennisui.model.LevelItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomePager(
    pagerState: PagerState,
    levels: List<LevelItem>
) {
    // Get screen width
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    HorizontalPager(
        state = pagerState,
        pageSize = PageSize.Fixed(screenWidth - 48.dp * 2),
        pageSpacing = 24.dp,
        contentPadding = PaddingValues(horizontal = 48.dp)
    ) { page ->
        Card(
            modifier = Modifier
                .height(380.dp)
                .fillMaxWidth()
                .graphicsLayer {
                    val pageOffset =
                        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)

                    lerp(
                        start = 0f,
                        stop = 1f,
                        fraction = pageOffset * 2
                    ).also { scale ->
                        rotationZ = -scale
                    }
                },
            shape = RoundedCornerShape(32.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = levels[page].icon,
                    contentDescription = "Level icon"
                )

                Spacer(
                    modifier = Modifier.height(160.dp)
                )

                Text(
                    text = levels[page].name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}