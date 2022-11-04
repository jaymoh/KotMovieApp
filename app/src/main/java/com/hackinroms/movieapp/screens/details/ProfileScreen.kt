package com.hackinroms.movieapp.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController) {

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
        Text(text = "Actor Profile")
      }
    }
  }) {
    Text(
      text = "Profile Screen",
      style = MaterialTheme.typography.h4
      )
  }
}