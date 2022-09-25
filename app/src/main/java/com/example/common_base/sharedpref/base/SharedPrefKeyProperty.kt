package com.example.common_base.sharedpref.base

import com.example.common_base.sharedpref.SharedPrefManager
import kotlin.properties.ReadWriteProperty

interface SharedPrefKeyProperty<T> : ReadWriteProperty<SharedPrefManager, T> {
    val key: String
}
