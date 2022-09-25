package com.example.common_base.sharedpref.optional

import com.example.common_base.sharedpref.SharedPrefManager
import com.example.common_base.sharedpref.base.SharedPrefKeyProperty
import com.example.common_base.sharedpref.base.SharedPrefKeyPropertyProvider
import com.example.common_base.sharedpref.util.edit
import kotlin.reflect.KProperty

/**
 * Boolean 형
 **/
internal class BooleanDelegate(
    override val key: String,
) : SharedPrefKeyProperty<Boolean?> {
    override operator fun getValue(thisRef: SharedPrefManager, property: KProperty<*>): Boolean? {
        return if (!thisRef.sharedPreferences.contains(key)) {
            null
        } else {
            thisRef.sharedPreferences.getBoolean(key, false)
        }
    }

    override operator fun setValue(thisRef: SharedPrefManager, property: KProperty<*>, value: Boolean?) {
        if (value == null) {
            thisRef.sharedPreferences.edit { remove(key) }
        } else {
            thisRef.sharedPreferences.edit { putBoolean(key, value) }
        }
    }
}

internal class BooleanDelegateProvider(
    val key: String?,
) : SharedPrefKeyPropertyProvider<Boolean?> {
    override fun provideDelegate(thisRef: SharedPrefManager, property: KProperty<*>): BooleanDelegate {
        return BooleanDelegate(key ?: property.name)
    }
}
