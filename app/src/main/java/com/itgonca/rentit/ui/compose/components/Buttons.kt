package com.itgonca.rentit.ui.compose.components

import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FilledButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(modifier = Modifier, onClick = { /*TODO*/ }) {
        content()
    }
}