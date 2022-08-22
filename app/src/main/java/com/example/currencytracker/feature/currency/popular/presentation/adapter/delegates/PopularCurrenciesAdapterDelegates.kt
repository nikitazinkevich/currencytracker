package com.example.currencytracker.feature.currency.popular.presentation.adapter.delegates

import android.annotation.SuppressLint
import com.example.currencytracker.R
import com.example.currencytracker.databinding.ItemPopularCurrenciesBinding
import com.example.currencytracker.feature.currency.popular.domain.model.Currency
import com.example.currencytracker.feature.currency.popular.presentation.viewmodel.PopularCurrenciesListListener
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

@SuppressLint("UseCompatLoadingForDrawables")
fun currenciesDelegate(
    listener: PopularCurrenciesListListener
) = adapterDelegateViewBinding<Currency, Currency, ItemPopularCurrenciesBinding>(
    viewBinding = { layoutInflater, viewGroup ->
        ItemPopularCurrenciesBinding.inflate(layoutInflater, viewGroup, false)
    },
) {
    bind {
        with(binding) {
            currencySymbolTextView.text = item.symbol
            currencyValueTextView.text = item.value.toString()
            currencyFavoriteIconImageView.setImageDrawable(
                context.resources.getDrawable(
                    R.drawable.ic_outline_favorite,
                    context.theme
                )
            )
            currencyFavoriteIconImageView.setOnClickListener {
                currencyFavoriteIconImageView.setImageDrawable(
                    context.resources.getDrawable(
                        R.drawable.ic_filled_favorite,
                        context.theme
                    )
                )
                listener.onAddToFavoriteClick(item)
            }
        }
    }
}