package com.example.common_base.loading.internal


import android.content.Context
import android.util.Log
import com.example.common_base.loading.LoadingAnimation
import com.example.common_base.loading.LoadingOverlay
import kotlinx.coroutines.*
import kotlin.time.DurationUnit

internal class LoadingOverlayImpl(
    context: Context,
    animation: LoadingAnimation,
    dimAmount: Float,
    isCancellable: Boolean,
    onShowListener: (() -> Unit)?,
    onCancelListener: (() -> Unit)?,
    onDismissListener: (() -> Unit)?
) : LoadingOverlay {
    private val externalScope: CoroutineScope? by lazy { GlobalScope }
    private val defaultDispatcher: CoroutineDispatcher? by lazy { Dispatchers.Main }
    private val overlayDialog: LoadingOverlayDialog by lazy {
        LoadingOverlayDialog(
            context,
            animation.rawRes,
            animation.dimens,
            dimAmount,
            isCancellable,
            onShowListener,
            onCancelListener,
            onDismissListener
        )
    }

    override fun show() {
        try {
            if (defaultDispatcher != null) externalScope?.launch(defaultDispatcher!!) { overlayDialog.show() }
        } catch (e: Exception) {
            Log.e("error","$e")
        }
    }

    override fun showFor(period: Long, unit: DurationUnit, action: (() -> Unit)?) {
        if (defaultDispatcher != null) externalScope?.launch(defaultDispatcher!!) { overlayDialog.show() }
        runAfter(period, unit) {
            dismiss()
            action?.invoke()
        }
    }

    override fun dismiss() {
        try {
            if (defaultDispatcher != null) externalScope?.launch(defaultDispatcher!!) { overlayDialog.dismiss() }
        } catch (e : Exception) {
            Log.e("error","$e")
        }
    }

    override fun isShowing() = overlayDialog.isShowing

}