package com.example.currencytracker.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.currencytracker.ViewModelFactory

inline fun <reified VM : ViewModel> Fragment.viewModelFactory(
    noinline factory: () -> VM,
): Lazy<VM> = viewModels {
    ViewModelFactory(factory)
}