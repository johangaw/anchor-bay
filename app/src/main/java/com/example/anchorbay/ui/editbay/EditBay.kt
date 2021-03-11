package com.example.anchorbay.ui.editbay

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
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
fun EditBay() {
    var rating by remember { mutableStateOf(0) }

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
            OutlinedTextField(value = "", onValueChange = { }, modifier = Modifier.fillMaxWidth())
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                IconButton(onClick = {}) {
                    Icon(Icons.Rounded.LocationOn, contentDescription = "")
                }
                IconButton(onClick = {}) {
                    Text(text = "NW", style = MaterialTheme.typography.h5)
                }
            }
            LabelSelect()
            BoatSelect()

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

@Composable
fun BoatSelect() {
    Column(
        Modifier.padding(8.dp)
    ) {
        Button(onClick = {}) {
            Text(text = "Boat 1")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {}) {
            Text(text = "Boat 2")
        }
    }
}


@Composable
@Preview(device = Devices.PIXEL_4_XL, showBackground = true, showSystemUi = true)
fun EditBayPreview() {
    EditBay()
}