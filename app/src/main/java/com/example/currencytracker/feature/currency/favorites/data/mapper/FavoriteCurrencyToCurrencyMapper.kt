package com.example.currencytracker.feature.currency.favorites.data.mapper

import com.example.currencytracker.Mapper
import com.example.currencytracker.database.entity.FavoriteCurrency
import com.example.currencytracker.feature.currency.popular.domain.model.Currency

class FavoriteCurrencyToCurrencyMapper : Mapper<FavoriteCurrency, Currency> {

    override fun map(entity: FavoriteCurrency): Currency {
        return Currency(
            symbol = entity.symbol,
            value = entity.value
        )
    }

    fun map(collection: Collection<FavoriteCurrency>) : Collection<Currency> {
        return collection.map(::map)
    }
}