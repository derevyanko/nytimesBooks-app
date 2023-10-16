package com.example.nytimesbooks.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.nytimesbooks.components.theme.NyTimesBooksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NyTimesBooksTheme {
                RootAppNavigation(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}