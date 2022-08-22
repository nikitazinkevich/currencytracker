package com.example.currencytracker.feature.currency.favorites.data.repository

import com.example.currencytracker.database.dao.FavoriteCurrenciesDao
import com.example.currencytracker.feature.currency.favorites.data.mapper.FavoriteCurrencyToCurrencyMapper
import com.example.currencytracker.feature.currency.favorites.domain.FavoriteCurrenciesRepository
import com.example.currencytracker.feature.currency.popular.domain.model.Currency

class FavoriteCurrenciesRepositoryImpl(
    private val favoriteCurrenciesDao: FavoriteCurrenciesDao,
    private val mapper: FavoriteCurrencyToCurrencyMapper
) : FavoriteCurrenciesRepository {

    override suspend fun getFavoriteCurrenciesList(): List<Currency> {
        return mapper.map(favoriteCurrenciesDao.getFavoriteCurrencies()).toList()
    }
}