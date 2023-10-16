package com.example.nytimesbooks.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.nytimesbooks.components.theme.NyTimesBooksTheme

@Composable
fun EmptyListView(
    text: String,
    modifier: Modifier = Modifier
) = Box(
    modifier = modifier,
    contentAlignment = Alignment.Center
) {
    Text(
        text = text,
        modifier = modifier,
        style = NyTimesBooksTheme.typography.headerBold
    )
}