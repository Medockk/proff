package com.example.matule.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.matule.fontFamilyMasiva
import com.example.matule.fontFamilyRaleway

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
val Raleway70020White = TextStyle(
    fontFamily = fontFamilyRaleway,
    fontWeight = FontWeight(700),
    fontSize = 20.sp,
    color = Color.White
)
val Raleway50016White = TextStyle(
    fontFamily = fontFamilyRaleway,
    fontWeight = FontWeight(500),
    fontSize = 16.sp,
    color = Color.White
)
val Raleway70015_48B2E7 = TextStyle(
    fontFamily = fontFamilyRaleway,
    fontWeight = FontWeight(700),
    fontSize = 15.sp,
    color = _48B2E7
)
val Raleway60012_48B2E7 = TextStyle(
    fontFamily = fontFamilyRaleway,
    fontWeight = FontWeight(600),
    fontSize = 12.sp,
    color = _48B2E7
)
val Raleway60020_2B2B2B = TextStyle(
    fontFamily = fontFamilyRaleway,
    fontWeight = FontWeight(600),
    fontSize = 20.sp,
    color = _2B2B2B
)
val Raleway60016_2B2B2B = TextStyle(
    fontFamily = fontFamilyRaleway,
    fontWeight = FontWeight(600),
    fontSize = 16.sp,
    color = _2B2B2B
)
val Masiva40016_2B2B2B = TextStyle(
    fontFamily = fontFamilyMasiva,
    fontWeight = FontWeight(400),
    fontSize = 16.sp,
    color = _2B2B2B
)
val Masiva40012_2B2B2B = TextStyle(
    fontFamily = fontFamilyMasiva,
    fontWeight = FontWeight(400),
    fontSize = 12.sp,
    color = _2B2B2B
)
val Masiva40012_707B81 = TextStyle(
    fontFamily = fontFamilyMasiva,
    fontWeight = FontWeight(400),
    fontSize = 12.sp,
    color = _707B81
)