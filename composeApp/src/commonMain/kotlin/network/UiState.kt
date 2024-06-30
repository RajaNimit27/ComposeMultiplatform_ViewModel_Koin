package com.app.compose_navigation_mvvm_flow.utils

sealed class UiState<out T> {

    data class Success<T>(val data: T?) : UiState<T>()

    data class Error<T>(val message: String) : UiState<T>()

    object Loading : UiState<Nothing>()

}