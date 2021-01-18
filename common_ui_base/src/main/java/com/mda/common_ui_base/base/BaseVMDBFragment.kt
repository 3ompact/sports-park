package com.mda.common_ui_base.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider

abstract class BaseVMDBFragment<VM : BaseViewModel, DB : ViewDataBinding> : androidx.fragment.app.Fragment() {

    lateinit var mDataBinding: DB
    private var isFirst: Boolean = true
    lateinit var mViewModel :VM
    lateinit var mActivity : AppCompatActivity

    abstract fun layoutId():Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDataBinding = DataBindingUtil.inflate(inflater,layoutId(),container,false)
        mDataBinding.lifecycleOwner = this
        return mDataBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFirst = true
        mViewModel = createViewModel()
        initView(savedInstanceState)
        onVisible()
        initData()
    }


    private fun createViewModel():VM{
        return ViewModelProvider(this).get(getVMClazz(this))
    }

    //初始化view
    abstract fun initView(savedInstanceState: Bundle?)

    //懒加载
    abstract fun lazyLoadData()

    override fun onResume() {
        super.onResume()
        onVisible()
    }
    /**
     * 是否需要懒加载
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            //延迟加载0.12秒加载 避免fragment跳转动画和渲染ui同时进行，出现些微的小卡顿
            view?.postDelayed({
                lazyLoadData()

                isFirst = false
            }, 120)
        }
    }

    open fun initData(){

    }

    /**
     * loading展示
     */
    abstract fun showLoading(message: String = "请求网络中...")

    /**
     * 取消loading
     */
    abstract fun dismissLoading()

}