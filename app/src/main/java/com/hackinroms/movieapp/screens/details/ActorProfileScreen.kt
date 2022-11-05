package com.hackinroms.movieapp.screens.details

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.hackinroms.movieapp.models.getMovie

@Composable
fun ActorProfileScreen(navController: NavController, actor: String?) {

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
        Text(text = actor ?: "Unknown Actor")
      }
    }
  }) {
    Box(modifier = Modifier
      .fillMaxSize()) {
      AndroidView(
        factory = { context ->
          WebView(context).apply {
            webViewClient = WebViewClient()
            loadUrl("https://www.google.com/search?q=${actor?.replace(" ", "+")}")
          }
        },
        update = { webView ->
          webView.loadUrl("https://www.google.com/search?q=${actor?.replace(" ", "+")}")
        }
      )
    }
  }
}


@Preview
@Composable
fun LoadWebUrl(url: String = "https://www.google.com") {
  AndroidView(factory = {
    WebView( it).apply {
      webViewClient = WebViewClient()

      loadUrl(url)
    }
  })
}