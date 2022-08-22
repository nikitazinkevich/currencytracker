package com.example.currencytracker.network.calladapter

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class HttpResultAdapter(
    private val type: Type
) : CallAdapter<Type, Call<HttpResult<Type>>> {
    override fun responseType() = type
    override fun adapt(call: Call<Type>): Call<HttpResult<Type>> = HttpResultCall(call)
}