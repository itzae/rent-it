package com.itgonca.rentit.ui.compose.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itgonca.rentit.R
import com.itgonca.rentit.data.remote.model.Location
import com.itgonca.rentit.ui.compose.components.LodgingItem
import com.itgonca.rentit.ui.compose.components.LodgingRowItem
import com.itgonca.rentit.ui.compose.theme.RentItTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier, listLocations: List<Location>) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(listLocations) {
            LodgingItem(it) {

            }
        }
        item {
            Text(
                text = stringResource(id = R.string.footer_label),
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(start = 16.dp)
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


}

@Preview(name = "HomeScreen", device = Devices.PIXEL_3A, showBackground = true)
@Composable
fun HomeScreenPreview() {
    RentItTheme {
        HomeScreen(
            modifier = Modifier,
            listOf(Location(id = 1, name = "Example Location", price = 150.0))
        )
    }
}

