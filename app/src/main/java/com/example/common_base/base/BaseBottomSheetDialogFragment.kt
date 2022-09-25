package com.example.common_base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseBottomSheetDialogFragment<B : ViewDataBinding>(@LayoutRes val layoutId: Int) : BottomSheetDialogFragment() {
    private lateinit var mBinding: B
    protected val binding get() = mBinding
    protected val compositeDisposable = CompositeDisposable()
    var isInitialized = false
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!this::mBinding.isInitialized) {
            mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
//            mBinding.setVariable(BR.bottomSheet, this)
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

    abstract fun init()

}