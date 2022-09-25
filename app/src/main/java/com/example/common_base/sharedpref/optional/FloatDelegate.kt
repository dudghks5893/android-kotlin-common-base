package com.example.common_base.sharedpref.optional

import com.example.common_base.sharedpref.util.edit
import com.example.common_base.sharedpref.SharedPrefManager
import com.example.common_base.sharedpref.base.SharedPrefKeyProperty
import com.example.common_base.sharedpref.base.SharedPrefKeyPropertyProvider
import kotlin.reflect.KProperty

/**
 * Float 형
 **/
internal class FloatDelegate(
    override val key: String,
) : SharedPrefKeyProperty<Float?> {
    override operator fun getValue(thisRef: SharedPrefManager, property: KProperty<*>): Float? {
        return if (!thisRef.sharedPreferences.contains(key)) {
            null
        } else {
            thisRef.sharedPreferences.getFloat(key, 0f)
        }
    }

    override operator fun setValue(thisRef: SharedPrefManager, property: KProperty<*>, value: Float?) {
        if (value == null) {
            thisRef.sharedPreferences.edit { remove(key) }
        } else {
            thisRef.sharedPreferences.edit { putFloat(key, value) }
        }
    }
}

internal class FloatDelegateProvider(
    val key: String?,
) : SharedPrefKeyPropertyProvider<Float?> {
    override fun provideDelegate(thisRef: SharedPrefManager, property: KProperty<*>): FloatDelegate {
        return FloatDelegate(key ?: property.name)
    }
}
