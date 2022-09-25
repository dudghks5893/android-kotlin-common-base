package com.example.common_base.sharedpref.optional

import com.example.common_base.sharedpref.util.edit
import com.example.common_base.sharedpref.SharedPrefManager
import com.example.common_base.sharedpref.base.SharedPrefKeyProperty
import com.example.common_base.sharedpref.base.SharedPrefKeyPropertyProvider
import kotlin.reflect.KProperty

/**
 * Long 형
 **/
internal class LongDelegate(
    override val key: String,
) : SharedPrefKeyProperty<Long?> {
    override operator fun getValue(thisRef: SharedPrefManager, property: KProperty<*>): Long? {
        return if (!thisRef.sharedPreferences.contains(key)) {
            null
        } else {
            thisRef.sharedPreferences.getLong(key, 0L)
        }
    }

    override operator fun setValue(thisRef: SharedPrefManager, property: KProperty<*>, value: Long?) {
        if (value == null) {
            thisRef.sharedPreferences.edit { remove(key) }
        } else {
            thisRef.sharedPreferences.edit { putLong(key, value) }
        }
    }
}

internal class LongDelegateProvider(
    val key: String?,
) : SharedPrefKeyPropertyProvider<Long?> {
    override fun provideDelegate(thisRef: SharedPrefManager, property: KProperty<*>): LongDelegate {
        return LongDelegate(key ?: property.name)
    }
}
