package com.example.matule

import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
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
import com.example.matule.ui.theme.Poppins40016_D8D8D8
import com.example.matule.ui.theme.Raleway30065White
import com.example.matule.ui.theme.Raleway60014Black
import com.example.matule.ui.theme.Raleway60014White
import com.example.matule.ui.theme.Raleway70034_ECECEC
import com.example.matule.ui.theme.Raleway70065White
import com.example.matule.ui.theme.Raleway90030_ECECEC
import com.example.matule.ui.theme._0076B1
import com.example.matule.ui.theme._2B2B2B
import com.example.matule.ui.theme._2B6B8B
import com.example.matule.ui.theme._48B2E7
import com.example.matule.ui.theme._D8D8D8
import com.example.matule.ui.theme._ECECEC


val fontFamilyRaleway = FontFamily(Font(resId = R.font.raleway_regular))
val fontFamilyPoppins = FontFamily(Font(resId = R.font.poppins_regular))
val fontFamilyFutura = FontFamily(Font(R.font.futura_regular))
val fontFamilyWorkSans = FontFamily(Font(R.font.work_sans_regular))
val fontFamilyMasiva = FontFamily(Font(R.font.masiva))

@Composable
fun WearMe(nextPage: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                nextPage()
            },
        contentAlignment = Alignment.Center
    ) {
        Backgroundd()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text("Matule", style = Raleway70065White)
            }
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier.padding(bottom = 20.dp)
            ) {
                Text("Me", style = Raleway30065White)
            }
        }
    }
}

@Composable
fun OnBoard1(nextPage: () -> Unit) {
    Backgroundd()
    Image(
        painter = painterResource(R.drawable.three_light),
        contentDescription = null,
        modifier = Modifier
            .padding(start = 60.dp, top = 100.dp)
            .size(30.dp),
        contentScale = ContentScale.Crop
    )
    Image(
        painter = painterResource(R.drawable.wawe),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(top = 210.dp, start = 110.dp)
            .size(130.dp, 20.dp)
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.three_img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(300.dp, 500.dp)
        )
    }
    var h = LocalConfiguration.current.screenHeightDp / 10
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "Добро\nпожаловать", style = Raleway90030_ECECEC,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = h.dp)
        )
        Image(
            painter = painterResource(R.drawable.foot_img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
        )
        StatusSpacer(1)
        BotButton("Начать", true, nextPage)
    }
}

@Composable
fun OnBoard2(nextPage: () -> Unit) {
    Backgroundd()
    Image(
        painter = painterResource(R.drawable.wawe_1),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(top = 110.dp, start = 30.dp)
            .size(90.dp, 50.dp)
            .rotate(-42f)
            .alpha(0.8f)
    )
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.smile_img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 110.dp, end = 30.dp)
                .size(45.dp)
                .alpha(0.7f)
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Box(modifier = Modifier.fillMaxHeight(0.2f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight()
            ) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.on_board_2_boot),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(310.dp, 210.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.ellipse),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(220.dp, 20.dp)
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight(0.3f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Начнем\nпутешествие", style = Raleway70034_ECECEC,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Умная, великолепная и модная\nколлекция Изучите сейчас",
                        style = Poppins40016_D8D8D8,
                        textAlign = TextAlign.Center
                    )
                }
                StatusSpacer(2)
                BotButton("Далее", true, nextPage)
            }
        }
    }
}

@Composable
fun OnBoard3(nextPage: () -> Unit) {
    Backgroundd()
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.on_board_3_boot),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Text("У вас есть сила,\nчтобы", style = Raleway70034_ECECEC,
            textAlign = TextAlign.Center)
        Text("В вашей комнате много красивых\nи привлекательных растений",
            style = Poppins40016_D8D8D8, textAlign = TextAlign.Center)

        StatusSpacer(3)
        BotButton("Далее", true, nextPage)
    }
}

@Composable
fun StatusSpacer(i: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(
            Modifier
                .clip(RoundedCornerShape(5.dp))
                .size(
                    width = if (i == 1) {
                        42.dp
                    } else {
                        28.dp
                    }, 5.dp
                )
                .background(
                    color = if (i == 1) {
                        Color.White
                    } else {
                        _2B6B8B
                    }
                )
        )
        Spacer(
            Modifier
                .padding(horizontal = 10.dp)
                .clip(RoundedCornerShape(5.dp))
                .size(
                    width = if (i == 2) {
                        42.dp
                    } else {
                        28.dp
                    }, 5.dp
                )
                .background(
                    color = if (i == 2) {
                        Color.White
                    } else {
                        _2B6B8B
                    }
                )
        )
        Spacer(
            Modifier
                .clip(RoundedCornerShape(5.dp))
                .size(
                    width = if (i == 3) {
                        42.dp
                    } else {
                        28.dp
                    }, 5.dp
                )
                .background(
                    color = if (i == 3) {
                        Color.White
                    } else {
                        _2B6B8B
                    }
                )
        )
    }
}

@Composable
fun BotButton(text: String, whiteButton: Boolean, click: () -> Unit) {
    Button(
        onClick = {click()},
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp, end = 20.dp,
                bottom = if (whiteButton) {
                    35.dp
                } else {
                    0.dp
                }
            )
            .background(color = if (whiteButton) {Color.White} else { _48B2E7},
                shape = RoundedCornerShape(13.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (whiteButton) {Color.White} else { _48B2E7}
        )
    ) {
        Text(text = text, style = if (whiteButton) {Raleway60014Black} else { Raleway60014White})
    }
}

@Composable
private fun Backgroundd() {
    val brush = Brush.verticalGradient(listOf(_48B2E7, _0076B1))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = brush)
    )
}
