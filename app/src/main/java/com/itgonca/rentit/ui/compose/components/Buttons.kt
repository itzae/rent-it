package com.itgonca.rentit.ui.compose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.itgonca.rentit.ui.compose.theme.Grey100
import com.itgonca.rentit.ui.compose.theme.LightGrey40

@Composable
fun FilterChip(filter: String, onExecuteChange: (String) -> Unit) {
    var isSelectedChip by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier.padding(end = 8.dp),
        shape = MaterialTheme.shapes.medium,
        color = if (isSelectedChip) MaterialTheme.colors.primary else LightGrey40
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    isSelectedChip = !isSelectedChip
                    onExecuteChange(filter)
                }
        ) {
            Text(
                text = filter,
                style = MaterialTheme.typography.caption.copy(color = if (isSelectedChip) Color.White else Grey100),
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
        }
    }
}