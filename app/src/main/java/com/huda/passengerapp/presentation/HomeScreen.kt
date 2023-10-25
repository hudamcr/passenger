package com.huda.passengerapp.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.huda.passengerapp.R
import com.huda.passengerapp.ui.theme.ColorCard
import com.huda.passengerapp.ui.theme.ColorPrimary
import com.huda.passengerapp.ui.theme.ColorSecondary
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(navController: NavController) {

    val sliderList = remember {
        mutableListOf(
            "https://www.gstatic.com/webp/gallery/1.webp",
            "https://www.gstatic.com/webp/gallery/2.webp",
            "https://www.gstatic.com/webp/gallery/3.webp",
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f)
                .background(ColorPrimary)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .border(2.dp, ColorSecondary, CircleShape)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.img_photo),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Achmad Miftachul Huda", fontSize = 14.sp, color = Color.White)
                        Text(text = "Where you wanna go?", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalCardTripList()
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                GridScreen()
                CustomSlider(sliderList = sliderList)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun CustomSlider(
    modifier: Modifier = Modifier,
    sliderList: MutableList<String>,
    dotsActiveColor: Color = Color.DarkGray,
    dotsInActiveColor: Color = Color.LightGray,
    dotsSize: Dp = 8.dp,
    pagerPaddingValues: PaddingValues = PaddingValues(horizontal = 15.dp),
    imageCornerRadius: Dp = 8.dp,
    imageHeight: Dp = 150.dp,
) {

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {


            HorizontalPager(
                sliderList.size,
                state = pagerState,
                contentPadding = pagerPaddingValues,
                modifier = modifier.weight(1f)
            ) { page ->
                val pageOffset =
                    (pagerState.currentPage - page) + pagerState.currentPageOffset

                val scaleFactor = 0.75f + (1f - 0.75f) * (1f - pageOffset.absoluteValue)


                Box(modifier = modifier
                    .graphicsLayer {
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                    }
                    .alpha(
                        scaleFactor.coerceIn(0f, 1f)
                    )
                    .padding(10.dp)
                    .clip(RoundedCornerShape(imageCornerRadius))) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).scale(Scale.FILL)
                            .crossfade(true).data(sliderList[page]).build(),
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(id = R.drawable.ic_launcher_background),
                        modifier = modifier.height(imageHeight)
                    )
                }
            }
        }
        Row(
            modifier
                .height(50.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            repeat(sliderList.size) {
                val color = if (pagerState.currentPage == it) dotsActiveColor else dotsInActiveColor
                Box(modifier = modifier
                    .padding(2.dp)
                    .clip(RectangleShape)
                    .width(dotsSize * 2)
                    .height(dotsSize)
                    .background(color)
                    .clickable {
                        scope.launch {
                            pagerState.animateScrollToPage(it)
                        }
                    })
            }
        }
    }
}

@Composable
fun HorizontalCardTripList() {
    LazyRow {
        items(3) {
            when (it) {
                0 -> CardItem("UPCOMING TRIP", "GA823", "Boarding Time", "Gate Open", "14:00 WIB", "13:00 WIB")
                1 -> CardItem("UPCOMING TRIP", "GA824", "Boarding Time", "Gate Open", "15:00 WIB", "14:00 WIB")
                2 -> CardItem("UPCOMING TRIP", "GA825", "Boarding Time", "Gate Open", "16:00 WIB", "15:00 WIB")
            }
        }
    }
}

@Composable
fun CardItem(text1: String, text2: String, text3: String, text4: String, text5: String, text6: String) {
    Card(
        modifier = Modifier
            .width(250.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(
            containerColor = ColorCard,
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = text1, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = text2, fontSize = 12.sp, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = text3, fontSize = 12.sp, color = Color.White)
                    Text(text = text5, fontSize = 12.sp, color = Color.White)
                }
                Column {
                    Text(text = text4, fontSize = 12.sp, color = Color.White)
                    Text(text = text6, fontSize = 12.sp, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun GridScreen() {
    LazyVerticalGrid(
        modifier = Modifier.padding(16.dp),
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(6) { item ->
            when (item) {
                0 -> GridItem(R.drawable.ic_flight_ticket, "Flight Ticket")
                1 -> GridItem(R.drawable.ic_pre_travel, "Pre Travel")
                2 -> GridItem(R.drawable.ic_checkin, "Check In")
                3 -> GridItem(R.drawable.ic_flight_info, "Flight Info")
                4 -> GridItem(R.drawable.ic_jasa_titip, "Jasa Titip")
                5 -> GridItem(R.drawable.ic_amenities, "Amenities")
            }
        }
    }
}

@Composable
fun GridItem(drawableId: Int, text: String) {
    val image = painterResource(id = drawableId)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(ColorPrimary)
                .padding(8.dp),
            tint = Color.White
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text,
            fontSize = 12.sp
        )
    }
}