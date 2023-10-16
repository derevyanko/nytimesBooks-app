package com.example.nytimesbooks.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nytimesbooks.R
import com.example.nytimesbooks.components.EmptyListView
import com.example.nytimesbooks.components.ErrorView
import com.example.nytimesbooks.components.LoadingView
import com.example.nytimesbooks.components.theme.NyTimesBooksTheme
import com.example.nytimesbooks.presentation.Routes
import com.example.nytimesbooks.presentation.viewmodel.CategoriesViewModel
import com.nytimesbooks.domain.base.UIState
import com.nytimesbooks.domain.models.categories.Categories
import com.nytimesbooks.domain.models.categories.Category

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: CategoriesViewModel
) {
    LaunchedEffect(Unit, block = { viewModel.getCategories() })

    val state = viewModel.categoriesSharedFlow.collectAsState(initial = UIState.Idle).value

    val isRefreshing = state is UIState.Loading
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = viewModel::getCategories
    )

    Scaffold(
        modifier = modifier,
        topBar = { TopBar() }
    ) {
        Box(
            modifier = modifier
                .padding(it)
                .pullRefresh(pullRefreshState)
                .background(NyTimesBooksTheme.colors.white)
        ) {
            CategoriesScreenContent(
                modifier = Modifier,
                state = state,
                onCategoryClick = { categoryName ->
                    val route = Routes.Books.BooksScreen.withArgs(categoryName)
                    navController.navigate(route = route)
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
    modifier: Modifier = Modifier
) = TopAppBar(
    title = {
        Text(
            text = stringResource(R.string.categories),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    },
    modifier = modifier,
    colors = TopAppBarDefaults.smallTopAppBarColors(NyTimesBooksTheme.colors.white)
)

@Composable
private fun CategoriesScreenContent(
    modifier: Modifier = Modifier,
    state: UIState<Categories>,
    onCategoryClick: (String) -> Unit = {}
) = when (state) {
    is UIState.Success -> CategoriesView(
        categories = state.data,
        onCategoryClick = onCategoryClick
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
        message = stringResource(R.string.categories_are_loading),
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    )

    else -> {}
}

@Composable
private fun CategoriesView(
    modifier: Modifier = Modifier,
    categories: Categories,
    onCategoryClick: (String) -> Unit = {}
) {
    if (categories.numberResults == 0) {
        EmptyListView(
            text = stringResource(R.string.empty_categories),
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        )
    } else {
        CategoriesList(
            categories = categories.categories,
            onCategoryClick = onCategoryClick
        )
    }
}

@Composable
private fun CategoriesList(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    onCategoryClick: (String) -> Unit = {}
) = LazyColumn(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(8.dp)
) {
    items(categories) {
        CategoryView(
            category = it,
            onClick = onCategoryClick
        )
    }
}

@Composable
private fun CategoryView(
    modifier: Modifier = Modifier,
    category: Category,
    onClick: (String) -> Unit = {}
) = Row(
    modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
        .clickable { onClick(category.listNameEncoded) },
    verticalAlignment = Alignment.CenterVertically
) {
    Column {
        Text(
            text = category.displayName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = NyTimesBooksTheme.typography.primaryBold
        )

        Text(
            text = category.newestPublishedDate,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = NyTimesBooksTheme.typography.hintRegular
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CategoriesScreen(
        navController = rememberNavController(),
        viewModel = hiltViewModel()
    )
}