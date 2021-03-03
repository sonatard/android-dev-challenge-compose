/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.layout

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

// Arrangement.Vertical and Alignment.Horizontal not supported
@Composable
fun VerticalGrid(
    modifier: Modifier = Modifier,
    columns: Int = 2,
    gap: Dp = 0.dp,
    xGap: Dp = 0.dp,
    yGap: Dp = 0.dp,
    content: @Composable () -> Unit,
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->

        // parameters
        var xgap = xGap.roundToPx()
        if (xgap == 0) {
            xgap = gap.roundToPx()
        }
        var ygap = yGap.roundToPx()
        if (ygap == 0) {
            ygap = gap.roundToPx()
        }

        // calculate available single cell width from parent width, columns and xgap
        val cellWidth = cellWidth(constraints.maxWidth, columns, xgap)

        // remeasure by new itemConstraints
        // update width and height
        val cellConstraints = constraints.copy(
            minWidth = 0,
            maxWidth = cellWidth,
        )
        val placeables = measurables.map { it.measure(cellConstraints) }

        // Align cell height in the same row
        val cellHeights = cellHeights(placeables.map { it.height }, columns)

        // calculate positions
        val sizes = placeables.mapIndexed { i, _ ->
            val row = i / columns
            IntSize(cellWidth, cellHeights[row])
        }
        val positions =
            positions(columns, xgap, ygap, sizes)

        // place items in cell
        layout(
            width = constraints.maxWidth,
            height = positions.last().y + cellHeights.last()
        ) {
            placeables.forEachIndexed { index, placeable ->
                // Placeable does not always place the object at the specified position.
                // If the constraints.minWidth is 10 and next item postion x is 5, the actual placement will be 10.
                placeable.place(positions[index])
            }
        }
    }
}

private fun cellWidth(maxWidth: Int, columns: Int, xgap: Int): Int {
    val totalXGap = (columns - 1) * xgap
    return (maxWidth - totalXGap) / columns
}

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
internal fun cellHeights(heights: List<Int>, columns: Int): List<Int> {
    return heights.chunked(columns).map { it.maxOrNull()!! }
}

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
internal fun rows(size: Int, columns: Int): Int {
    if (size == 0) {
        return 0
    }

    return (size - 1) / columns + 1
}

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
internal fun positions(
    columns: Int,
    xgap: Int,
    ygap: Int,
    sizes: List<IntSize>
): Array<IntOffset> {
    val positions = Array(sizes.size) { IntOffset(0, 0) }
    val cellX = Array(rows(sizes.size, columns)) { 0 }
    val cellY = Array(columns) { 0 }
    sizes.forEachIndexed { index, size ->
        val row = index / columns
        val column = index % columns

        positions[index] = IntOffset(x = cellX[row], y = cellY[column])

        cellX[row] += size.width + xgap
        cellY[column] += size.height + ygap
    }

    return positions
}
