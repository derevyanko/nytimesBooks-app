package com.example.nytimesbooks.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.nytimesbooks.domain.base.UIState
import com.nytimesbooks.domain.models.categories.Categories
import com.nytimesbooks.domain.usecases.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val coroutineScope: CoroutineScope,
    private val getCategoriesUseCase: GetCategoriesUseCase
): ViewModel() {

    private var _categoriesMutableSharedFlow = MutableSharedFlow<UIState<Categories>>()
    val categoriesSharedFlow: SharedFlow<UIState<Categories>>
        get() = _categoriesMutableSharedFlow

    fun getCategories() = coroutineScope.launch {
        getCategoriesUseCase.invoke().collect {
            _categoriesMutableSharedFlow.emit(it)
        }
    }
}