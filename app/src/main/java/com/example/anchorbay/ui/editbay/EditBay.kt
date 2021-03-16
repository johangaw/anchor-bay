package com.example.anchorbay.ui.editbay

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.anchorbay.R
import com.example.anchorbay.data.Boat
import com.example.anchorbay.data.Label
import com.example.anchorbay.data.Localisation
import com.example.anchorbay.data.availableBoats

@Composable
fun RatingBar(value: Int, onValueChange: (value: Int) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        repeat(5) {
            val selected = it < value
            val color by animateColorAsState(targetValue = if (selected) Color.Yellow else Color.Black)
            Image(
                modifier = Modifier
                    .clickable { onValueChange(it + 1) }
                    .size(50.dp),
                painter = painterResource(if (selected) R.drawable.star_filled else R.drawable.start_unfilled),
                colorFilter = ColorFilter.tint(color = color),
                contentDescription = null
            )
        }
    }
}

@Composable
fun Nickname(value: String, onValueChange: (value: String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Smeknamn") })
}


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun EditBay() {
    var rating by remember { mutableStateOf(0) }
    var nickname by remember { mutableStateOf("") }
    var labels by remember { mutableStateOf(listOf<Label>()) }
    var localisation by remember { mutableStateOf(Localisation("", null)) }
    var boat by remember { mutableStateOf<Boat?>(null)}

    Column(Modifier.verticalScroll(rememberScrollState())) {
        Image(
            painter = painterResource(R.drawable.hero),
            contentScale = ContentScale.FillHeight,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
        Column(Modifier.padding(16.dp)) {
            RatingBar(rating) { rating = it }
            Nickname(value = nickname, onValueChange = { nickname = it })
            Spacer(modifier = Modifier.height(16.dp))
            LocationAndDirection(localisation, { localisation = it })
            Spacer(modifier = Modifier.height(16.dp))
            LabelSelect(labels, { labels = it })
            BoatSelect(boat, {boat = it}, availableBoats)

            var str by remember { mutableStateOf("") }
            TextField(
                value = str,
                onValueChange = { str = it },
                Modifier.fillMaxWidth(),
                singleLine = false,
            )
        }
    }
}


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
@Preview(device = Devices.PIXEL_4_XL, showBackground = true, showSystemUi = true)
fun EditBayPreview() {
    EditBay()
}