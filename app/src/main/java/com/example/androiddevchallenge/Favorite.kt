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
package com.example.androiddevchallenge

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(
    id: String,
    modifier: Modifier = Modifier,
) {
    val (dogMap, setDogMap) = LocalDogMap.current
    val toggleFavorite = { ->
        dogMap.map { (key, value) ->
            key to if (value.id == id) {
                value.copy(favorite = !value.favorite)
            } else {
                value
            }
        }.toMap().let(setDogMap)
    }

    val dog = dogMap[id]!!
    Icon(
        imageVector = if (dog.favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
        contentDescription = null,
        tint = Color.Red,
        modifier = modifier.size(36.dp).clickable {
            toggleFavorite()
        }
    )
}
