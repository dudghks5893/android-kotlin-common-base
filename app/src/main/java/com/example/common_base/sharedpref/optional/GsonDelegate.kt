package com.example.common_base.sharedpref.optional

import com.example.common_base.sharedpref.util.edit
import com.google.gson.TypeAdapter
import com.example.common_base.sharedpref.SharedPrefManager
import com.example.common_base.sharedpref.base.SharedPrefKeyProperty
import com.example.common_base.sharedpref.base.SharedPrefKeyPropertyProvider
import kotlin.reflect.KProperty
import java.lang.reflect.Type
import com.google.gson.reflect.TypeToken
import com.example.common_base.sharedpref.instance.internalGson

/**
 * Gson 형
 **/
class GsonDelegate<T : Any>(
    override val key: String,
    private val adapter: TypeAdapter<T>,
) : SharedPrefKeyProperty<T?> {

    override operator fun getValue(thisRef: SharedPrefManager, property: KProperty<*>): T? {
        return if (key !in thisRef.sharedPreferences) {
            null
        } else {
            val string = requireNotNull(thisRef.sharedPreferences.getString(key, null))
            adapter.fromJson(string)
        }
    }

    override operator fun setValue(thisRef: SharedPrefManager, property: KProperty<*>, value: T?) {
        if (value == null) {
            thisRef.sharedPreferences.edit {
                remove(key)
            }
        } else {
            thisRef.sharedPreferences.edit {
                putString(key, adapter.toJson(value))
            }
        }
    }
}

internal class GsonDelegateFactory<T : Any>(
    private val key: String?,
    private val type: Type,
) : SharedPrefKeyPropertyProvider<T?> {

    override fun provideDelegate(thisRef: SharedPrefManager, property: KProperty<*>): SharedPrefKeyProperty<T?> {
        @Suppress("UNCHECKED_CAST")
        val adapter: TypeAdapter<T> = thisRef.internalGson.getAdapter(TypeToken.get(type)) as TypeAdapter<T>
        return GsonDelegate(key ?: property.name, adapter)
    }
}