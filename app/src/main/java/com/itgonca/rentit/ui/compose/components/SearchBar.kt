package com.itgonca.rentit.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.itgonca.rentit.ui.compose.theme.*


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    state: EditableInputState = rememberEditableInputState(hint = "")
) {
    Surface(
        modifier = modifier
    ) {
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


@Composable
fun SearchBarWithButton(
    state: EditableInputState = rememberEditableInputState(hint = ""),
    listFilters: List<String>,
    isFilterBarShow: Boolean = true
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
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
                SearchBar(state = state)
            }
            if(isFilterBarShow)
                SearchFilters(
                    listFilters = listFilters, modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
        }
    }

}

@Composable
fun SearchFilters(modifier: Modifier = Modifier, listFilters: List<String>) {
    LazyRow(modifier = modifier, content = {
        items(listFilters) {
            FilterChip(
                filter = it,
                onExecuteChange = {
                })
        }
    })
}

@Preview(name = "SearchBarWithButtonStart", showBackground = true)
@Composable
fun SearchBarWithButtonStart() {
    RentItTheme {
        SearchBarWithButton(
            listFilters = listOf(
                "Filtro 1",
                "Filtro 2",
                "Filtro 3",
                "Filtro 4"
            )
        )
    }
}

@Preview(name = "SearchBar", showBackground = true)
@Composable
fun SearchBarPreview() {
    RentItTheme { SearchBar() }
}

@Preview(name = "ChipFilters", showBackground = true)
@Composable
fun FiltersChip() {
    RentItTheme {
        SearchFilters(listFilters = listOf("Filtro 1", "Filtro 2", "Filtro 3", "Filtro 4"))
    }
}