package com.example.currencytracker.feature.currency.popular.domain.usecases

import com.example.currencytracker.feature.currency.popular.domain.PopularCurrenciesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPopularCurrenciesUseCase(private val repository: PopularCurrenciesRepository) {

    suspend operator fun invoke(baseCurrencySymbol: String? = null) = withContext(Dispatchers.IO) {
        when (baseCurrencySymbol) {
            null -> repository.getPopularCurrenciesList()
            else -> repository.getPopularCurrenciesList(baseCurrencySymbol)
        }
    }
}