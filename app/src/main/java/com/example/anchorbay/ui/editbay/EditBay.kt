package com.example.anchorbay.ui

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.anchorbay.R
import com.example.anchorbay.ui.editbay.LabelSelect

@Composable
fun RatingBar() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        repeat(5) {
            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(R.drawable.ic_baseline_star_outline_24),
                colorFilter = ColorFilter.tint(color = Color.Black),
                contentDescription = null
            )
        }
    }
}


@Composable
fun EditBay() {
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
            RatingBar()
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

            var str by remember { mutableStateOf("")}
            TextField(value = str, onValueChange = { str = it }, Modifier.fillMaxWidth(), singleLine = false,)
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