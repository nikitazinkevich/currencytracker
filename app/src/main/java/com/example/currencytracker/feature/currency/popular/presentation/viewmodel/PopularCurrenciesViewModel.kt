package com.example.currencytracker.feature.currency.popular.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencytracker.feature.settings.SortSettings
import com.example.currencytracker.feature.settings.SortSettingsKey
import com.example.currencytracker.feature.currency.popular.domain.usecases.AddCurrencyToFavoriteUseCase
import com.example.currencytracker.feature.currency.popular.domain.usecases.GetPopularCurrenciesUseCase
import com.example.currencytracker.feature.currency.popular.domain.model.Currency
import com.example.currencytracker.utils.LoadingState
import com.example.currencytracker.utils.onError
import com.example.currencytracker.utils.onException
import com.example.currencytracker.utils.onSuccess
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PopularCurrenciesViewModel @AssistedInject constructor(
    private val getPopularCurrenciesUseCase: GetPopularCurrenciesUseCase,
    private val addCurrencyToFavoriteUseCase: AddCurrencyToFavoriteUseCase
) : ViewModel(), PopularCurrenciesListener {

    private val _popularCurrenciesStateFlow = MutableStateFlow<List<Currency>>(mutableListOf())
    val popularCurrenciesStateFlow: StateFlow<List<Currency>>
        get() = _popularCurrenciesStateFlow

    private val _loadingStateFlow = MutableStateFlow(LoadingState.LOADING)
    val loadingStateFlow: StateFlow<LoadingState>
        get() = _loadingStateFlow

    private val _swipeRefreshStateFlow = MutableStateFlow(LoadingState.LOADING)
    val swipeRefreshStateFlow: StateFlow<LoadingState>
        get() = _swipeRefreshStateFlow

    private val exceptionHandler = CoroutineExceptionHandler {_, throwable ->
        Log.i(TAG, throwable.message.toString())
    }

    private fun List<Currency>.applySortingSettings(
        sortSettingsKey: SortSettingsKey
    ): List<Currency> {
        return when (sortSettingsKey) {
            is SortSettingsKey.Alphabetical -> this.alphabeticalSort(sortSettingsKey)
            else -> this.valueSort(sortSettingsKey)
        }
    }

    private fun List<Currency>.alphabeticalSort(sortSettingsKey: SortSettingsKey): List<Currency> {
        return if (sortSettingsKey.isAscending) {
            this.sortedBy { currency -> currency.symbol }
        } else {
            this.sortedByDescending { currency -> currency.symbol }
        }
    }

    private fun List<Currency>.valueSort(sortSettingsKey: SortSettingsKey): List<Currency> {
        return if (sortSettingsKey.isAscending) {
            this.sortedBy { currency -> currency.value }
        } else {
            this.sortedByDescending { currency -> currency.value }
        }
    }

    override fun onAddToFavoriteClick(currency: Currency) {
        viewModelScope.launch(exceptionHandler) {
            addCurrencyToFavoriteUseCase(currency)
        }
    }

    fun refreshPopularCurrencies(baseCurrency: String? = null) {
        viewModelScope.launch(exceptionHandler) {
            updateLoadingState(newState = LoadingState.LOADING)
            getPopularCurrenciesUseCase(baseCurrency)
                .onSuccess { currencyList ->
                    setCurrenciesList(currencyList)
                    updateLoadingState(newState = LoadingState.LOADED)
                }
                .onError {
                    updateLoadingState(newState = LoadingState.ERROR)
                }
                .onException {
                    updateLoadingState(newState = LoadingState.EXCEPTION)
                }
        }
    }


    fun resetScreenState() {
        viewModelScope.launch(exceptionHandler) {
            resetSwipeRefreshProgress()
            updateLoadingState(newState = LoadingState.LOADING)
            getPopularCurrenciesUseCase()
                .onSuccess { currencyList ->
                    setCurrenciesList(currencyList)
                    stopSwipeRefreshProgress()
                    updateLoadingState(newState = LoadingState.LOADED)
                }
                .onError {
                    stopSwipeRefreshProgress()
                    updateLoadingState(newState = LoadingState.ERROR)
                }
                .onException {
                    stopSwipeRefreshProgress()
                    updateLoadingState(newState = LoadingState.EXCEPTION)
                }
        }
    }

    private fun updateLoadingState(newState: LoadingState) {
        _loadingStateFlow.value = newState
    }

    private fun stopSwipeRefreshProgress() {
        _swipeRefreshStateFlow.value = LoadingState.LOADED
    }

    private fun resetSwipeRefreshProgress() {
        _swipeRefreshStateFlow.value = LoadingState.LOADING
    }

    private fun setCurrenciesList(currencyList: List<Currency>) {
        _popularCurrenciesStateFlow.value = currencyList
            .applySortingSettings(SortSettings.currentSortSettingsKey)
    }

    @AssistedFactory
    interface Factory {
        fun create(): PopularCurrenciesViewModel
    }

    companion object {
        val TAG = PopularCurrenciesViewModel::class.simpleName
    }
}