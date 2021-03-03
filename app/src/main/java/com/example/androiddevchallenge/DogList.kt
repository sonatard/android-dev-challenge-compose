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

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.ui.theme.MySurface
import com.example.androiddevchallenge.ui.theme.MyTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DogList() {
    val navController = LocalNav.current
    val (dogList, _) = LocalDogMap.current
    MySurface(color = MaterialTheme.colors.background) {
        LazyColumn {
            items(dogList.map { (_, v) -> v }) { dog ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd,
                ) {
                    Image(
                        painter = painterResource(id = dog.image),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().clickable {
                            navController.navigate(NavItem.DogDetail.name + "/" + dog.id)
                        }
                    )
                    Column(horizontalAlignment = Alignment.End) {
                        FavoriteButton(dog.id, Modifier.padding(16.dp))
                        Spacer(Modifier.weight(1F))
                        Row(
                            modifier = Modifier.fillMaxWidth().height(50.dp)
                                .background(Color.Black.copy(0.5F)).padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Icon(
                                imageVector = Icons.Filled.Pets,
                                contentDescription = "",
                                tint = MaterialTheme.colors.onPrimary,
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(dog.name, color = MaterialTheme.colors.onPrimary)
                            Spacer(Modifier.weight(1F))
                            Text("${dog.age} month", color = MaterialTheme.colors.onPrimary)
                            Spacer(Modifier.width(4.dp))
                            Icon(
                                dog.sex.icon(),
                                contentDescription = "",
                                tint = MaterialTheme.colors.onPrimary
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(dog.place, color = MaterialTheme.colors.onPrimary)
                        }
                    }
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun DogListLightPreview() {
    MyTheme {
        DogList()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DogListDarkPreview() {
    MyTheme(darkTheme = true) {
        DogList()
    }
}
