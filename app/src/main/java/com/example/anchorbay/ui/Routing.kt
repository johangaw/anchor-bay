package com.example.anchorbay.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.anchorbay.data.BayViewModel
import com.example.anchorbay.ui.baylist.BayList
import com.example.anchorbay.ui.editbay.NewBay

sealed class Screen(val route: String) {
    object List : Screen("list")
    object New : Screen("new")
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable()
fun Routing() {
    val navController = rememberNavController()
    val viewModel: BayViewModel = viewModel()

    NavHost(navController, startDestination = "list") {
        composable(Screen.List.route) {
            val bays = viewModel.bays.observeAsState(emptyList())
            BayList(bays.value, {}, { navController.navigate(Screen.New.route) })
        }
        composable(Screen.New.route) {
            NewBay(onSubmit={
                viewModel.addNewBay(it)
                navController.navigate(Screen.List.route)
            })
        }
    }
}