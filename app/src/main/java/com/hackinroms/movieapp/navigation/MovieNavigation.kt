package com.hackinroms.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hackinroms.movieapp.screens.home.HomeScreen
import com.hackinroms.movieapp.screens.details.DetailScreen
import com.hackinroms.movieapp.screens.details.ProfileScreen

// Navigation Component > Nav Controller > Nav Host > Nav Graph
@Composable
fun MovieNavigation() { // Nav Component
  val navController = rememberNavController() // Nav Controller

  NavHost( // Nav Host
    navController = navController,
    startDestination = MovieScreens.HomeScreen.name
  ) {

    // Nav Graphs goes below
    composable(MovieScreens.HomeScreen.name) {
      // Where this navigation will go
      HomeScreen(navController = navController)
    }

    // with arguments
    composable(
      MovieScreens.DetailScreen.name + "/{movie}",
      arguments = listOf(navArgument(name = "movie") { type = NavType.StringType })
    ) {
      backStackEntry ->
      // Where this navigation will go
      DetailScreen(navController = navController, backStackEntry.arguments?.getString("movie"))
    }

    composable(MovieScreens.ProfileScreen.name) {
      // Where this navigation will go
      ProfileScreen(navController = navController)
    }
  }
}