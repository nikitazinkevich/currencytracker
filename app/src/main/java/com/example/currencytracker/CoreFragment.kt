package com.example.currencytracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class CoreFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {


    abstract val binding: VB
    protected abstract val fragmentViewModel: VM
    protected val mainActivity by lazy { activity as MainActivity }

    @LayoutRes
    abstract fun getFragmentLayoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(getFragmentLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        observeData()
    }
    open fun initView() {}

    open fun observeData() {}
}