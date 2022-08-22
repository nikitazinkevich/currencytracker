package com.example.currencytracker.feature.currency.popular.di.module

import com.example.currencytracker.database.dao.FavoriteCurrenciesDao
import com.example.currencytracker.di.FragmentScope
import com.example.currencytracker.feature.currency.popular.data.api.PopularCurrenciesApi
import com.example.currencytracker.feature.currency.popular.data.mapper.CurrenciesMapper
import com.example.currencytracker.feature.currency.popular.data.mapper.CurrencyToFavoriteCurrencyMapper
import com.example.currencytracker.feature.currency.popular.data.repository.PopularCurrenciesRepositoryImpl
import com.example.currencytracker.feature.currency.popular.domain.PopularCurrenciesRepository
import com.example.currencytracker.feature.currency.popular.domain.usecases.AddCurrencyToFavoriteUseCase
import com.example.currencytracker.feature.currency.popular.domain.usecases.GetPopularCurrenciesUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object PopularCurrenciesModule {

    @FragmentScope
    @Provides
    fun providePopularCurrenciesApiClass(): Class<PopularCurrenciesApi> {
        return PopularCurrenciesApi::class.java
    }

    @FragmentScope
    @Provides
    fun providePopularCurrenciesApi(
        retrofit: Retrofit,
        popularCurrenciesApiClass: Class<PopularCurrenciesApi>
    ): PopularCurrenciesApi {
        return retrofit.create(popularCurrenciesApiClass)
    }

    @FragmentScope
    @Provides
    fun providePopularCurrenciesRepository(
        popularCurrenciesApi: PopularCurrenciesApi,
        favoriteCurrenciesDao: FavoriteCurrenciesDao,
        favoriteCurrencyMapper: CurrencyToFavoriteCurrencyMapper,
        popularCurrenciesMapper: CurrenciesMapper
    ): PopularCurrenciesRepository {
        return PopularCurrenciesRepositoryImpl(
            popularCurrenciesApi,
            favoriteCurrenciesDao,
            favoriteCurrencyMapper,
            popularCurrenciesMapper)
    }

    @FragmentScope
    @Provides
    fun provideGetPopularCurrenciesUseCase(popularCurrenciesRepository: PopularCurrenciesRepository
    ): GetPopularCurrenciesUseCase {
        return GetPopularCurrenciesUseCase(popularCurrenciesRepository)
    }

    @FragmentScope
    @Provides
    fun provideAddCurrencyToFavoriteUseCase(popularCurrenciesRepository: PopularCurrenciesRepository
    ): AddCurrencyToFavoriteUseCase{
        return AddCurrencyToFavoriteUseCase(popularCurrenciesRepository)
    }

    @FragmentScope
    @Provides
    fun provideCurrenciesMapper(): CurrenciesMapper {
        return CurrenciesMapper()
    }

    @FragmentScope
    @Provides
    fun provideCurrencyToFavoriteCurrencyMapper(): CurrencyToFavoriteCurrencyMapper {
        return CurrencyToFavoriteCurrencyMapper()
    }

}