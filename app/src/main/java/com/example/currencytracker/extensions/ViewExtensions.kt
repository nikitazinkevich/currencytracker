package com.example.currencytracker.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.google.android.material.textfield.TextInputEditText

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.makeInvisible(){
    this.visibility = View.INVISIBLE
}


fun TextInputEditText.afterTextChanged(
    afterTextChanged: (String) -> Unit,
) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            if (editable.toString().isNotBlank()) {
                afterTextChanged.invoke(editable.toString())
            }
        }
    })
}