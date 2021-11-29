package com.itgonca.rentit.ui.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itgonca.rentit.R
import com.itgonca.rentit.data.remote.model.Location
import com.itgonca.rentit.ui.compose.theme.Blue100
import com.itgonca.rentit.ui.compose.theme.Grey100
import com.itgonca.rentit.ui.compose.theme.Red100
import com.itgonca.rentit.ui.compose.theme.RentItTheme

@Composable
fun LodgingItem(location: Location) {
    var isFavoriteLocation by remember {
        mutableStateOf(location.favorite)
    }
    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.image_lodging),
                contentDescription = stringResource(id = R.string.content_description_image_location),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp)),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = "2.3 miles",
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Red100)
                    .padding(start = 16.dp, end = 16.dp),
                style = MaterialTheme.typography.caption.copy(color = Color.White)
            )
            IconButton(
                onClick = { isFavoriteLocation = !isFavoriteLocation },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Image(
                    painter = painterResource(id = if (isFavoriteLocation) R.drawable.ic_favorite_fill else R.drawable.ic_favorite_regular),
                    contentDescription = null
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = location.name, style = MaterialTheme.typography.h3)
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle()) {
                        withStyle(style = SpanStyle(Blue100)) {
                            append(location.price.toString())
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Grey100,
                                fontWeight = FontWeight.Normal
                            )
                        ) {
                            append("/per day")
                        }
                    }
                }, style = MaterialTheme.typography.h4
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_gray_location),
                contentDescription = "",
                tint = Grey100,
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(8.dp, 9.dp)
            )
            Text(text = "Los Angeles", style = MaterialTheme.typography.body1.copy(color = Grey100))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LodgingItemCard() {
    RentItTheme {
        LodgingItem(Location(1, "Suny apartment", 233.0, "", 12))
    }
}