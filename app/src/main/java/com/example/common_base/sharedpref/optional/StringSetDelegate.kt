package com.example.common_base.sharedpref.optional

import com.example.common_base.sharedpref.util.edit
import com.example.common_base.sharedpref.SharedPrefManager
import com.example.common_base.sharedpref.base.SharedPrefKeyProperty
import com.example.common_base.sharedpref.base.SharedPrefKeyPropertyProvider
import kotlin.reflect.KProperty

/**
 * StringSet 형
 **/
internal class StringSetDelegate(
    override val key: String,
) : SharedPrefKeyProperty<Set<String>?> {
    override operator fun getValue(thisRef: SharedPrefManager, property: KProperty<*>): Set<String>? {
        return if (!thisRef.sharedPreferences.contains(key)) {
            null
        } else {
            thisRef.sharedPreferences.getStringSet(key, emptySet())
        }
    }

    override operator fun setValue(thisRef: SharedPrefManager, property: KProperty<*>, value: Set<String>?) {
        if (value == null) {
            thisRef.sharedPreferences.edit { remove(key) }
        } else {
            thisRef.sharedPreferences.edit { putStringSet(key, value) }
        }
    }
}

internal class StringSetDelegateProvider(
    val key: String?,
) : SharedPrefKeyPropertyProvider<Set<String>?> {
    override fun provideDelegate(thisRef: SharedPrefManager, property: KProperty<*>): StringSetDelegate {
        return StringSetDelegate(key ?: property.name)
    }
}
