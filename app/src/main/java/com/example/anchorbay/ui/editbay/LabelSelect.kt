package com.example.anchorbay.ui.editbay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max

val labels = listOf(
    "Grillklippa",
    "Toa/dass",
    "Affär",
    "Resturang asd sad as das d asd as das d asd d as ",
    "kilar",
    "kilar",
    "kilar",
    "kilar",
    "kilar",
    "kilar",
)

@Composable
fun LabelSelect() {
    Card(
        Modifier
            .padding(8.dp)
            .fillMaxWidth(), elevation = 4.dp
    ) {
        FloatingLayout(
            Modifier.fillMaxWidth().padding(8.dp),
            horizontalPadding = 4.dp,
            verticalPadding = 4.dp
        ) {
            for (label in labels) {
                Row(
                    Modifier
                        .wrapContentSize()
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
}

@Composable
fun FloatingLayout(
    modifier: Modifier = Modifier,
    verticalPadding: Dp = 0.dp,
    horizontalPadding: Dp = 0.dp,
    content: @Composable() () -> Unit,
) {
    val vPaddingPx = with(LocalDensity.current) { verticalPadding.roundToPx() }
    val hPaddingPx = with(LocalDensity.current) { horizontalPadding.roundToPx() }

    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables =
            measurables.map { m -> m.measure(constraints.copy(minHeight = 0, minWidth = 0)) }

        val lines = placeables
            .fold(listOf(listOf<Placeable>())) { acc, current ->
                val currentRow = acc.last()
                val otherRows = acc.dropLast(1)
                val rowLength = currentRow.sumBy { it.width }

                val totalLinePadding = currentRow.size * vPaddingPx
                if (rowLength + current.width + totalLinePadding <= constraints.maxWidth) {
                    otherRows + listOf(currentRow + current)
                } else {
                    otherRows + listOf(currentRow) + listOf(listOf(current))
                }
            }

        val height =
            lines.sumBy { line -> line.maxOf { it.height } } + (lines.size - 1) * hPaddingPx

        layout(constraints.maxWidth, height) {
            var y = 0
            lines.forEach { line ->
                var x = 0
                var varLineMaxWidth = 0
                line.forEach {
                    it.place(x, y)
                    x += (it.width + vPaddingPx)
                    varLineMaxWidth = max(varLineMaxWidth, it.height)
                }
                y += varLineMaxWidth + hPaddingPx
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LabelSelectPreview() {
    Column() {
        Spacer(modifier = Modifier.height(16.dp))
        LabelSelect()
        Spacer(modifier = Modifier.height(16.dp))

    }
}