package com.example.currencytracker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.currencytracker.database.dao.FavoriteCurrenciesDao
import com.example.currencytracker.database.entity.FavoriteCurrency

@Database(entities = [FavoriteCurrency::class], version = 1, exportSchema = false)
abstract class CurrencyTrackerDataBase : RoomDatabase() {

    abstract fun favoriteCurrenciesDao() : FavoriteCurrenciesDao

}