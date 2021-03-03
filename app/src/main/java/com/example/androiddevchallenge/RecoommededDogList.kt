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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.layout.VerticalGrid
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun RecoomendDogsList(id: String) {
    val navController = LocalNav.current
    val (dogMap, _) = LocalDogMap.current
    val targetDog = dogMap[id]!!
    val recommendedDogList =
        dogMap.map { (_, v) -> v }.filter { it.id != targetDog.id }
            .filter { v -> v.place == targetDog.place }
    Column(
        Modifier.background(Color.Black.copy(alpha = 0.9F)).padding(16.dp)
    ) {
        Text(
            "Neighborhood",
            style = MaterialTheme.typography.h5,
            color = Color.White.copy(alpha = 0.8F)
        )
        Spacer(Modifier.height(16.dp))
        Box() {
            VerticalGrid(gap = 16.dp) {
                recommendedDogList.forEach { dog ->
                    Box(
                        contentAlignment = Alignment.BottomStart,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = dog.image),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth().aspectRatio(1F).clickable {
                                navController.navigate(NavItem.DogDetail.name + "/" + dog.id)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun DogRecommendListLightPreview() {
    MyTheme {
        DogList()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DogRecommendListDarkPreview() {
    MyTheme(darkTheme = true) {
        DogList()
    }
}
