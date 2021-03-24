package com.example.anchorbay.ui.baylist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.anchorbay.R
import com.example.anchorbay.data.Bay

@ExperimentalMaterialApi
@Composable()
fun BayList(bays: List<Bay>, onEditBay: (bay: Bay) -> Unit, onNewBay: () -> Unit) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNewBay) {
                Icon(Icons.Rounded.Add, contentDescription = null)
            }
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 75.dp)
        ) {
            items(bays) { bay ->
                ListItem(
                    text = { Text(bay.nickname) },
                    icon = {
                        Image(
                            painter = painterResource(id = R.drawable.hero),
                            contentDescription = null
                        )
                    },
                    secondaryText = {
                        Rating(bay.rating)
                    },
                    modifier = Modifier
                        .clickable { onEditBay(bay) }
                        .padding(bottom = 8.dp)
                )
                Divider()
            }
        }
    }
}


@Composable()
fun Rating(rating: Int) {
    Row() {
        repeat(rating.coerceAtMost(5)) {
            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(R.drawable.star_filled),
                colorFilter = ColorFilter.tint(color = Color.Yellow),
                contentDescription = null
            )
        }
    }
}


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
@Preview(device = Devices.PIXEL_4_XL, showBackground = true, showSystemUi = true)
fun BayListPreview() {
    val bays: List<Bay> = listOf(
        Bay(nickname = "Kråkholmen", rating = 4),
        Bay(nickname = "Klubbholmen", rating = 2),
        Bay(nickname = "Badviken", rating = 5),
    )
    BayList(bays, {}, {})
}