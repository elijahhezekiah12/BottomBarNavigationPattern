package com.example.bottombarnavigationpattern.ui.theme.screens.mainScreen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bottombarnavigationpattern.ui.theme.screens.Route
import com.example.bottombarnavigationpattern.ui.theme.screens.SimpleScreen


// Navigation hierarchy (i.e. mapping routes to screens)
@Composable
fun MainScreenContent(){
    val navController = rememberNavController()
    val navigateToNextScreen: (String) -> Unit =  {  destinationRoute ->
        val currentScreenNum = navController.currentBackStackEntry?.arguments?.getString("num") ?: "0"
        val nextScreenNum = currentScreenNum.toInt() + 1
        navController.navigate(destinationRoute.replace("{num}", "$nextScreenNum"))

    }

    NavHost(navController, startDestination = Route.HomeRoot.route) {
        composable(Route.HomeRoot.route) {
            SimpleScreen(
                title = Route.HomeRoot.title,
                onNavigateToNextScreenClicked = { navigateToNextScreen(Route.HomeChild.route) }
            )
        }

        composable(Route.HomeChild.route) { backStackEntry ->
            val screenNum = backStackEntry.arguments?.getString("num") ?: "0"
            SimpleScreen(
                title = "${Route.HomeChild.title} $screenNum",
                onNavigateToNextScreenClicked = { navigateToNextScreen(Route.HomeChild.route) }
            )
        }
        composable(Route.SettingsRoot.route) {
            SimpleScreen(
                title = Route.SettingsRoot.title,
                onNavigateToNextScreenClicked = { navigateToNextScreen(Route.SettingsChild.route) }
            )
        }
        composable(Route.SettingsChild.route) { backStackEntry ->
            val screenNum = backStackEntry.arguments?.getString("num") ?: "0"
            SimpleScreen(
                title = "${Route.SettingsChild.title} $screenNum",
                onNavigateToNextScreenClicked = { navigateToNextScreen(Route.SettingsChild.route) }
            )
        }
    }


}