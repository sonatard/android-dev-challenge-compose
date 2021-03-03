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

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.model.DogModel
import com.example.androiddevchallenge.model.dogMap

enum class NavItem(
    @StringRes val text: Int,
) {
    DogList(text = R.string.dog_list),
    DogDetail(text = R.string.dog_detail),
}

val LocalNav = staticCompositionLocalOf<NavHostController> {
    error("No NavHostController provided")
}

val LocalDogMap = compositionLocalOf<MutableState<Map<String, DogModel>>> {
    error("No DogList provided")
}

@Composable
fun DogApp(
    initNavItem: NavItem = NavItem.DogList,
) {
    var dogMapState = remember { mutableStateOf(dogMap) }
    val navController = rememberNavController()
    Box {
        CompositionLocalProvider(LocalDogMap provides dogMapState) {
            CompositionLocalProvider(LocalNav provides navController) {
                NavHost(navController = LocalNav.current, startDestination = initNavItem.name) {
                    // Bottom Navigation
                    composable(route = NavItem.DogList.name) {
                        DogList()
                    }
                    composable(route = NavItem.DogDetail.name + "/{dogId}") { backStackEntry ->
                        DogDetail(backStackEntry.arguments?.getString("dogId").toString())
                    }
                }
            }
        }
    }
}
