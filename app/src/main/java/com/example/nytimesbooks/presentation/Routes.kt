package com.example.nytimesbooks.presentation

sealed class Routes(val route: String) {

    object Books : Routes("books") {

        object CategoriesScreen : Routes("categoriesScreen")

        object BooksScreen : Routes("booksScreen") {

            const val KEY_CATEGORY_NAME = "categoryName"
        }

        object BuyBookScreen : Routes("buyBook") {

            const val KEY_URL = "url"
        }
    }

    fun withArgs(vararg args: String) = buildString {
        append(route)
        args.forEach { arg ->
            append("/$arg")
        }
    }
}
