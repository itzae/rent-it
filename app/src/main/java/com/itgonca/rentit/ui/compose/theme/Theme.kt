package com.itgonca.rentit.ui.compose.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun RentItTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = RentItLightColors,
        typography = RentItTypography,
        content = content
    )
}