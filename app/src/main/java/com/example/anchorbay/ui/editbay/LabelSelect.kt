package com.example.anchorbay.ui.editbay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



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

    Row(
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

@Preview
@Composable
fun LabelSelectPreview() {
    LabelSelect()
}