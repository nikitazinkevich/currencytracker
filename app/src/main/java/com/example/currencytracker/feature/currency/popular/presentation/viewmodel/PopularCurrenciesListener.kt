package com.example.currencytracker.feature.currency.popular.presentation.viewmodel

import com.example.currencytracker.feature.currency.popular.domain.model.Currency


interface PopularCurrenciesListener :
    PopularCurrenciesListListener


interface PopularCurrenciesListListener {

    fun onAddToFavoriteClick(currency: Currency)
}
