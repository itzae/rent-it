package com.itgonca.rentit.ui.compose.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itgonca.rentit.data.remote.model.Location
import com.itgonca.rentit.ui.compose.components.FilterChip
import com.itgonca.rentit.ui.compose.components.LodgingItem
import com.itgonca.rentit.ui.compose.components.SearchBarWithButton
import com.itgonca.rentit.ui.compose.state.rememberEditableInputState
import com.itgonca.rentit.ui.compose.theme.RentItTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter

@Composable
fun HomeScreen(
    listFilters: List<String>,
    listLocations: List<Location>,
    onQueryChange: (String) -> Unit
) {
    val searchBarState = rememberEditableInputState(hint = "Search via City")

    val currentOnQueryChanged by rememberUpdatedState(onQueryChange)
    LaunchedEffect(searchBarState) {
        snapshotFlow { searchBarState.text }
            .filter { !searchBarState.isHint }
            .collect {
                currentOnQueryChanged(searchBarState.text)
            }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBarWithButton(searchBarState)
        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), content = {
            items(listFilters) {
                FilterChip(
                    filter = it,
                    onExecuteChange = {
                    })
            }
        })
        LazyColumn(modifier = Modifier.padding(8.dp), content = {
            items(listLocations) {
                LodgingItem(it)
            }
        })
    }
}

@Preview(name = "HomeScreen", device = Devices.PIXEL_3A, showBackground = true)
@Composable
fun HomeScreenPreview() {
    RentItTheme {
        HomeScreen(
            listOf("Filtro 1", "Filtro 2", "Filtro 3", "Filtro 4"),
            listOf(Location(id = 1, name = "Example Location", price = 150.0))
        ) {}
    }
}

