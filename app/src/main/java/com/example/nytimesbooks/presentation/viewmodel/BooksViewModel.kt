package com.example.nytimesbooks.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.nytimesbooks.domain.base.UIState
import com.nytimesbooks.domain.models.books.Books
import com.nytimesbooks.domain.usecases.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val coroutineScope: CoroutineScope,
    private val getBooksUseCase: GetBooksUseCase
): ViewModel() {

    private var _booksMutableSharedFlow = MutableSharedFlow<UIState<Books>>()
    val booksSharedFlow = _booksMutableSharedFlow

    fun getBooks(list: String) = coroutineScope.launch {
        getBooksUseCase.invoke(list).collect {
            _booksMutableSharedFlow.emit(it)
        }
    }
}