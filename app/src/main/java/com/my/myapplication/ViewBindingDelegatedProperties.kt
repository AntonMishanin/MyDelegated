package com.my.myapplication

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import java.lang.NullPointerException
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class viewBinding<T : ViewBinding>(
    private val initializer: (LayoutInflater) -> T,
    private val activity: AppCompatActivity
) : ReadOnlyProperty<AppCompatActivity, T>, DefaultLifecycleObserver {

    private var binding: T? = null

    init {
        activity.lifecycle.addObserver(this)
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        activity.lifecycle.removeObserver(this)
        binding = initializer(activity.layoutInflater)
        activity.setContentView(binding!!.root)
    }

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {

        if (binding == null) {
            throw NullPointerException("Can not getValue from viewBinding before onCreate")
        }

        return binding!!
    }
}