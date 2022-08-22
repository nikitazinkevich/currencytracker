package com.example.currencytracker.network.calladapter

import com.example.currencytracker.network.calladapter.delegate.CallDelegate
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class HttpResultCall<T>(proxy: Call<T>) : CallDelegate<T, HttpResult<T>>(proxy) {
    override fun enqueueImpl(callback: Callback<HttpResult<T>>) =
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val code = response.code()
                val result = if (code in 200 until 300) {
                    val body = response.body()
//                    val successResult: HttpResult<T> = HttpResult.Success(body)
//                    successResult
                    HttpResult.Success(body)
                } else {
                    HttpResult.Failure(statusCode = code, message = response.message())
                }
                callback.onResponse(this@HttpResultCall, Response.success(result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val result = if (t is IOException) {
                    HttpResult.NetworkError(t)
                } else {
                    HttpResult.Failure(null, null)
                }

                callback.onResponse(this@HttpResultCall, Response.success(result))
            }
        })

    override fun cloneImpl() = HttpResultCall(proxy.clone())

    override fun timeout(): Timeout {
        return proxy.timeout()
    }
}