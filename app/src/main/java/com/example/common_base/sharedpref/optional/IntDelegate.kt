package com.example.common_base.sharedpref.optional

import com.example.common_base.sharedpref.SharedPrefManager
import com.example.common_base.sharedpref.base.SharedPrefKeyProperty
import com.example.common_base.sharedpref.base.SharedPrefKeyPropertyProvider
import com.example.common_base.sharedpref.util.edit
import kotlin.reflect.KProperty

/**
 * Int 형
 **/
internal class IntDelegate(
    override val key: String,
) : SharedPrefKeyProperty<Int?> {
    override operator fun getValue(thisRef: SharedPrefManager, property: KProperty<*>): Int? {
        return if (!thisRef.sharedPreferences.contains(key)) {
            null
        } else {
            thisRef.sharedPreferences.getInt(key, 0)
        }
    }

    override operator fun setValue(thisRef: SharedPrefManager, property: KProperty<*>, value: Int?) {
        if (value == null) {
            thisRef.sharedPreferences.edit { remove(key) }
        } else {
            thisRef.sharedPreferences.edit { putInt(key, value) }
        }
    }
}

internal class IntDelegateProvider(
    val key: String?,
) : SharedPrefKeyPropertyProvider<Int?> {
    override fun provideDelegate(thisRef: SharedPrefManager, property: KProperty<*>): IntDelegate {
        return IntDelegate(key ?: property.name)
    }
}
