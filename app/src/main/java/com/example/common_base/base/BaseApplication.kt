package com.example.common_base.base

import android.app.Activity
import android.app.Application
import android.content.res.Resources
import android.os.Bundle
import com.example.common_base.loading.LoadingAnimation
import com.example.common_base.loading.LoadingOverlay
import com.example.common_base.sharedpref.SharedPrefManager

/**
 * Application
 **/
class BaseApplication : Application(), Application.ActivityLifecycleCallbacks {
    var isActive = false

    companion object {
        private var sharedPrefManager: SharedPrefManager? = null
        private var baseApplication: BaseApplication? = null
        private var baseResources: Resources? = null
        var loadingOverlay: LoadingOverlay? = null

        fun getSharedPrefManager(): SharedPrefManager? {
            return sharedPrefManager
        }

        fun applicationContext(): BaseApplication {
            return baseApplication as BaseApplication
        }

        fun resources(): Resources {
            return baseResources as Resources
        }
    }

    override fun onCreate() {
        super.onCreate()

        baseApplication = this
        baseResources = resources
        sharedPrefManager = SharedPrefManager()

        // 앱 백그라운드 상태 설정확인
        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
                loadingOverlay =
                    LoadingOverlay.with(
                        context = activity,
                        animation = LoadingAnimation.FADING_PROGRESS
                    )
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }
        })
    }

    override fun onTerminate() {
        super.onTerminate()
        getSharedPrefManager()?.isLogined = false
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        //TODO("Not yet implemented")
    }

    override fun onActivityStarted(activity: Activity) {
        //TODO("Not yet implemented")
    }

    override fun onActivityResumed(activity: Activity) {
        //TODO("Not yet implemented")
        isActive = true
    }

    override fun onActivityPaused(activity: Activity) {
        //TODO("Not yet implemented")
        isActive = false
    }

    override fun onActivityStopped(activity: Activity) {
        //TODO("Not yet implemented")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        //TODO("Not yet implemented")
    }

    override fun onActivityDestroyed(activity: Activity) {
        //TODO("Not yet implemented")
    }
}