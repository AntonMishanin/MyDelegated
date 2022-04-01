package com.my.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlin.reflect.KProperty

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val value by MyLazy { SomeClass() }
        findViewById<View>(R.id.text).setOnClickListener {
            Log.d("EE", "click on value = $value")
        }

        val sellPhone = SellPhone()
        sellPhone.makeCall()
        sellPhone.makePhoto()
    }
}

class MyLazy<T>(initializer: () -> T) {

    private var initializer: (() -> T)? = initializer
    private var isInit = false
    private var value: T? = null

    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return if (isInit) {
            value as T
        } else {
            value = initializer!!()
            initializer = null
            isInit = true
            value as T
        }
    }
}

class SomeClass {
    init {
        Log.d("EE", "SomeClass() init")
    }
}

