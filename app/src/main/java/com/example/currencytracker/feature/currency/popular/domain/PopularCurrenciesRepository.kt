package com.example.currencytracker.feature.currency.popular.domain

import com.example.currencytracker.feature.currency.popular.domain.model.Currency
import com.example.currencytracker.utils.DataWrapper

interface PopularCurrenciesRepository {

    suspend fun getPopularCurrenciesList() : DataWrapper<List<Currency>>

    suspend fun getPopularCurrenciesList(baseCurrency: String) : DataWrapper<List<Currency>>

    suspend fun addPopularCurrencyToFavoriteList(currency: Currency)
}