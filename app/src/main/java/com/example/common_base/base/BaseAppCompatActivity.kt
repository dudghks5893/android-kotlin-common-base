package com.example.common_base.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable

/**
 * View Data binding Base Activity
 **/
abstract class BaseAppCompatActivity<B : ViewDataBinding>(
    @LayoutRes open val layoutId: Int
) : AppCompatActivity() {
    lateinit var binding: B
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)

        // lifecycleOwner 지정
        binding.lifecycleOwner = this
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}