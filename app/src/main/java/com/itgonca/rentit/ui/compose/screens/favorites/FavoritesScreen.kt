package com.itgonca.rentit.ui.compose.screens.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.itgonca.rentit.R
import com.itgonca.rentit.data.remote.model.Location
import com.itgonca.rentit.ui.compose.components.LodgingItem

@Composable
fun FavoritesScreen(modifier: Modifier = Modifier, listLocations: List<Location>) {
    LazyColumn(content = {
        item {
            Text(
                text = stringResource(id = R.string.favorites_title_label),
                modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                style = MaterialTheme.typography.h3
            )
        }
        items(listLocations) {
            LodgingItem(it) {

            }
        }
    }, modifier = modifier.fillMaxSize())
}