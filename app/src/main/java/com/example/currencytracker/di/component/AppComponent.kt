package com.example.currencytracker.di.component

import android.content.Context
import com.example.currencytracker.MainActivity
import com.example.currencytracker.di.modules.DatabaseModule
import com.example.currencytracker.di.modules.NetworkModule
import com.example.currencytracker.feature.currency.favorites.di.FavoriteCurrenciesSubComponent
import com.example.currencytracker.feature.currency.popular.di.PopularCurrenciesSubComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, DatabaseModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context, ): AppComponent
    }

    fun mainActivityComponent() : MainActivityComponent.Factory
}