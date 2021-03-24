package com.example.anchorbay.ui.editbay

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.anchorbay.data.Bay

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun NewBay(onSubmit: (bay: Bay) -> Unit) {
    var bay by remember { mutableStateOf(Bay()) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onSubmit(bay) }) {
                Icon(Icons.Rounded.Done, contentDescription = null)
            }
        }
    ) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            EditBay(bay = bay, onBayChange = { bay = it })
            Spacer(Modifier.height(75.dp))
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable()
@Preview(device = Devices.PIXEL_4_XL, showBackground = true, showSystemUi = true)
fun NewBayPreview() {
    NewBay {}
}