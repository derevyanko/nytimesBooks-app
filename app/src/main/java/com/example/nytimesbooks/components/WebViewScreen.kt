package com.example.nytimesbooks.components

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewScreen(
    url: String,
    modifier: Modifier = Modifier
) = AndroidView(
    modifier = modifier,
    factory = {
        WebView(it).apply {
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }
)