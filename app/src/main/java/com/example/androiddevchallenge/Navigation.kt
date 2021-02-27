package com.example.androiddevchallenge

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
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