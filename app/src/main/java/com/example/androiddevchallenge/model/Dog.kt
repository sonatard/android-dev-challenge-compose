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

enum class DogColor {
    BROWN, BLACK, WHITE;
}


data class DogModel(
    val id: String,
    val name: String,
    val sex: Sex,
    val color: DogColor = DogColor.BLACK,
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
        color = DogColor.BROWN,
        age = 10,
        image = R.drawable.dog1,
    ),
    DogModel(
        id = "2",
        name = "Bruno",
        sex = Sex.FEMALE,
        color = DogColor.BROWN,
        age = 20,
        image = R.drawable.dog2,
        place = "Japan",
    ),
    DogModel(
        id = "3",
        name = "Hunter",
        sex = Sex.FEMALE,
        color = DogColor.BROWN,
        age = 30,
        image = R.drawable.dog3,
    ),
    DogModel(
        id = "4",
        name = "Otis",
        sex = Sex.FEMALE,
        color = DogColor.BROWN,
        age = 40,
        image = R.drawable.dog4,
    ),
    DogModel(
        id = "5",
        name = "Roscoe",
        sex = Sex.FEMALE,
        color = DogColor.BROWN,
        age = 50,
        image = R.drawable.dog5,
    ),
    DogModel(
        id = "6",
        name = "Abby",
        sex = Sex.FEMALE,
        color = DogColor.BROWN,
        age = 60,
        image = R.drawable.dog6,
        place = "Japan",
    ),
    DogModel(
        id = "7",
        name = "Jasper",
        sex = Sex.FEMALE,
        color = DogColor.BROWN,
        age = 70,
        image = R.drawable.dog7,
        place = "Japan",
    ),
    DogModel(
        id = "8",
        name = "Samson",
        sex = Sex.FEMALE,
        color = DogColor.BROWN,
        age = 80,
        image = R.drawable.dog8,
        place = "Japan",
    ),
    DogModel(
        id = "9",
        name = "Abby",
        sex = Sex.FEMALE,
        color = DogColor.BROWN,
        age = 90,
        image = R.drawable.dog9,
        place = "Japan",
    ),
    DogModel(
        id = "10",
        name = "Oliver",
        sex = Sex.FEMALE,
        color = DogColor.BROWN,
        age = 90,
        image = R.drawable.dog10,
        place = "Japan",
    ),
    DogModel(
        id = "10",
        name = "Copper",
        sex = Sex.FEMALE,
        color = DogColor.BROWN,
        age = 90,
        image = R.drawable.dog11,
    ),
)

val dogMap = dogList.map { it.id to it }.toMap()