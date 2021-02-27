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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MySurface
import com.example.androiddevchallenge.ui.theme.MyTheme

// Start building your app here!
@Composable
fun DogDetail(
    id: String,
) {
    val (dogList, _) = LocalDogMap.current
    val dog = dogList[id]!!

    MySurface(color = MaterialTheme.colors.background) {
        LazyColumn {
            item {

                Box(
                    contentAlignment = Alignment.BottomEnd,
                ) {
                    Image(
                        painter = painterResource(dog.image),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().aspectRatio(1F)
                    )
                    FavoriteButton(id, Modifier.padding(16.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.1F).background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.White)
                            )
                        )
                    )
                }
            }
            item {
                Column(
                    Modifier.background(Color.Black.copy(alpha = 0.9F)).padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = dog.name,
                            style = MaterialTheme.typography.h4,
                            color = Color.White.copy(alpha = 0.8F)
                        )
                        Spacer(Modifier.weight(1F))
                        Text(
                            "${dog.age} month",
                            color = Color.White.copy(alpha = 0.8F)
                        )
                        Icon(
                            dog.sex.icon(), "",
                            tint = Color.White.copy(alpha = 0.8F)
                        )
                        Text(
                            dog.place,
                            color = Color.White.copy(alpha = 0.8F)
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                    Text(
                        dog.description,
                        color = Color.White.copy(alpha = 0.8F)
                    )
                }
            }
            item {
                RecoomendDogsList(dog.id)
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun DogLightPreview() {
    MyTheme {
        DogDetail("1")
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DogDarkPreview() {
    MyTheme(darkTheme = true) {
        DogDetail("1")
    }
}
