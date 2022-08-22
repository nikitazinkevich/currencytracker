package com.example.currencytracker.feature.currency.favorites.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencytracker.feature.currency.favorites.domain.interactor.GetFavoriteCurrenciesUseCase
import com.example.currencytracker.feature.currency.popular.domain.model.Currency
import com.example.currencytracker.feature.currency.popular.presentation.viewmodel.PopularCurrenciesViewModel
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteCurrenciesViewModel @AssistedInject constructor(
    private val getFavoriteCurrenciesUseCase: GetFavoriteCurrenciesUseCase
) : ViewModel() {

    private val _favoriteCurrenciesStateFlow = MutableStateFlow<List<Currency>>(mutableListOf())
    val favoriteCurrenciesStateFlow: StateFlow<List<Currency>>
        get() = _favoriteCurrenciesStateFlow

    private val exceptionHandler = CoroutineExceptionHandler {_, throwable ->
        Log.i(PopularCurrenciesViewModel.TAG, throwable.message.toString())
    }

    init {
        initFavoriteCurrencies()
    }

    private fun initFavoriteCurrencies(){
        viewModelScope.launch(exceptionHandler) {
            _favoriteCurrenciesStateFlow.value = getFavoriteCurrenciesUseCase()
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(): FavoriteCurrenciesViewModel
    }
}