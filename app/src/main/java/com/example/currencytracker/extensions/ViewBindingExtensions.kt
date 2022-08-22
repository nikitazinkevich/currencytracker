package com.example.currencytracker.extensions

import android.graphics.Rect
import androidx.viewbinding.ViewBinding

private const val KEYBOARD_HEIGHT = 0.25

fun ViewBinding.isKeyboardOpen(): Boolean {
    val visibleBounds = Rect()
    root.getWindowVisibleDisplayFrame(visibleBounds)
    val heightDiff = root.rootView.height - visibleBounds.height()
    return heightDiff > KEYBOARD_HEIGHT * root.rootView.height
}

fun ViewBinding.isKeyboardClosed(): Boolean {
    return !isKeyboardOpen()
}