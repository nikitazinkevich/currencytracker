package com.example.currencytracker.feature.currency.popular.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.currencytracker.feature.currency.popular.domain.model.Currency
import com.example.currencytracker.feature.currency.popular.presentation.adapter.delegates.currenciesDelegate
import com.example.currencytracker.feature.currency.popular.presentation.viewmodel.PopularCurrenciesListener
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class PopularCurrenciesAdapter(listener: PopularCurrenciesListener) :
    AsyncListDifferDelegationAdapter<Currency>(
        PopularCurrenciesDiffCallback(),
        currenciesDelegate(listener)
    )

class PopularCurrenciesDiffCallback : DiffUtil.ItemCallback<Currency>() {

    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem::class.java == newItem::class.java
    }

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem == newItem
    }

}

