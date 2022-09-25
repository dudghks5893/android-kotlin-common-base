package com.example.common_base.sharedpref.base

import com.example.common_base.sharedpref.SharedPrefManager
import kotlin.properties.PropertyDelegateProvider

interface SharedPrefKeyPropertyProvider<T> :
    PropertyDelegateProvider<SharedPrefManager, SharedPrefKeyProperty<T>>
