package com.example.currencytracker.feature.currency.favorites.presentation

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.currencytracker.CoreFragment
import com.example.currencytracker.R
import com.example.currencytracker.databinding.FragmentFavoriteCurrenceisBinding
import com.example.currencytracker.feature.currency.favorites.di.FavoriteCurrenciesSubComponent
import com.example.currencytracker.feature.currency.favorites.presentation.adapter.FavoriteCurrenciesAdapter
import com.example.currencytracker.feature.currency.favorites.presentation.viewmodel.FavoriteCurrenciesViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteCurrenciesFragment :
    CoreFragment<FragmentFavoriteCurrenceisBinding, FavoriteCurrenciesViewModel>() {

    override val binding by viewBinding(FragmentFavoriteCurrenceisBinding::bind)
    override val fragmentViewModel by lazy { component.injectViewModel().create() }
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { FavoriteCurrenciesAdapter() }
    private lateinit var component: FavoriteCurrenciesSubComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component = mainActivity.component.favoriteCurrenciesSubComponent().create()
    }

    override fun initView() {
        super.initView()
        initRecyclerView()
    }

    override fun observeData() {
        observeFavoriteCurrencyList()
    }

    override fun getFragmentLayoutId(): Int = R.layout.fragment_favorite_currenceis

    private fun observeFavoriteCurrencyList() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                fragmentViewModel.favoriteCurrenciesStateFlow.collect { favoriteCurrenciesList ->
                    adapter.items = favoriteCurrenciesList
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.favoriteCurrenciesRecyclerView.adapter = adapter
    }
}