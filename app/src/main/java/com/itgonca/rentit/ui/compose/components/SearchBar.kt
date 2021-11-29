package com.itgonca.rentit.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itgonca.rentit.R
import com.itgonca.rentit.ui.compose.state.EditableInputState
import com.itgonca.rentit.ui.compose.state.rememberEditableInputState
import com.itgonca.rentit.ui.compose.theme.Blue100
import com.itgonca.rentit.ui.compose.theme.Dark100
import com.itgonca.rentit.ui.compose.theme.Dark40
import com.itgonca.rentit.ui.compose.theme.LightGrey40

@Composable
fun SearchBarWithButton(
    state: EditableInputState = rememberEditableInputState(hint = "")
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(Blue100)

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = stringResource(id = R.string.content_description_icon_filter),
                    tint = Color.White
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(LightGrey40),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.content_description_search_bar),
                    tint = Dark40, modifier = Modifier.padding(start = 12.dp)
                )
                BasicTextField(
                    value = state.text,
                    onValueChange = {
                        if (state.isHint) state.text = "" else state.text = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, top = 14.dp, bottom = 14.dp),
                    textStyle = MaterialTheme.typography.caption.copy(color = if (state.isHint) Dark40 else Dark100)
                )
            }

        }
    }

}

@Preview(name = "SearchBarWithButtonStart", showBackground = true)
@Composable
fun SearchBarWithButtonStart() {
    SearchBarWithButton()
}