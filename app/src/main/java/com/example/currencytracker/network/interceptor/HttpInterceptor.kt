package com.example.currencytracker.network.interceptor

import com.example.currencytracker.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HttpInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("apikey", BuildConfig.API_KEY)
            .build()
        return chain.proceed(request)
    }
}