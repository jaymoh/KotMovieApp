package com.hackinroms.movieapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import com.hackinroms.movieapp.models.Movie
import com.hackinroms.movieapp.models.getImagePainter
import com.hackinroms.movieapp.models.getMovie
import com.hackinroms.movieapp.navigation.MovieScreens
import com.hackinroms.movieapp.widgets.MovieItem

@Composable
fun DetailScreen(navController: NavController, movieId: String?) {
  val movie = movieId?.let { getMovie(movieId) }
  val actors = movie?.actors?.split(",") ?: listOf()

  Scaffold(topBar = {
    TopAppBar(
      backgroundColor = Color.Gray,
      elevation = 0.dp,
    ) {
      Row(
        horizontalArrangement = Arrangement.Start
      ) {
        Icon(
          imageVector = Icons.Default.ArrowBack,
          contentDescription = "Arrow Back",
          modifier = Modifier.clickable {
            navController.popBackStack()
          }
        )

        Spacer(modifier = Modifier.width(100.dp))
        Text(text = movie?.title ?: "Movie Details")
      }
    }
  }) {
    Surface(
      modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
      ) {

        if (movie != null) {
          MovieItem(movie = movie)

          Spacer(modifier = Modifier.height(15.dp))
          Divider()

          Text(text = "Movie Images")
          HorizontalScrollableImageView(movie)

          Spacer(modifier = Modifier.height(15.dp))
          Divider()

          Text(text = "Movie Actors")
          ActorsList(actors = actors) { actor ->
            navController.navigate(route = MovieScreens.ActorProfileScreen.name + "/$actor")
          }

        } else {
          Text(
            text = "No Movie Details Found",
            style = MaterialTheme.typography.h4
          )
        }
      }
    }
  }
}

@Composable
private fun HorizontalScrollableImageView(movie: Movie) {
  LazyRow {
    items(movie.images) { image ->
      Card(
        modifier = Modifier
          .padding(8.dp)
          .size(200.dp),
        elevation = 5.dp
      ) {
        val imagePainter = getImagePainter(url = image)

        if (imagePainter.state is AsyncImagePainter.State.Loading ||
          imagePainter.state is AsyncImagePainter.State.Error
        ) {
          CircularProgressIndicator(
            modifier = Modifier
              .fillMaxSize()
              .padding(6.dp)
          )
        }

        Image(
          painter = imagePainter,
          contentDescription = "Movie Image",
          modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .clip(shape = RectangleShape)
        )
      }
    }
  }
}

@Composable
private fun ActorsList(actors: List<String>, onActorClick: (String) -> Unit) {
  LazyColumn {
    items(actors) { actor ->
      Card(
        modifier = Modifier
          .padding(5.dp)
          .clickable {
            onActorClick(actor)
          }
        ,
        elevation = 5.dp
      ) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
          contentAlignment = Alignment.CenterStart,
        ) {
          Text(
            text = actor,
            fontSize = 16.sp,
            modifier = Modifier.padding(6.dp),
          )
        }
      }
    }
  }
}

