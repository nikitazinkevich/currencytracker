package com.example.currencytracker.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_currency")
data class FavoriteCurrency(

    @PrimaryKey
    @ColumnInfo(name = "symbol")
    val symbol : String,

    @ColumnInfo(name = "value")
    val value: Float
)
