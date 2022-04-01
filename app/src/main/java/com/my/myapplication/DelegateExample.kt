package com.my.myapplication

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class DelegateExample {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

fun delegatedPropertyExample(): ReadWriteProperty<Any, String> =
    object : ReadWriteProperty<Any, String> {
        override fun getValue(thisRef: Any, property: KProperty<*>) = "dss"

        override fun setValue(thisRef: Any, property: KProperty<*>, value: String) = Unit
    }