package com.example.currencytracker.feature.currency.favorites.di.module

import com.example.currencytracker.database.dao.FavoriteCurrenciesDao
import com.example.currencytracker.di.FragmentScope
import com.example.currencytracker.feature.currency.favorites.data.mapper.FavoriteCurrencyToCurrencyMapper
import com.example.currencytracker.feature.currency.favorites.data.repository.FavoriteCurrenciesRepositoryImpl
import com.example.currencytracker.feature.currency.favorites.domain.FavoriteCurrenciesRepository
import com.example.currencytracker.feature.currency.favorites.domain.interactor.GetFavoriteCurrenciesUseCase
import dagger.Module
import dagger.Provides

@Module
object FavoriteCurrenciesModule {

    @FragmentScope
    @Provides
    fun provideGetFavoriteCurrenciesUseCase(repository: FavoriteCurrenciesRepository
    ) : GetFavoriteCurrenciesUseCase {
        return  GetFavoriteCurrenciesUseCase(repository)
    }

    @FragmentScope
    @Provides
    fun provideFavoriteCurrenciesRepository(dao: FavoriteCurrenciesDao, mapper: FavoriteCurrencyToCurrencyMapper): FavoriteCurrenciesRepository {
        return FavoriteCurrenciesRepositoryImpl(dao, mapper)
    }

    @FragmentScope
    @Provides
    fun provideMapper(): FavoriteCurrencyToCurrencyMapper {
        return FavoriteCurrencyToCurrencyMapper()
    }

}