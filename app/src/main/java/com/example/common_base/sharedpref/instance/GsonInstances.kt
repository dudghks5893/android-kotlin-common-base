package com.example.common_base.sharedpref.instance

import com.google.gson.Gson
import com.example.common_base.sharedpref.SharedPrefManager


internal val defaultGson = Gson()

internal val gsonInstances: MutableMap<SharedPrefManager, Gson> = mutableMapOf<SharedPrefManager, Gson>().withDefault { defaultGson }

internal inline val SharedPrefManager.internalGson: Gson
    get() = gsonInstances.getValue(this)

 var SharedPrefManager.gson: Gson?
    set(value) {
        if (value != null) {
            gsonInstances[this] = value
        } else {
            gsonInstances.remove(this)
        }
    }
    get() {
        return gsonInstances[this]
    }