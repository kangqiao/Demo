package com.zp.android.arch


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.zp.android.demo.utils.LogUtil

/**
 * Create by zhaopan on 2020/7/2
 */
open abstract class BaseLazyFragment: Fragment() {


    private var isLoaded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    open fun initView(view: View) {}

    override fun onResume() {
        super.onResume()
        if (!isLoaded && !isHidden) {
            lazyInit();
            isLoaded = true
            LogUtil.d("BaseLazyFragment lazyInit...")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    abstract fun lazyInit()
}