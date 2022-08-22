package com.example.currencytracker.network.calladapter

sealed class HttpResult<out T> {
    data class Success<T>(val data: T?) : HttpResult<T>()
    data class Failure(val statusCode: Int?, val message: String?) : HttpResult<Nothing>()
    data class NetworkError(val throwable: Throwable) : HttpResult<Nothing>()
}