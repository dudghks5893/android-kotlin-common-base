package com.example.common_base.sharedpref

import android.content.Context
import android.content.SharedPreferences
import com.example.common_base.base.BaseApplication.Companion.applicationContext
import com.example.common_base.sharedpref.default.withDefault
import com.example.common_base.common.AppConstants

/**
 * SharedPreference
 **/
class SharedPrefManager  {

    val sharedPreferences: SharedPreferences =
        applicationContext().getSharedPreferences(AppConstants.APP_PREF_NAME, Context.MODE_PRIVATE)

    var isLogined by booleanPref().withDefault(false)

    
     var exampleInt by intPref().withDefault(0)
     var exampleLong by longPref().withDefault(0L)
     var exampleString by stringPref().withDefault("")
     var exampleStringSet by stringSetPref().withDefault(setOf())
//     var exampleUserGson by gsonPref<User>().withDefault(User("Gson", "Green"))


}