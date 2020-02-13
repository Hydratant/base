package com.tami.base.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tami.base.BaseActivity
import com.tami.base.dp2px
import com.tami.base.hideKeyboard

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun test() {
        showProgress()

    }
}
