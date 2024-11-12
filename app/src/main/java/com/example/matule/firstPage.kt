package com.example.matule

import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.matule.ui.theme._0076B1
import com.example.matule.ui.theme._2B2B2B
import com.example.matule.ui.theme._48B2E7
import com.example.matule.ui.theme._D8D8D8
import com.example.matule.ui.theme._ECECEC


val fontFamilyRaleway = FontFamily(Font(resId = R.font.raleway_regular))
val fontFamilyPoppins = FontFamily(Font(resId = R.font.poppins_regular))
val fontFamilyFutura = FontFamily(Font(R.font.futura_regular))
val fontFamilyWorkSans = FontFamily(Font(R.font.work_sans_regular))
val fontFamilyMasiva = FontFamily(Font(R.font.masiva))

@Composable
fun FirstPage(onClick: () -> Unit) {
    val brush = Brush.verticalGradient(
        listOf(
            _48B2E7,
            _0076B1
        )
    )
    Box(
        modifier = Modifier
            .background(brush = brush)
            .fillMaxSize()
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .padding(top = 322.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Matule",
                style = TextStyle(
                    fontSize = 65.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = fontFamilyRaleway
                )
            )
            Text(
                modifier = Modifier.padding(
                    start = 5.dp,
                    bottom = 20.dp
                ),
                text = "Me",
                style = TextStyle(
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = fontFamilyRaleway
                )
            )
        }
    }
}

fun timer1() {
    val timer = object : CountDownTimer(3000, 1000) {
        override fun onTick(p0: Long) {
            TODO("Not yet implemented")
        }

        override fun onFinish() {

        }

    }
    timer.start()
}


@Composable
fun OnBoard1(onClick: () -> Unit) {
    val brush = Brush.verticalGradient(
        listOf(
            _48B2E7,
            _0076B1
        )
    )
    Box(
        modifier = Modifier
            .background(brush = brush)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    text = "Добро",
                    style = TextStyle(
                        fontFamily = fontFamilyRaleway,
                        color = _ECECEC,
                        fontSize = 30.sp
                    ),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "пожаловать",
                    style = TextStyle(
                        fontFamily = fontFamilyRaleway,
                        color = _ECECEC,
                        fontSize = 30.sp
                    ),
                    textAlign = TextAlign.Center,
                )
                Box() {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(325.dp),
                            painter = painterResource(R.drawable.foot_image),
                            contentDescription = "im1",
                            contentScale = ContentScale.Crop
                        )
                        Row(modifier = Modifier.padding(top = 10.dp)) {
                            Box(
                                modifier = Modifier
                                    .background(Color.White)
                                    .size(
                                        width = 40.dp,
                                        height = 3.dp
                                    )
                            )
                            Box(
                                modifier = Modifier.padding(horizontal = 5.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .background(Color.DarkGray)
                                    .size(
                                        width = 30.dp,
                                        height = 3.dp
                                    )
                            )
                            Box(
                                modifier = Modifier.padding(horizontal = 5.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .background(Color.DarkGray)
                                    .size(
                                        width = 30.dp,
                                        height = 3.dp
                                    )
                            )
                        }
                    }
                }

            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 50.dp,
                        start = 20.dp,
                        end = 20.dp,
                    ),
                onClick = {
                    onClick()
                },
                shape = RoundedCornerShape(3.dp),
                colors = ButtonColors(
                    containerColor = Color.White,
                    contentColor = _2B2B2B,
                    disabledContentColor = Color.White,
                    disabledContainerColor = Color.Black
                )
            ) {
                Text(
                    text = "Начать",
                    style = TextStyle(
                        color = _2B2B2B,
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}

@Composable
fun OnBoard2(onClick: () -> Unit) {
    val brush = Brush.verticalGradient(
        listOf(
            _48B2E7,
            _0076B1
        )
    )
    DoubleBoard(
        brush, firstLineText = "Начнем", secondLineText = "путешествие",
        painter = painterResource(R.drawable.foot_image2),
        firstLineText1 = "Умная, великолепная и модная",
        secondLineText1 = "коллекция Изучите сейчас", 1,
        onClick = onClick
    )
}

@Composable
fun DoubleBoard(
    brush: Brush, firstLineText: String, secondLineText: String,
    painter: Painter, firstLineText1: String, secondLineText1: String, state: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(brush = brush)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.padding(top = 50.dp)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (state == 1) {
                        Image(
                            modifier = Modifier.fillMaxWidth(),
                            painter = painter,
                            contentDescription = "im2",
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(
                            modifier = Modifier.size(400.dp),
                            painter = painter,
                            contentDescription = "im2",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Box{
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = firstLineText,
                        style = TextStyle(
                            fontFamily = fontFamilyRaleway,
                            color = _ECECEC,
                            fontSize = 34.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        text = secondLineText,
                        style = TextStyle(
                            fontFamily = fontFamilyRaleway,
                            color = _ECECEC,
                            fontSize = 34.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        modifier = Modifier.padding(top = 25.dp),
                        text = firstLineText1,
                        style = TextStyle(
                            fontFamily = fontFamilyPoppins,
                            color = _D8D8D8,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        text = secondLineText1,
                        style = TextStyle(
                            fontFamily = fontFamilyPoppins,
                            color = _D8D8D8,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                    Row(modifier = Modifier.padding(top = 30.dp)) {
                        Box(
                            modifier = Modifier
                                .background(Color.DarkGray)
                                .size(
                                    width = 30.dp,
                                    height = 3.dp
                                )
                        )
                        Box(
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                        Box(
                            modifier = Modifier
                                .background(
                                    if (state == 1) {
                                        Color.White
                                    } else {
                                        Color.DarkGray
                                    }
                                )
                                .size(
                                    width =
                                    if (state == 1) {
                                        40.dp
                                    } else {
                                        30.dp
                                    },
                                    height = 3.dp
                                )
                        )
                        Box(
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                        Box(
                            modifier = Modifier
                                .background(
                                    if (state == 2) {
                                        Color.White
                                    } else {
                                        Color.DarkGray
                                    }
                                )
                                .size(
                                    width =
                                    if (state == 2) {
                                        40.dp
                                    } else {
                                        30.dp
                                    },
                                    height = 3.dp
                                )
                        )
                    }
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 50.dp,
                        start = 20.dp,
                        end = 20.dp,
                    ),
                onClick = {
                    onClick()
                },
                shape = RoundedCornerShape(13.dp),
                colors = ButtonColors(
                    containerColor = Color.White,
                    contentColor = _2B2B2B,
                    disabledContentColor = Color.White,
                    disabledContainerColor = Color.Black
                )
            ) {
                Text(
                    text = "Далее",
                    style = TextStyle(
                        color = _2B2B2B,
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}

@Composable
fun OnBoard3(onClick: () -> Unit) {
    val brush = Brush.verticalGradient(
        listOf(
            _48B2E7,
            _0076B1
        )
    )
    DoubleBoard(
        brush = brush,
        firstLineText = "У вас есть сила,",
        secondLineText = "Чтобы",
        painter = painterResource(R.drawable.foot_image3),
        firstLineText1 = "В вашей комнате много красивых и",
        secondLineText1 = "привлекательных растений",
        state = 2,
        onClick = onClick
    )
}
