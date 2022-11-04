package com.hackinroms.movieapp.navigation

enum class MovieScreens {
  HomeScreen,
  DetailScreen,
  ProfileScreen;

  companion object {
    fun fromRoute(route: String?): MovieScreens = when (route?.substringBefore("/")) {
      HomeScreen.name -> HomeScreen
      DetailScreen.name -> DetailScreen
      ProfileScreen.name -> ProfileScreen
      null -> HomeScreen
      else -> throw IllegalArgumentException("Unknown route $route")
    }
  }
}