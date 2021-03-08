package com.example.anchorbay.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
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
            LabelSelect()
        }
    }
}


@Composable
fun LabelSelect() {
    val labels = remember {
        mutableStateListOf(
            "Grillklippa",
            "Toa/dass",
            "Affär",
            "Resturang",
            "kilar",
            "kilar",
            "kilar",
            "kilar",
            "kilar",
            "kilar",
        )
    }

    Column(
        Modifier.fillMaxWidth()
    ) {
        for (label in labels) {
            Row(
                Modifier
                    .padding(start = 4.dp, bottom = 4.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Cyan)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    Modifier
                        .size(24.dp)
                        .background(Color.Red)
                )
                Spacer(Modifier.width(4.dp))
                Text(text = label)
            }
        }
    }
}

@Composable
@Preview(device = Devices.PIXEL_4_XL, showBackground = true, showSystemUi = true)
fun EditBayPreview() {
    EditBay()
}