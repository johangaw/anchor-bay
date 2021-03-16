package com.example.anchorbay.ui.editbay

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.anchorbay.R
import com.example.anchorbay.data.Boat
import com.example.anchorbay.data.availableBoats

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun BoatSelect(
    selected: Boat?,
    onSelectedChange: (boat: Boat) -> Unit,
    availableBoats: List<Boat>
) {
    var expanded by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(targetValue = if (expanded) 0f else -90f)
    Column(Modifier.animateContentSize()) {
        ListItem(
            Modifier.clickable { expanded = !expanded },
            icon = {
                Icon(
                    painterResource(id = R.drawable.sailboat),
                    contentDescription = null,
                    tint = if(selected == null) Color.Gray else Color.Black
                )
            },
            text = { Text(selected?.name ?: "") },
            trailing = {
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    Modifier
                        .size(40.dp)
                        .rotate(rotation)
                )
            }
        )
        Divider()
        AnimatedVisibility(visible = expanded) {
            Column {
                for (boat in availableBoats) {
                    ListItem(
                        Modifier.clickable {
                            onSelectedChange(boat)
                            expanded = false
                        },
                        icon = {
                            Icon(
                                painterResource(id = R.drawable.sailboat),
                                contentDescription = null,
                                tint = Color.Black
                            )
                        },
                        text = { Text(boat.name) },
                    )
                }
                Divider()
            }
        }
    }
}


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
@Preview(device = Devices.PIXEL_4_XL, showBackground = true, showSystemUi = true)
fun BoatSelectPreview() {
    var boat by remember { mutableStateOf<Boat?>(null) }
    Column {
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .background(Color.Gray)
        )
        BoatSelect(boat, { boat = it }, availableBoats)
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .background(Color.Gray)
        )
    }
}