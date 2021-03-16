package com.example.anchorbay.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.anchorbay.ui.baylist.BayList
import com.example.anchorbay.ui.editbay.EditBay

sealed class Screen(val route: String) {
    object List : Screen("list")
    object New : Screen("new")
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable()
fun Routing() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "list") {
        composable(Screen.List.route) {
            BayList({}, { navController.navigate(Screen.New.route) })
        }
        composable(Screen.New.route) {
            EditBay()
        }
    }
}