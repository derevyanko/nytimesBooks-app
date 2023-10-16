package com.nytimesbooks.domain.base

open class UIState<out T> {

    data class Success<T>(val data: T) : UIState<T>()

    data class Failure<T>(val error: ErrorEntity) : UIState<T>()

    object Loading : UIState<Nothing>()

    object Idle : UIState<Nothing>()
}