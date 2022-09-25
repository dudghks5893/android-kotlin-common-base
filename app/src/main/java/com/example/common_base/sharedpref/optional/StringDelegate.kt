package com.example.common_base.sharedpref.optional

import com.example.common_base.sharedpref.util.edit
import com.example.common_base.sharedpref.SharedPrefManager
import com.example.common_base.sharedpref.base.SharedPrefKeyProperty
import com.example.common_base.sharedpref.base.SharedPrefKeyPropertyProvider
import kotlin.reflect.KProperty

/**
 * String 형
 **/
internal class StringDelegate(
    override val key: String,
) : SharedPrefKeyProperty<String?> {
    override operator fun getValue(thisRef: SharedPrefManager, property: KProperty<*>): String? {
        return if (!thisRef.sharedPreferences.contains(key)) {
            null
        } else {
            thisRef.sharedPreferences.getString(key, null)
        }
    }

    override operator fun setValue(thisRef: SharedPrefManager, property: KProperty<*>, value: String?) {
        if (value == null) {
            thisRef.sharedPreferences.edit { remove(key) }
        } else {
            thisRef.sharedPreferences.edit { putString(key, value) }
        }
    }
}

internal class StringDelegateProvider(
    val key: String?,
) : SharedPrefKeyPropertyProvider<String?> {
    override fun provideDelegate(thisRef: SharedPrefManager, property: KProperty<*>): StringDelegate {
        return StringDelegate(key ?: property.name)
    }
}
