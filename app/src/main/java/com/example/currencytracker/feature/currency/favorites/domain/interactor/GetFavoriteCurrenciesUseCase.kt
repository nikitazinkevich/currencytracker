package com.example.currencytracker.feature.currency.favorites.domain.interactor

import com.example.currencytracker.feature.currency.favorites.domain.FavoriteCurrenciesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetFavoriteCurrenciesUseCase(
    private val favoriteCurrenciesRepository: FavoriteCurrenciesRepository
) {

    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        favoriteCurrenciesRepository.getFavoriteCurrenciesList()
    }
}