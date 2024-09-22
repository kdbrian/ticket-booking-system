package io.github.junrdev.booker.util

sealed class ResponseWrapper<T>(
    val data: T? = null,
    val message: String? = null
) {

    // data exists
    class Success<T>(data: T?) : ResponseWrapper<T>(data)

    // no data, error message only
    class Error<T>(message: String?, data: T? = null) : ResponseWrapper<T>(data, message)

    // no data or error message
    class Loading<T>() : ResponseWrapper<T>()
}