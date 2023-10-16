package com.example.nytimesbooks.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.nytimesbooks.R
import com.example.nytimesbooks.components.EmptyListView
import com.example.nytimesbooks.components.ErrorView
import com.example.nytimesbooks.components.LoadingView
import com.example.nytimesbooks.components.theme.NyTimesBooksTheme
import com.example.nytimesbooks.presentation.Routes
import com.example.nytimesbooks.presentation.viewmodel.BooksViewModel
import com.nytimesbooks.domain.base.UIState
import com.nytimesbooks.domain.models.books.Book
import com.nytimesbooks.domain.models.books.Books

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun BooksScreen(
    listNameEncoded: String,
    modifier: Modifier = Modifier,
    viewModel: BooksViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(Unit, block = { viewModel.getBooks(listNameEncoded) })

    val state = viewModel.booksSharedFlow.collectAsStateWithLifecycle(initialValue = UIState.Idle).value

    val isRefreshing = state is UIState.Loading
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { viewModel.getBooks(listNameEncoded) }
    )

    Scaffold(
        modifier = modifier,
        topBar = { TopBar(state = state) }
    ) {
        Box(
            modifier = modifier
                .padding(it)
                .pullRefresh(pullRefreshState)
                .background(NyTimesBooksTheme.colors.white)
        ) {
            BooksScreenContent(
                modifier = Modifier
                    .padding(it)
                    .align(Alignment.TopCenter),
                state = state,
                onBuyButtonClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        Routes.Books.BuyBookScreen.KEY_URL,
                        value = it
                    )
                    navController.navigate(route = Routes.Books.BuyBookScreen.route)
                }
            )

            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    state: UIState<Books>
) = TopAppBar(
    title = {
        val title = when (state) {
            is UIState.Success -> state.data.listNameDisplay
            else -> stringResource(R.string.books)
        }

        Text(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = NyTimesBooksTheme.typography.headerBold
        )
    },
    modifier = modifier,
    colors = TopAppBarDefaults.smallTopAppBarColors(NyTimesBooksTheme.colors.white)
)

@Composable
private fun BooksScreenContent(
    modifier: Modifier = Modifier,
    state: UIState<Books>,
    onBuyButtonClick: (String) -> Unit = {}
) = when (state) {
    is UIState.Success -> BooksView(
        books = state.data,
        onBuyButtonClick = onBuyButtonClick
    )

    is UIState.Failure -> when (state.error) {
        else -> ErrorView(
            message = stringResource(R.string.something_went_wrong),
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        )
    }

    is UIState.Loading -> LoadingView(
        message = stringResource(R.string.books_are_loading),
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    )

    else -> {}
}

@Composable
private fun BooksView(
    modifier: Modifier = Modifier,
    books: Books,
    onBuyButtonClick: (String) -> Unit = {}
) {
    if (books.books.isEmpty()) {
        EmptyListView(
            text = stringResource(R.string.empty_books),
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        )
    } else {
        BooksList(
            books = books.books,
            onBuyButtonClick = onBuyButtonClick
        )
    }
}

@Composable
private fun BooksList(
    modifier: Modifier = Modifier,
    books: List<Book>,
    onBuyButtonClick: (String) -> Unit = {}
) = LazyColumn(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(8.dp),
) {
    items(books) {
        BookView(
            book = it,
            onBuyButtonClick = onBuyButtonClick
        )
    }
}

@Composable
private fun BookView(
    modifier: Modifier = Modifier,
    book: Book,
    onBuyButtonClick: (String) -> Unit
) = Row(
    modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
        .border(
            width = 1.dp,
            color = NyTimesBooksTheme.colors.black,
            shape = NyTimesBooksTheme.shapes.medium
        )
        .clip(NyTimesBooksTheme.shapes.medium)
        .clickable { onBuyButtonClick(book.urlAmazon) },
    verticalAlignment = Alignment.Top,
) {
    AsyncImage(
        model = book.urlImage,
        contentDescription = null,
        modifier = Modifier
            .width(120.dp)
    )

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = stringResource(R.string.title) + book.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = NyTimesBooksTheme.typography.primaryRegular
        )

        Text(
            text = stringResource(R.string.description) + book.description,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis,
            style = NyTimesBooksTheme.typography.primaryRegular
        )

        Text(
            text = stringResource(R.string.author) + book.author,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = NyTimesBooksTheme.typography.primaryRegular
        )

        Text(
            text = stringResource(R.string.publisher) + book.publisher,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = NyTimesBooksTheme.typography.primaryRegular
        )

        Text(
            text = stringResource(R.string.rank) + book.rank,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = NyTimesBooksTheme.typography.primaryRegular
        )
    }
}