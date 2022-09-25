package com.busanbank.mbe.sharedpref.validation

import com.example.common_base.sharedpref.SharedPrefManager
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * SharedPreference validation 체크
 **/
class ValidatedPreferenceDelegate<T>(
    private val delegate: ReadWriteProperty<SharedPrefManager, T>,
    private val isValid: (newValue: T) -> Boolean,
) : ReadWriteProperty<SharedPrefManager, T> by delegate {

    override operator fun setValue(thisRef: SharedPrefManager, property: KProperty<*>, value: T) {
        require(isValid(value)) { "$value is not valid for ${property.name}." }

        delegate.setValue(thisRef, property, value)
    }

}

class ValidatedPreferenceDelegateFactory<T>(
    private val propertyDelegateProvider: PropertyDelegateProvider<SharedPrefManager, ReadWriteProperty<SharedPrefManager, T>>,
    private val isValid: (newValue: T) -> Boolean,
) : PropertyDelegateProvider<SharedPrefManager, ReadWriteProperty<SharedPrefManager, T>> {

    override fun provideDelegate(thisRef: SharedPrefManager, property: KProperty<*>): ReadWriteProperty<SharedPrefManager, T> {
        return ValidatedPreferenceDelegate(
            delegate = propertyDelegateProvider.provideDelegate(thisRef, property),
            isValid = isValid,
        )
    }
}

fun <T : Any?> ReadWriteProperty<SharedPrefManager, T>.validate(
    isValid: (newValue: T) -> Boolean,
): ReadWriteProperty<SharedPrefManager, T> {
    return ValidatedPreferenceDelegate(this, isValid)
}

fun <T : Any?> PropertyDelegateProvider<SharedPrefManager, ReadWriteProperty<SharedPrefManager, T>>.validate(
    isValid: (newValue: T) -> Boolean,
): PropertyDelegateProvider<SharedPrefManager, ReadWriteProperty<SharedPrefManager, T>> {
    return ValidatedPreferenceDelegateFactory(this, isValid)
}
