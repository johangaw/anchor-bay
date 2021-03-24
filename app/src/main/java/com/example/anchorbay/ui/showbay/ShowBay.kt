package com.example.anchorbay.ui.showbay

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.anchorbay.data.Bay
import com.example.anchorbay.ui.editbay.EditBay


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun ShowBay(bay: Bay) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        EditBay(bay = bay, onBayChange = {})
    }
}