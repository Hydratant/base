package com.tami.base

import androidx.appcompat.app.AppCompatActivity


/**
 * CharSequence 형태로 리턴해준다.
 */
fun AppCompatActivity.getText(text: Any?): CharSequence? {
    text?.let {
        if (it is CharSequence) return it
        return if (it is Int) this.getString(it) else it.toString()
    } ?: return null
}
