package com.hackinroms.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import com.hackinroms.movieapp.models.Movie
import com.hackinroms.movieapp.models.getImagePainter
import com.hackinroms.movieapp.models.getMovies

@Preview
@Composable
fun MovieItem(movie: Movie = getMovies()[7], onItemClick: (String) -> Unit = {}) {

  val imagePainter = getImagePainter(url = movie.poster)

  var expanded by remember { mutableStateOf(false) }

  Card(
    modifier = Modifier
      .padding(4.dp)
      .fillMaxWidth()
      //.height(100.dp)
      .clickable {
        onItemClick(movie.id)
      },
    shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    elevation = 6.dp
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Start
    ) {
      Surface(
        modifier = Modifier
          .padding(12.dp)
          .size(100.dp),
        shape = RectangleShape,
        elevation = 4.dp
      ) {

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
          contentDescription = movie.title,
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .clip(shape = CircleShape)
        )
      }

      Column(
        modifier = Modifier.padding(4.dp)
      ) {
        Text(
          text = movie.title,
          style = MaterialTheme.typography.h6
        )
        Text(
          text = "Director: ${movie.director}",
          style = MaterialTheme.typography.caption
        )
        Text(
          text = "Released: ${movie.year}",
          style = MaterialTheme.typography.caption
        )

        AnimatedVisibility(
          visible = expanded,
          modifier = Modifier.padding(6.dp)
        ) {
          Column {
            Text(
              buildAnnotatedString {
              withStyle(style = SpanStyle(
                color = MaterialTheme.colors.onBackground,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
              )
              ) {
                append("Plot: ")
              }
              withStyle(style = SpanStyle(
                color = MaterialTheme.colors.onBackground,
                fontSize = 13.sp,
                fontWeight = FontWeight.Light
              )
              ) {
                append(movie.plot)
              }
            },
              modifier = Modifier.padding(2.dp)
            )

            Divider(modifier = Modifier.padding(3.dp))

            Text(text = "Director: ${movie.director}",
              style = MaterialTheme.typography.caption,
              modifier = Modifier.padding(2.dp),
            )
            Text(text = "Genre: ${movie.genre}",
              style = MaterialTheme.typography.caption,
              modifier = Modifier.padding(2.dp),
            )
            Text(text = "Rating: ${movie.rating}",
              style = MaterialTheme.typography.caption,
              modifier = Modifier.padding(2.dp),
            )
          }
        }

        Icon(
          imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
          contentDescription = "View More",
          modifier = Modifier
            .padding(0.dp)
            .size(30.dp)
            .clickable { expanded = !expanded }
        )
      }
    }
  }
}
