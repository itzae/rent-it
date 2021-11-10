package com.itgonca.rentit.ui.compose.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.itgonca.rentit.R

private val SfproDisplay = FontFamily(
    Font(R.font.sf_pro_display_regular, FontWeight.Normal),
    Font(R.font.sf_pro_display_medium, FontWeight.Medium),
    Font(R.font.sf_pro_display_black, FontWeight.Black),
    Font(R.font.sf_pro_display_bold, FontWeight.Bold)
)

val RentItTypography = Typography(
    h1 = TextStyle(
        fontFamily = SfproDisplay,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp,
        color = Dark100
    ),
    h2 = TextStyle(
        fontFamily = SfproDisplay,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 30.sp,
        color = Dark100
    ),
    h3 = TextStyle(
        fontFamily = SfproDisplay,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 22.sp,
        color = Dark100
    ),
    h4 = TextStyle(
        fontFamily = SfproDisplay,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 20.sp,
        color = Dark100
    ),
    body1 = TextStyle(
        fontFamily = SfproDisplay,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight =22.sp,
        color = Dark100
    ),
    body2 = TextStyle(
        fontFamily = SfproDisplay,
        fontSize =12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight =20.sp,
        color = Dark100
    ),
    //Display
    caption = TextStyle(
        fontFamily = SfproDisplay,
        fontSize =14.sp,
        fontWeight = FontWeight.Medium,
        lineHeight =20.sp,
        color = Dark100
    ),
    subtitle1 = TextStyle(
        fontFamily = SfproDisplay,
        fontSize =12.sp,
        fontWeight = FontWeight.Bold,
        lineHeight =20.sp,
        color = Dark100
    )
)