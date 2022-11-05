package com.hackinroms.movieapp.navigation

enum class MovieScreens {
  HomeScreen,
  DetailScreen,
  ActorProfileScreen;

  companion object {
    fun fromRoute(route: String?): MovieScreens = when (route?.substringBefore("/")) {
      HomeScreen.name -> HomeScreen
      DetailScreen.name -> DetailScreen
      ActorProfileScreen.name -> ActorProfileScreen
      null -> HomeScreen
      else -> throw IllegalArgumentException("Unknown route $route")
    }
  }
}