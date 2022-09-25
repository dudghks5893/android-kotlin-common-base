@file:Suppress("unused")

package com.example.common_base.sharedpref

import com.google.gson.reflect.TypeToken
import com.example.common_base.sharedpref.base.SharedPrefKeyPropertyProvider
import com.example.common_base.sharedpref.default.withDefault
import com.example.common_base.sharedpref.optional.BooleanDelegateProvider
import com.example.common_base.sharedpref.optional.FloatDelegateProvider
import com.example.common_base.sharedpref.optional.GsonDelegateFactory
import com.example.common_base.sharedpref.optional.IntDelegateProvider
import com.example.common_base.sharedpref.optional.LongDelegateProvider
import com.example.common_base.sharedpref.optional.StringDelegateProvider
import com.example.common_base.sharedpref.optional.StringSetDelegateProvider
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import java.lang.reflect.Type

/**
 * SharedPreference에 저장되는 변수형별로 Provider 생성 함수를 정의
 **/
fun SharedPrefManager.booleanPref(key: String? = null): SharedPrefKeyPropertyProvider<Boolean?> {
    return BooleanDelegateProvider(key)
}

fun SharedPrefManager.floatPref(key: String? = null): SharedPrefKeyPropertyProvider<Float?> {
    return FloatDelegateProvider(key)
}

fun SharedPrefManager.intPref(key: String? = null): SharedPrefKeyPropertyProvider<Int?> {
    return IntDelegateProvider(key)
}

fun SharedPrefManager.longPref(key: String? = null): SharedPrefKeyPropertyProvider<Long?> {
    return LongDelegateProvider(key)
}

fun SharedPrefManager.stringPref(key: String? = null): SharedPrefKeyPropertyProvider<String?> {
    return StringDelegateProvider(key)
}

fun SharedPrefManager.stringSetPref(key: String? = null): SharedPrefKeyPropertyProvider<Set<String>?> {
    return StringSetDelegateProvider(key)
}

fun SharedPrefManager.booleanPref(key: String? = null, defaultValue: Boolean): PropertyDelegateProvider<SharedPrefManager, ReadWriteProperty<SharedPrefManager, Boolean>> {
    return booleanPref(key).withDefault(defaultValue)
}

fun SharedPrefManager.floatPref(key: String? = null, defaultValue: Float): PropertyDelegateProvider<SharedPrefManager, ReadWriteProperty<SharedPrefManager, Float>> {
    return floatPref(key).withDefault(defaultValue)
}

fun SharedPrefManager.intPref(key: String? = null, defaultValue: Int): PropertyDelegateProvider<SharedPrefManager, ReadWriteProperty<SharedPrefManager, Int>> {
    return intPref(key).withDefault(defaultValue)
}

fun SharedPrefManager.longPref(key: String? = null, defaultValue: Long): PropertyDelegateProvider<SharedPrefManager, ReadWriteProperty<SharedPrefManager, Long>> {
    return longPref(key).withDefault(defaultValue)
}

fun SharedPrefManager.stringPref(key: String? = null, defaultValue: String): PropertyDelegateProvider<SharedPrefManager, ReadWriteProperty<SharedPrefManager, String>> {
    return stringPref(key).withDefault(defaultValue)
}

fun SharedPrefManager.stringSetPref(key: String? = null, defaultValue: Set<String>): PropertyDelegateProvider<SharedPrefManager, ReadWriteProperty<SharedPrefManager, Set<String>>> {
    return stringSetPref(key).withDefault(defaultValue)
}

 inline fun <reified T : Any> SharedPrefManager.gsonPref(
    key: String? = null,
): SharedPrefKeyPropertyProvider<T?> {
    return gsonPrefImpl(key, object : TypeToken<T>() {}.type)
}

@PublishedApi
internal fun <T : Any> SharedPrefManager.gsonPrefImpl(
    key: String?,
    type: Type,
): SharedPrefKeyPropertyProvider<T?> {
    return GsonDelegateFactory(key, type)
}

 inline fun <reified T : Any> SharedPrefManager.gsonPref(
    key: String? = null,
    defaultValue: T,
): PropertyDelegateProvider<SharedPrefManager, ReadWriteProperty<SharedPrefManager, T>> {
    return gsonPref<T>(key).withDefault(defaultValue)
}
