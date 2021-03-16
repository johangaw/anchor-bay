package com.example.anchorbay.ui.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
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