package com.example.currencytracker.feature.currency.favorites.di

import com.example.currencytracker.di.FragmentScope
import com.example.currencytracker.feature.currency.favorites.di.module.FavoriteCurrenciesModule
import com.example.currencytracker.feature.currency.favorites.presentation.FavoriteCurrenciesFragment
import com.example.currencytracker.feature.currency.favorites.presentation.viewmodel.FavoriteCurrenciesViewModel
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [FavoriteCurrenciesModule::class])
interface FavoriteCurrenciesSubComponent {

    fun inject(fragment: FavoriteCurrenciesFragment)

    fun injectViewModel() : FavoriteCurrenciesViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): FavoriteCurrenciesSubComponent
    }
}