package com.example.common_base.base

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import android.view.WindowManager
import androidx.annotation.StringRes
import com.example.common_base.base.BaseApplication.Companion.applicationContext
import com.example.common_base.base.BaseApplication.Companion.resources
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

object BaseResources {
    private val externalScope: CoroutineScope by lazy { GlobalScope }
    private val defaultDispatcher: CoroutineDispatcher by lazy { Dispatchers.Main }

    fun getStringById(@StringRes resId: Int): String {
        return resources().getString(resId)
    }

    fun getStringByName(name: String, defaultStr: String? = ""): String? {
        val resId: Int? = resources().getIdentifier(
            name,
            "string",
            applicationContext().packageName
        )
        return if (resId == 0) defaultStr else resources().getString(resId!!)
    }

    // display 너비 (deprecated 대응)
    private fun getDisplayWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = wm.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val displayMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }

    // 토스트 레이아웃
//    private fun toastBinding(): LayoutToastBinding {
//        val inflater = LayoutInflater.from(applicationContext())
//        val binding: LayoutToastBinding =
//            DataBindingUtil.inflate(inflater, R.layout.layout_toast, null, false)
//        binding.tvMessage.width = getDisplayWidth(applicationContext())
//        return binding
//    }

//    fun showToast(message: String) =
//        externalScope.launch(defaultDispatcher) {
//            val binding = toastBinding().apply {
//                tvMessage.text = message
//            }
//            Toast(applicationContext()).apply {
//                duration = Toast.LENGTH_LONG
//                view = binding.root
//            }.show()
//        }
//
//    fun showToast(resId: Int) =
//        externalScope.launch(defaultDispatcher) {
//            val binding = toastBinding().apply {
//                tvMessage.text = getStringById(resId)
//            }
//            Toast(applicationContext()).apply {
//                duration = Toast.LENGTH_LONG
//                view = binding.root
//            }.show()
//        }
//
//    fun showToast(message: String, icon: Int) =
//        externalScope.launch(defaultDispatcher) {
//            val binding = toastBinding().apply {
//                tvMessage.text = message
//                tvMessage.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
//            }
//            Toast(applicationContext()).apply {
//                duration = Toast.LENGTH_LONG
//                view = binding.root
//            }.show()
//        }
//
//    fun showToast(resId: Int, icon: Int) =
//        externalScope.launch(defaultDispatcher) {
//            val binding = toastBinding().apply {
//                tvMessage.text = getStringById(resId)
//                tvMessage.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
//            }
//            Toast(applicationContext()).apply {
//                duration = Toast.LENGTH_LONG
//                view = binding.root
//            }.show()
//        }
//
//    fun showToast(message: String, icon: Int, placeholder: String) =
//        externalScope.launch(defaultDispatcher) {
//            val binding = toastBinding().apply {
//                tvMessage.text = message
//                tvMessage.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
//                tvPlaceholder.visibility = View.VISIBLE
//                tvPlaceholder.text = placeholder
//            }
//            Toast(applicationContext()).apply {
//                duration = Toast.LENGTH_LONG
//                view = binding.root
//            }.show()
//        }
//
//    fun showToast(resId: Int, icon: Int, placeholder: String) =
//        externalScope.launch(defaultDispatcher) {
//            val binding = toastBinding().apply {
//                tvMessage.text = getStringById(resId)
//                tvMessage.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
//                tvPlaceholder.visibility = View.VISIBLE
//                tvPlaceholder.text = placeholder
//            }
//            Toast(applicationContext()).apply {
//                duration = Toast.LENGTH_LONG
//                view = binding.root
//            }.show()
//        }
//
//    fun showToast(message: String, icon: Int, placeholder: Int) =
//        externalScope.launch(defaultDispatcher) {
//            val binding = toastBinding().apply {
//                tvMessage.text = message
//                tvMessage.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
//                tvPlaceholder.visibility = View.VISIBLE
//                tvPlaceholder.text = getStringById(placeholder)
//            }
//            Toast(applicationContext()).apply {
//                duration = Toast.LENGTH_LONG
//                view = binding.root
//            }.show()
//        }
//
//    fun showToast(resId: Int, icon: Int, placeholder: Int) =
//        externalScope.launch(defaultDispatcher) {
//            val binding = toastBinding().apply {
//                tvMessage.text = getStringById(resId)
//                tvMessage.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
//                tvPlaceholder.visibility = View.VISIBLE
//                tvPlaceholder.text = getStringById(placeholder)
//            }
//            Toast(applicationContext()).apply {
//                duration = Toast.LENGTH_LONG
//                view = binding.root
//            }.show()
//        }

}

