package com.mda.common_ui_base.base

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.qmuiteam.qmui.util.QMUIStatusBarHelper

/**
 *
 * activity转场动画
 * 1.使用 overridePendingTransition(R.anim.slide_in_left,R.anim.slide_in_right)
 *
 * 2.在theme中进行设置
 *
 * <style>
 *     <item name="Android:activityOpenEnterAnimation">@anim/slide_in_left<item>
 *     <item name="Android:activityOpenExitAnimation">@anim/slide_in_out<item>
 * </style>
 *
 * 3.MD之后，可以使用
 * startActivity(intent,Activityoptions.makeSceneTransitionAnimation(MainActivitu.this.toBundle())
 *
 */

abstract class BaseVMDBActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    lateinit var mDataBinding: DB
    lateinit var mViewModel: VM

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

    open fun initDataBindAndViewModel() {
        mDataBinding = DataBindingUtil.setContentView(this, layoutId())
//            .setFitsSystemWindows(false)
        mDataBinding.lifecycleOwner = this
        mViewModel = createViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QMUIStatusBarHelper.translucent(this)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
        initDataBindAndViewModel()
//        if (actionBar())
//            supportActionBar!!.hide()
        initView()
        initData()
    }

}