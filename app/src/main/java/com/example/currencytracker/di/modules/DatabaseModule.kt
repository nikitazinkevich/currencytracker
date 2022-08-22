package com.example.currencytracker.di.modules

import android.content.Context
import androidx.room.Room
import com.example.currencytracker.R
import com.example.currencytracker.database.CurrencyTrackerDataBase
import com.example.currencytracker.database.dao.FavoriteCurrenciesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Context): CurrencyTrackerDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            CurrencyTrackerDataBase::class.java,
            context.resources.getString(R.string.database_name)
        ).build()
    }

    @Singleton
    @Provides
    fun provideFavoriteCurrenciesDao(dataBase: CurrencyTrackerDataBase): FavoriteCurrenciesDao {
        return dataBase.favoriteCurrenciesDao()
    }

}