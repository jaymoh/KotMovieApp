package com.hackinroms.movieapp.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hackinroms.movieapp.models.Movie
import com.hackinroms.movieapp.models.getMovies
import com.hackinroms.movieapp.navigation.MovieScreens
import com.hackinroms.movieapp.widgets.MovieItem

@Composable
fun HomeScreen(navController: NavController) {
  Scaffold(topBar = {
    TopAppBar(
      backgroundColor = Color.Gray,
      elevation = 5.dp,
    ) {
      Text(text = "Movies")
    }
  }) {
    MainContent(navController = navController)
  }
}

@Composable
fun MainContent(
  navController: NavController,
  movieList: List<Movie> = getMovies()
) {
  Column(
    modifier = Modifier.padding(12.dp)
  ) {
    LazyColumn(content = {
      items(items = movieList) {
        MovieItem(movie = it) { movie ->
          navController.navigate(route = MovieScreens.DetailScreen.name + "/$movie")
        }
      }
    })
  }
}

