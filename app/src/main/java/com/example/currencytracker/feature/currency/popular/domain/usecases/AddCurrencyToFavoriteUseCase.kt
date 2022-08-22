package com.example.currencytracker.feature.currency.popular.domain.usecases

import com.example.currencytracker.feature.currency.popular.domain.PopularCurrenciesRepository
import com.example.currencytracker.feature.currency.popular.domain.model.Currency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddCurrencyToFavoriteUseCase(
    private val popularCurrenciesRepository: PopularCurrenciesRepository
) {

    suspend operator fun invoke(currency: Currency) = withContext(Dispatchers.IO) {
        popularCurrenciesRepository.addPopularCurrencyToFavoriteList(currency)
    }
}