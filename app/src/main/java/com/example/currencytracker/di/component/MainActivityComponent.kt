package com.example.currencytracker.di.component

import com.example.currencytracker.MainActivity
import com.example.currencytracker.di.ActivityScope
import com.example.currencytracker.feature.currency.favorites.di.FavoriteCurrenciesSubComponent
import com.example.currencytracker.feature.currency.popular.di.PopularCurrenciesSubComponent
import dagger.Component
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface MainActivityComponent {

    fun inject(activity: MainActivity)

    fun popularCurrenciesSubComponent() : PopularCurrenciesSubComponent.Factory

    fun favoriteCurrenciesSubComponent() : FavoriteCurrenciesSubComponent.Factory

    @Subcomponent.Factory
    interface Factory {

        fun create() : MainActivityComponent
    }
}