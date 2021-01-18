package com.mda.common_ui_base.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.*
import com.mda.common_ui_base.ui.widget.QRScanView

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : Activity(),LifecycleOwner,
    ViewModelStoreOwner {

    lateinit var mDataBinding: DB
    lateinit var mViewModel: VM
    lateinit var lifecycleRegistry: LifecycleRegistry

    /**
     * 布局资源id
     */
    abstract fun layoutId(): Int

    /**
     * 是否隐藏actionbar
     */
    abstract fun actionBar(): Boolean

    /**
     * 初始化view
     */
    abstract fun initView()

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 消息显示
     */
    abstract fun showLoading(message: String = "请求网络中")

    /**
     * 取消消息显示
     */
    abstract fun dismissLoading()

    /**
     * 网络变化监听
     */
    open fun onNetworkStateChanged() {}

    /**
     * 创建ViewModel
     */
    fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVMClazz(this))
    }

    abstract fun createObserver()

    open fun initDataBind() {
        mDataBinding = DataBindingUtil.setContentView(this, layoutId())
        mDataBinding.lifecycleOwner = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBind()
        if (actionBar())
            actionBar!!.hide()

        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
//        lifecycle.addObserver()
        initView()
        initData()
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.currentState = Lifecycle.State.STARTED

    }



    override fun getLifecycle(): Lifecycle {

        return lifecycleRegistry
    }

    override fun getViewModelStore(): ViewModelStore {
        return ViewModelStore()
    }


}