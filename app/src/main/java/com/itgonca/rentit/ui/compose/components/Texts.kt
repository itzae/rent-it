package com.itgonca.rentit.ui.compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LabelHelper(
    modifier: Modifier = Modifier,
    message: String = "",
    colorMessage: Color = MaterialTheme.colors.onSurface,
    isHelperShow: Boolean = false,
    content: @Composable () -> Unit
) {
    Column {
        content()
        if(isHelperShow)
            Text(
                text = message,
                color = colorMessage,
                style = MaterialTheme.typography.caption,
                modifier = modifier
            )
    }
}