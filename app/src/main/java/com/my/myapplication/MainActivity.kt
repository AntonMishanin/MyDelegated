package com.my.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlin.properties.Delegates

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

