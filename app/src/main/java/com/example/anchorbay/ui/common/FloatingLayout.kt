package com.example.anchorbay.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max


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