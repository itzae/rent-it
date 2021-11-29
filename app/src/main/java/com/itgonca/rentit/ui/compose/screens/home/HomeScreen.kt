package com.itgonca.rentit.ui.compose.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itgonca.rentit.R
import com.itgonca.rentit.data.remote.model.Location
import com.itgonca.rentit.ui.compose.components.LodgingItem
import com.itgonca.rentit.ui.compose.components.LodgingRowItem
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
    val searchBarState =
        rememberEditableInputState(hint = stringResource(id = R.string.search_city_label))

    val currentOnQueryChanged by rememberUpdatedState(onQueryChange)
    LaunchedEffect(searchBarState) {
        snapshotFlow { searchBarState.text }
            .filter { !searchBarState.isHint }
            .collect {
                currentOnQueryChanged(searchBarState.text)
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.White, elevation = 0.dp,modifier = Modifier.height(120.dp)) {
                SearchBarWithButton(searchBarState, listFilters = listFilters)
            }
        },
        content = {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(listLocations) {
                    LodgingItem(it)
                }
                item {
                    Text(
                        text = stringResource(id = R.string.footer_label),
                        style = MaterialTheme.typography.h3
                    )
                }
                item {
                    LazyRow(content = {
                        items(4) {
                            LodgingRowItem(location = Location(1, "Example offers"))
                        }
                    })
                }
            }
        })


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

