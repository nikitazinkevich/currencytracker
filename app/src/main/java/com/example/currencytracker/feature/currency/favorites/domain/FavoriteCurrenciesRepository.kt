package com.example.currencytracker.feature.currency.favorites.domain

import com.example.currencytracker.feature.currency.popular.domain.model.Currency

interface FavoriteCurrenciesRepository {

    suspend fun getFavoriteCurrenciesList(): List<Currency>
}