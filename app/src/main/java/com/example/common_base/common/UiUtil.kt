package com.example.common_base.common

import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.databinding.BindingAdapter

object UiUtil {

    @BindingAdapter("layoutMarginTop")
    @JvmStatic fun setLayoutMarginTop(view: View, dimen: Float) {
        view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            topMargin = dimen.toInt()
        }
    }

    @BindingAdapter("layoutMarginStart")
    @JvmStatic fun setLayoutMarginStart(view: View, dimen: Float) {
        view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            marginStart = dimen.toInt()
        }
    }
}