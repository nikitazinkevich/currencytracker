package com.example.currencytracker.feature.currency.popular.data.mapper

import com.example.currencytracker.Mapper
import com.example.currencytracker.database.entity.FavoriteCurrency
import com.example.currencytracker.feature.currency.popular.domain.model.Currency

class CurrencyToFavoriteCurrencyMapper : Mapper<Currency, FavoriteCurrency> {

    override fun map(entity: Currency): FavoriteCurrency {
        return FavoriteCurrency(
            symbol = entity.symbol,
            value = entity.value
        )
    }
}