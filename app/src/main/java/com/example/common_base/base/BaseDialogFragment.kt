package com.example.common_base.base

import android.content.Context
import android.graphics.Insets
import android.graphics.Point
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.example.common_base.R
import io.reactivex.disposables.CompositeDisposable

abstract class BaseDialogFragment<B : ViewDataBinding>(@LayoutRes val layoutId: Int) : DialogFragment() {
    private lateinit var mBinding: B
    protected val binding get() = mBinding
    private val compositeDisposable = CompositeDisposable()
    var isInitialized = false
        private set

//    override fun getTheme() = R.style.RoundedCornersDialogTheme

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!this::mBinding.isInitialized) {
            mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        if (!isInitialized) {
            init()
            isInitialized = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.lifecycleOwner = null
    }

    override fun onResume() {
        super.onResume()
        setScreenMargin()
    }

    private fun setScreenMargin() {
        // 디바이스 size 구하기
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val windowManager = activity?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val size = windowManager.currentWindowMetricsPointCompat() // 디바이스 가로,세로 길이
        val deviceWidth = size.x // 디바이스 가로 길이
        val deviceHeight = size.y // 디바이스 가로 길이

        params?.width = (deviceWidth * 0.9).toInt() // 여백 너비 지정
//        params?.height = (deviceHeight * 0.5).toInt() // 여백 높이 지정
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    private fun WindowManager.currentWindowMetricsPointCompat(): Point {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val windowInsets = currentWindowMetrics.windowInsets
            var insets: Insets = windowInsets.getInsets(WindowInsets.Type.navigationBars())
            windowInsets.displayCutout?.run {
                insets = Insets.max(
                    insets,
                    Insets.of(safeInsetLeft, safeInsetTop, safeInsetRight, safeInsetBottom)
                )
            }
            val insetsWidth = insets.right + insets.left
            val insetsHeight = insets.top + insets.bottom
            Point(
                currentWindowMetrics.bounds.width() - insetsWidth,
                currentWindowMetrics.bounds.height() - insetsHeight
            )
        } else {
            Point().apply {
//                getSystemService<DisplayManager>()?.getDisplay(Display.DEFAULT_DISPLAY)
                defaultDisplay.getSize(this)
            }
        }
    }

    abstract fun init()


}