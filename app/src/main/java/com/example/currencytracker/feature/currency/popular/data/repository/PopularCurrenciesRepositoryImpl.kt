package com.example.currencytracker.feature.currency.popular.data.repository

import com.example.currencytracker.database.dao.FavoriteCurrenciesDao
import com.example.currencytracker.feature.currency.popular.data.api.PopularCurrenciesApi
import com.example.currencytracker.feature.currency.popular.data.mapper.CurrenciesMapper
import com.example.currencytracker.feature.currency.popular.data.mapper.CurrencyToFavoriteCurrencyMapper
import com.example.currencytracker.feature.currency.popular.domain.PopularCurrenciesRepository
import com.example.currencytracker.feature.currency.popular.domain.model.Currency
import com.example.currencytracker.utils.DataWrapper

class PopularCurrenciesRepositoryImpl(
    private val remoteSource: PopularCurrenciesApi,
    private val storageSource: FavoriteCurrenciesDao,
    private val favoriteCurrencyMapper: CurrencyToFavoriteCurrencyMapper,
    private val popularCurrencyMapper: CurrenciesMapper
) : PopularCurrenciesRepository {

    override suspend fun getPopularCurrenciesList(): DataWrapper<List<Currency>> {
        return popularCurrencyMapper.map(remoteSource.getPopularCurrenciesList())
    }

    override suspend fun getPopularCurrenciesList(baseCurrency: String): DataWrapper<List<Currency>> {
        return popularCurrencyMapper.map(remoteSource.getPopularCurrenciesListByBase(baseCurrency))
    }

    override suspend fun addPopularCurrencyToFavoriteList(currency: Currency) {
        return storageSource.insertCurrency(favoriteCurrencyMapper.map(currency))
    }
}