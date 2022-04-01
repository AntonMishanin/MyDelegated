package com.my.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.my.myapplication.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val value by MyLazy { SomeClass() }
        binding.text.setOnClickListener {
            Log.d("EE", "click on value = $value")
        }

        val sellPhone = SellPhone()
        sellPhone.makeCall()
        sellPhone.makePhoto()

        var messageAfter by Delegates.observable("value") { p, old, new ->
            Log.d("EE", "old = $old new = $new")
        }

        var messageBefore by Delegates.vetoable("value") { p, old, new ->
            Log.d("EE", "old = $old new = $new")
            false
        }

        findViewById<View>(R.id.send_message).setOnClickListener {
            messageBefore = "sdsd"
        }
    }
}

