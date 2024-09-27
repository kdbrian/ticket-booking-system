package io.github.junrdev.booker.presentation.ui.util

data class UiStateWrapper<T>(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: T? = null
)