package com.example.currencytracker.feature.currency.favorites.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.currencytracker.feature.currency.favorites.presentation.adapter.delegates.favoriteCurrenciesDelegate
import com.example.currencytracker.feature.currency.popular.domain.model.Currency
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class FavoriteCurrenciesAdapter: AsyncListDifferDelegationAdapter<Currency>(
    FavoriteCurrenciesDiffCallback(),
    favoriteCurrenciesDelegate()
)

class FavoriteCurrenciesDiffCallback : DiffUtil.ItemCallback<Currency>() {

    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem::class.java == newItem::class.java
    }

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem == newItem
    }

}

