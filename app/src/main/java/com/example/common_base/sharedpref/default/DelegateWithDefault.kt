@file:Suppress("unused")

package com.example.common_base.sharedpref.default

import com.example.common_base.sharedpref.SharedPrefManager
import com.example.common_base.sharedpref.base.SharedPrefKeyProperty
import com.example.common_base.sharedpref.base.SharedPrefKeyPropertyProvider
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 기본 Delegate 정의
 **/
class DelegateWithDefault<T>(
    private val delegate: SharedPrefKeyProperty<T?>,
    private val defaultValue: T,
) : ReadWriteProperty<SharedPrefManager, T> {
    // get/set 함수 재정의
    override fun getValue(thisRef: SharedPrefManager, property: KProperty<*>): T {
        return if (delegate.key !in thisRef.sharedPreferences) {
            defaultValue
        } else {
            delegate.getValue(thisRef, property)!!
        }
    }

    override fun setValue(thisRef: SharedPrefManager, property: KProperty<*>, value: T) {
        delegate.setValue(thisRef, property, value)
    }
}

class DelegateWithDefaultFactory<T>(
    private val propertyDelegateProvider: PropertyDelegateProvider<SharedPrefManager, SharedPrefKeyProperty<T?>>,
    private val defaultValue: T,
) : PropertyDelegateProvider<SharedPrefManager, ReadWriteProperty<SharedPrefManager, T>> {

    override fun provideDelegate(thisRef: SharedPrefManager, property: KProperty<*>): ReadWriteProperty<SharedPrefManager, T> {
        return DelegateWithDefault(
            // Delegate 대상에 SharedPrefManager ref 와 property 전달
            delegate = propertyDelegateProvider.provideDelegate(thisRef, property),
            defaultValue = defaultValue
        )
    }
}

// Property 별로 정의된 함수 호출
fun <T : Any?> SharedPrefKeyProperty<T?>.withDefault(
    defaultValue: T
): ReadWriteProperty<SharedPrefManager, T> {
    return DelegateWithDefault(this, defaultValue)
}

// Property 별로 정의된 함수 호출
fun <T : Any?> SharedPrefKeyPropertyProvider<T?>.withDefault(
    defaultValue: T
): PropertyDelegateProvider<SharedPrefManager, ReadWriteProperty<SharedPrefManager, T>> {
    // 여기서 this 는 Delegate 대상
    return DelegateWithDefaultFactory(this, defaultValue)
}
