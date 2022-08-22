package com.example.currencytracker.feature.currency.popular.data.model

import com.google.gson.annotations.SerializedName

data class LatestResponse(

    @SerializedName("name")
    val base: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("rates")
    val rates: Map<String, Float>
)