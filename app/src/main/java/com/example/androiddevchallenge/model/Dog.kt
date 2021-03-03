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
package com.example.androiddevchallenge.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.androiddevchallenge.R

enum class Sex {
    FEMALE, MALE;

    fun icon(): ImageVector {
        return when (this) {
            MALE -> Icons.Filled.Male
            FEMALE -> Icons.Filled.Female
        }
    }
}

data class DogModel(
    val id: String,
    val name: String,
    val sex: Sex,
    val age: Int, // month
    val image: Int,
    val place: String = "USA",
    val description: String = LoremIpsum(70).values.toList().joinToString(" ").replace("\n", ""),
    val favorite: Boolean = false,
) {
    override fun toString(): String {
        return "$id: $name $age month $place $description $favorite "
    }
}

val dogList = listOf(
    DogModel(
        id = "1",
        name = "Abby",
        sex = Sex.FEMALE,
        age = 10,
        image = R.drawable.dog1,
    ),
    DogModel(
        id = "2",
        name = "Bruno",
        sex = Sex.FEMALE,
        age = 20,
        image = R.drawable.dog2,
        place = "Japan",
    ),
    DogModel(
        id = "3",
        name = "Hunter",
        sex = Sex.FEMALE,
        age = 30,
        image = R.drawable.dog3,
    ),
    DogModel(
        id = "4",
        name = "Otis",
        sex = Sex.FEMALE,
        age = 40,
        image = R.drawable.dog4,
    ),
    DogModel(
        id = "5",
        name = "Roscoe",
        sex = Sex.FEMALE,
        age = 50,
        image = R.drawable.dog5,
    ),
    DogModel(
        id = "6",
        name = "Abby",
        sex = Sex.FEMALE,
        age = 60,
        image = R.drawable.dog6,
        place = "Japan",
    ),
    DogModel(
        id = "7",
        name = "Jasper",
        sex = Sex.FEMALE,
        age = 70,
        image = R.drawable.dog7,
        place = "Japan",
    ),
    DogModel(
        id = "8",
        name = "Samson",
        sex = Sex.FEMALE,
        age = 80,
        image = R.drawable.dog8,
        place = "Japan",
    ),
    DogModel(
        id = "9",
        name = "Abby",
        sex = Sex.FEMALE,
        age = 90,
        image = R.drawable.dog9,
        place = "Japan",
    ),
    DogModel(
        id = "10",
        name = "Oliver",
        sex = Sex.FEMALE,
        age = 90,
        image = R.drawable.dog10,
        place = "Japan",
    ),
    DogModel(
        id = "10",
        name = "Copper",
        sex = Sex.FEMALE,
        age = 90,
        image = R.drawable.dog11,
    ),
)

val dogMap = dogList.map { it.id to it }.toMap()
