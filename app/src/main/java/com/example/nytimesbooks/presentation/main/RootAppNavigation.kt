package com.example.nytimesbooks.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nytimesbooks.components.WebViewScreen
import com.example.nytimesbooks.presentation.Routes
import com.example.nytimesbooks.presentation.view.BooksScreen
import com.example.nytimesbooks.presentation.view.CategoriesScreen
import com.example.nytimesbooks.presentation.viewmodel.BooksViewModel
import com.example.nytimesbooks.presentation.viewmodel.CategoriesViewModel

@Composable
fun RootAppNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Books.route) {
        navigation(
            startDestination = Routes.Books.CategoriesScreen.route,
            route = Routes.Books.route
        ) {
            composable(route = Routes.Books.CategoriesScreen.route) {
                val viewModel = hiltViewModel<CategoriesViewModel>()

                CategoriesScreen(
                    modifier = modifier,
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable(
                route = Routes.Books.BooksScreen.route + "/{${Routes.Books.BooksScreen.KEY_CATEGORY_NAME}}",
                arguments = listOf(
                    navArgument(Routes.Books.BooksScreen.KEY_CATEGORY_NAME) {
                        type = NavType.StringType
                    }
                )
            ) {
                val listNameEncoded = it.arguments?.getString(Routes.Books.BooksScreen.KEY_CATEGORY_NAME) ?: return@composable
                val viewModel = hiltViewModel<BooksViewModel>()

                BooksScreen(
                    listNameEncoded = listNameEncoded,
                    modifier = modifier,
                    viewModel = viewModel,
                    navController = navController
                )
            }

            composable(route = Routes.Books.BuyBookScreen.route) {
                val url = navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                    Routes.Books.BuyBookScreen.KEY_URL
                ) ?: return@composable

                WebViewScreen(
                    url = url,
                    modifier = modifier
                )
            }
        }
    }
}