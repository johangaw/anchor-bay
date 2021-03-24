package com.example.anchorbay.ui.editbay

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.anchorbay.R
import com.example.anchorbay.data.*
import com.example.anchorbay.ui.common.RatingBar

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
fun EditBay(bay: Bay, onBayChange: (bay: Bay) -> Unit) {
    Column {
        Image(
            painter = painterResource(R.drawable.hero),
            contentScale = ContentScale.FillHeight,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
        Column(Modifier.padding(16.dp)) {
            RatingBar(bay.rating) { onBayChange(bay.copy(rating = it)) }
            Nickname(value = bay.nickname, onValueChange = { onBayChange(bay.copy(nickname = it ))})
            Spacer(modifier = Modifier.height(16.dp))
            LocationAndDirection(bay.localisation, { onBayChange(bay.copy(localisation = it)) })
            Spacer(modifier = Modifier.height(16.dp))
            LabelSelect(bay.labels, { onBayChange(bay.copy(labels = it)) })
            BoatSelect(bay.boat, { onBayChange(bay.copy(boat = it)) }, availableBoats)
            TextField(
                value = bay.comments,
                onValueChange = { onBayChange(bay.copy(comments = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 200.dp),
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
    var bay by remember { mutableStateOf(Bay())}
    EditBay(bay, {bay = it})
}