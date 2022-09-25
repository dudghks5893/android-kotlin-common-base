package com.example.common_base.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import kotlinx.coroutines.*

abstract class BaseFragment<B : ViewDataBinding>(
    @LayoutRes val layoutId: Int
) : Fragment() {
    private lateinit var mBinding: B
    protected val binding get() = mBinding
    private val externalScope: CoroutineScope = GlobalScope
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Main
    
    var isInitialized = false
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        Log.d("Fragment", "Fragment onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.lifecycleOwner = null
        isInitialized = false
        Log.d("Fragment", "Fragment onDestroyView")
    }

    fun NavController.safeNavigate(direction: NavDirections, extra: Bundle? = null) {
        externalScope.launch(defaultDispatcher) {
            currentDestination?.getAction(direction.actionId)?.run {
                navigate(direction.actionId, extra)
            }
        }
    }

    fun NavController.safeNavigate(resId: Int, extra: Bundle? = null) {
        externalScope.launch(defaultDispatcher) {
            navigate(resId, extra)
        }
    }

    fun NavController.safePopBackStack() {
        externalScope.launch(defaultDispatcher) {
            popBackStack()
        }
    }

    abstract fun init()
}