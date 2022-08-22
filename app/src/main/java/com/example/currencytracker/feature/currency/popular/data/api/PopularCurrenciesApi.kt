package com.example.currencytracker.feature.currency.popular.data.api

import com.example.currencytracker.feature.currency.popular.data.model.LatestResponse
import com.example.currencytracker.network.calladapter.HttpResult
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularCurrenciesApi {

    @GET("v1/latest")
    suspend fun getPopularCurrenciesList(): HttpResult<LatestResponse>

    @GET("v1/latest")
    suspend fun getPopularCurrenciesListByBase(
        @Query("base") baseCurrency: String
    ): HttpResult<LatestResponse>
}