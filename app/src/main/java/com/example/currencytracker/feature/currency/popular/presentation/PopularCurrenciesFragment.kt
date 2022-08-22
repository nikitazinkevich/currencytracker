package com.example.currencytracker.feature.currency.popular.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.currencytracker.CoreFragment
import com.example.currencytracker.R
import com.example.currencytracker.databinding.FragmentPopularCurrenciesBinding
import com.example.currencytracker.extensions.viewModelFactory
import com.example.currencytracker.feature.currency.popular.di.PopularCurrenciesSubComponent
import com.example.currencytracker.feature.currency.popular.presentation.adapter.PopularCurrenciesAdapter
import com.example.currencytracker.extensions.hide
import com.example.currencytracker.utils.LoadingState
import com.example.currencytracker.extensions.show
import com.example.currencytracker.feature.currency.popular.presentation.viewmodel.PopularCurrenciesViewModel
import com.example.currencytracker.utils.keyboard_listener.KeyboardEventListener
import kotlinx.coroutines.flow.collect

import kotlinx.coroutines.launch

class PopularCurrenciesFragment :
    CoreFragment<FragmentPopularCurrenciesBinding, PopularCurrenciesViewModel>() {

    override val binding by viewBinding(FragmentPopularCurrenciesBinding::bind)
    override val fragmentViewModel: PopularCurrenciesViewModel by viewModelFactory {
        component.injectViewModel().create()
    }
    private lateinit var component: PopularCurrenciesSubComponent

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        PopularCurrenciesAdapter(fragmentViewModel)
    }

    override fun getFragmentLayoutId(): Int = R.layout.fragment_popular_currencies

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component = mainActivity.component.popularCurrenciesSubComponent().create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onStart() {
        super.onStart()
        fragmentViewModel.refreshPopularCurrencies()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initKeyboardListener()
    }

    override fun initView() {
        initSwipeRefreshLayout()
        initRecyclerView()
        initListeners()
    }

    override fun observeData() {
        observeSwipeRefreshState()
        observePopularCurrencyList()
        observeLoadingState()
    }

    private fun observePopularCurrencyList() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                fragmentViewModel.popularCurrenciesStateFlow.collect { currenciesList ->
                    adapter.items = currenciesList
                }
            }
        }
    }

    private fun observeSwipeRefreshState() {
        lifecycleScope.launch {
            fragmentViewModel.swipeRefreshStateFlow.collect { state ->
                handleSwipeRefreshState(state)
            }
        }
    }

    private fun observeLoadingState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                fragmentViewModel.loadingStateFlow.collect { loadingState ->
                    handleLoadingState(loadingState)
                }
            }
        }
    }

    private fun handleSwipeRefreshState(loadingState: LoadingState) {
        when (loadingState) {
            LoadingState.LOADED -> hideSwipeRefreshProgressBar()
            else -> return
        }
    }

    private fun handleLoadingState(loadingState: LoadingState) {
        when (loadingState) {
            LoadingState.LOADING -> {
                with(binding) {
                    popularCurrenciesRecyclerView.hide()
                    popularCurrenciesRequestErrorLayout.root.hide()
                    networkErrorLayout.root.hide()
                    progressIndicatorLayout.show()
                }
            }
            LoadingState.LOADED -> {
                with(binding) {
                    popularCurrenciesRecyclerView.show()
                    mainFragmentLayout.show()
                    progressIndicatorLayout.hide()
                    networkErrorLayout.root.hide()
                    popularCurrenciesRequestErrorLayout.root.hide()
                }
            }
            LoadingState.ERROR -> {
                with(binding) {
                    popularCurrenciesRequestErrorLayout.root.show()
                    networkErrorLayout.root.hide()
                    progressIndicatorLayout.hide()
                    popularCurrenciesRecyclerView.hide()
                }
            }
            LoadingState.EXCEPTION -> {
                with(binding) {
                    mainFragmentLayout.hide()
                    progressIndicatorLayout.hide()
                    popularCurrenciesRequestErrorLayout.root.hide()
                    networkErrorLayout.root.show()
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.popularCurrenciesRecyclerView.adapter = adapter
    }

    private fun initKeyboardListener() {
        KeyboardEventListener(binding, viewLifecycleOwner) { isOpen ->
            if (!isOpen) {
                binding.editText.refreshPopularCurrencies()
            }
        }
    }

    private fun initSwipeRefreshLayout() {
        binding.popularCurrenciesSwipeRefreshLayout.setColorSchemeColors(R.color.purple_200)
    }

    private fun hideSwipeRefreshProgressBar() {
        binding.popularCurrenciesSwipeRefreshLayout.isRefreshing = false
    }

    private fun initListeners() {
        with(binding) {

            sortSettingsImageButton.setOnClickListener {
                findNavController().navigate(R.id.open_sort_settings_fragment)
            }

            popularCurrenciesSwipeRefreshLayout.setOnRefreshListener {
                fragmentViewModel.resetScreenState()
            }
        }
    }

    private fun EditText.refreshPopularCurrencies() {
        val text = this.text.toString()
        if (text.isBlank() && isPopularCurrencyVisible()) {
            return
        } else {
            fragmentViewModel.refreshPopularCurrencies(text)
        }
    }

    private fun isPopularCurrencyVisible(): Boolean {
        return binding.popularCurrenciesRecyclerView.visibility == View.VISIBLE
    }
}

