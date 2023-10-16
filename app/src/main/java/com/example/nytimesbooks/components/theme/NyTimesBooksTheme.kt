package com.example.nytimesbooks.components.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NyTimesBooksTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkThemeColors else lightThemeColors
    val typography = LocalTypography.current
    val shapes = LocalShapes.current

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography,
        LocalShapes provides shapes,
        content = content
    )
}

object NyTimesBooksTheme {

    val colors: NyTimesBooksColors
        @Composable
        get() = LocalColors.current

    val typography: NyTimesBooksTypography
        @Composable
        get() = LocalTypography.current

    val shapes: NyTimesBooksShapes
        @Composable
        get() = LocalShapes.current
}

// Colors

private val LocalColors = staticCompositionLocalOf { commonColors }

val commonColors = NyTimesBooksColors(
    primary = Color.Unspecified,
    primaryVariant = Color.Unspecified,

    white = Color(0xFFFFFFFF),
    black = Color(0xFF000000),

    transparent = Color.Transparent
)

val lightThemeColors = with(commonColors) {
    copy(
        primary = white,
        primaryVariant = black,
    )
}

val darkThemeColors = with(commonColors) {
    copy(
        primary = black,
        primaryVariant = white,
    )
}


// Typograpy

private val LocalTypography = compositionLocalOf { commonTypography }

val commonTypography = NyTimesBooksTypography(
    headerRegular = TextStyle(
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        fontFamily = FontFamily.Monospace,
        fontSize = 18.sp
    ),
    headerBold = TextStyle(
        fontWeight = FontWeight.W600,
        fontStyle = FontStyle.Normal,
        fontFamily = FontFamily.Monospace,
        fontSize = 18.sp
    ),

    primaryRegular = TextStyle(
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        fontFamily = FontFamily.Monospace,
        fontSize = 14.sp
    ),
    primaryBold = TextStyle(
        fontWeight = FontWeight.W600,
        fontStyle = FontStyle.Normal,
        fontFamily = FontFamily.Monospace,
        fontSize = 14.sp
    ),

    hintRegular = TextStyle(
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        fontFamily = FontFamily.Monospace,
        fontSize = 10.sp,
        letterSpacing = 0.sp
    )
)

// Shapes

private val LocalShapes = staticCompositionLocalOf { commonShapes }

private val commonShapes = NyTimesBooksShapes(
    medium = RoundedCornerShape(10.dp)
)