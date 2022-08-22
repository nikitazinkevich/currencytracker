package com.example.currencytracker.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencytracker.database.entity.FavoriteCurrency
import com.example.currencytracker.feature.currency.popular.domain.model.Currency

@Dao
interface FavoriteCurrenciesDao {

    @Query("SELECT * FROM favorite_currency ORDER BY symbol DESC" )
    suspend fun getFavoriteCurrencies() : List<FavoriteCurrency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currency: FavoriteCurrency)
}