package com.example.currencytracker.feature.currency.popular.data.mapper

import com.example.currencytracker.Mapper
import com.example.currencytracker.feature.currency.popular.data.model.LatestResponse
import com.example.currencytracker.feature.currency.popular.domain.model.Currency
import com.example.currencytracker.network.calladapter.HttpResult
import com.example.currencytracker.utils.DataWrapper
import com.example.currencytracker.utils.Status

class CurrenciesMapper : Mapper<HttpResult<LatestResponse>, DataWrapper<List<Currency>>> {

    override fun map(entity: HttpResult<LatestResponse>): DataWrapper<List<Currency>> {
       return when (entity) {
            is HttpResult.Success -> DataWrapper(
                status = Status.Success,
                data = mapCurrencies(entity.data!!)
            )
           is HttpResult.Failure -> DataWrapper(
               status = Status.Error(message = entity.message)
           )
           is HttpResult.NetworkError -> DataWrapper(
               status = Status.Exception(throwable = entity.throwable))
        }
    }

    private fun mapCurrencies(response: LatestResponse): List<Currency> {
        return response.rates.toList().map { pair ->
            Currency(
                symbol = pair.first.trim(),
                value = pair.second
            )
        }
    }
}