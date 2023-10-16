package com.example.nytimesbooks.components.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle

@Immutable
data class NyTimesBooksTypography(
    val headerRegular: TextStyle,
    val headerBold: TextStyle,

    val primaryRegular: TextStyle,
    val primaryBold: TextStyle,

    val hintRegular: TextStyle
)