package com.example.currencytracker.feature.currency.popular.di

import com.example.currencytracker.di.FragmentScope
import com.example.currencytracker.feature.currency.popular.di.module.PopularCurrenciesModule
import com.example.currencytracker.feature.currency.popular.presentation.PopularCurrenciesFragment
import com.example.currencytracker.feature.currency.popular.presentation.viewmodel.PopularCurrenciesViewModel
import dagger.Subcomponent

@FragmentScope
@Subcomponent( modules = [
    PopularCurrenciesModule::class])
interface PopularCurrenciesSubComponent {

    fun inject(fragment: PopularCurrenciesFragment)

    fun injectViewModel() : PopularCurrenciesViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create() : PopularCurrenciesSubComponent
    }

}