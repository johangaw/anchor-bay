package com.example.anchorbay.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.anchorbay.data.BayViewModel
import com.example.anchorbay.ui.baylist.BayList
import com.example.anchorbay.ui.editbay.NewBay
import com.example.anchorbay.ui.showbay.ShowBay

sealed class Screen(val route: String) {
    object List : Screen("list")
    object New : Screen("new")
    object Show : Screen("show/{bayId}")
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
            BayList(bays.value, { navController.navigate("show/${it.nickname}")  }, { navController.navigate(Screen.New.route) })
        }
        composable(Screen.New.route) {
            NewBay(onSubmit = {
                viewModel.addNewBay(it)
                navController.navigate(Screen.List.route)
            })
        }
        composable(
            Screen.Show.route,
            arguments = listOf(navArgument("bayId") { type = NavType.StringType })
        ) {
            val bayId = it.arguments?.getString("bayId")
            val bay = viewModel.getBay(bayId!!)
            bay?.let {
                ShowBay(bay = bay)
            }
        }
    }
}