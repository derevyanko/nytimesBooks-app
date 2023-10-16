package com.example.nytimesbooks.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.nytimesbooks.R
import com.example.nytimesbooks.components.theme.NyTimesBooksTheme

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    message: String
) = Box(
    modifier = modifier,
    contentAlignment = Alignment.Center
) {
    Text(
        text = message,
        style = NyTimesBooksTheme.typography.headerBold
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ErrorView(
        message = stringResource(R.string.something_went_wrong),
        modifier = Modifier
            .fillMaxSize()
    )
}