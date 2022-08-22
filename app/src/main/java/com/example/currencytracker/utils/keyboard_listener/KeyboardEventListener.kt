package com.example.currencytracker.utils.keyboard_listener

import android.view.ViewTreeObserver
import androidx.annotation.CallSuper
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.example.currencytracker.extensions.isKeyboardOpen

class KeyboardEventListener(
    private val binding: ViewBinding,
    lifecycleOwner: LifecycleOwner,
    private val callback: (isOpen: Boolean) -> Unit,
) : DefaultLifecycleObserver {

    private val listener: ViewTreeObserver.OnGlobalLayoutListener by lazy(::initOnGlobalLayoutListener)

    init {
        dispatchKeyboardEvent(binding.isKeyboardOpen())
        lifecycleOwner.lifecycle.addObserver(this)
        registerKeyboardListener()
    }

    @CallSuper
    override fun onStart(owner: LifecycleOwner) {
        registerKeyboardListener()
    }

    @CallSuper
    override fun onStop(owner: LifecycleOwner) {
        unregisterKeyboardListener()
    }

    private fun initOnGlobalLayoutListener(): ViewTreeObserver.OnGlobalLayoutListener {
        return object : ViewTreeObserver.OnGlobalLayoutListener {
            private var lastState: Boolean = binding.isKeyboardOpen()

            override fun onGlobalLayout() {
                val isOpen = binding.isKeyboardOpen()
                if (isOpen == lastState) {
                    return
                } else {
                    dispatchKeyboardEvent(isOpen)
                    lastState = isOpen
                }
            }
        }
    }

    private fun dispatchKeyboardEvent(isOpen: Boolean) {
        callback(isOpen)
    }

    private fun registerKeyboardListener() {
        binding.root.viewTreeObserver.addOnGlobalLayoutListener(listener)
    }

    private fun unregisterKeyboardListener() {
        binding.root.viewTreeObserver.removeOnGlobalLayoutListener(listener)
    }
}