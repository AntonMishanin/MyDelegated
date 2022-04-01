package com.my.myapplication

import android.util.Log
import kotlin.reflect.KProperty

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