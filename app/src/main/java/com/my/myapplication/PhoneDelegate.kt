package com.my.myapplication

import android.util.Log

interface Phone {
    fun makeCall()
}

class Samsung : Phone {
    override fun makeCall() {
        Log.d("EE", "makeCall")
    }
}

interface Camera {
    fun makePhoto()
}

class BigCamera : Camera {
    override fun makePhoto() {
        Log.d("EE", "makePhoto")
    }
}

class SellPhone(
    private val phone: Phone = Samsung(),
    private val camera: Camera = BigCamera()
) : Phone by phone, Camera by camera