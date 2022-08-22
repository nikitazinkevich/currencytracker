package com.example.currencytracker.utils

data class DataWrapper<T>(
    val status: Status,
    val data: T? = null,
)

sealed class Status {

    object Success : Status()

    data class Error(val message: String? = null) : Status()

    data class Exception(val throwable: Throwable? = null) : Status()
}

inline fun <T> DataWrapper<T>.onSuccess(action: (T) -> Unit): DataWrapper<T> {
    if (data != null && status is Status.Success) {
        action(data)
    }
    return this
}

fun <T> DataWrapper<T>.onError(action: (Status.Error) -> Unit): DataWrapper<T> {
    if (status is Status.Error) {
        action(status)
    }
    return this
}

inline fun <T> DataWrapper<T>.onException(action: (Status.Exception) -> Unit): DataWrapper<T> {
    if (status is Status.Exception) {
        action(status)
    }
    return this
}
