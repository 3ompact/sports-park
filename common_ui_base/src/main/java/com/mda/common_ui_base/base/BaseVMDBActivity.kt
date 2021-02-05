package com.mda.common_ui_base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.launcher.ARouter
import com.qmuiteam.qmui.arch.SwipeBackLayout
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
//    var boolTrans :Boolean = false

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

//    fun setTranslucentFull(bool: Boolean){
//        this.boolTrans = bool
//    }

//    override fun setContentView(view: View?) {
//        super.setContentView(newSwipeBackLayout(view))
//    }
//
//    override fun setContentView(layoutResID: Int) {
//        super.setContentView(layoutResID)
//        val wrapper = SwipeBackLayout(this)
//        val root : View = LayoutInflater.from(this).inflate(layoutResID, null, false)
//
//        with(wrapper) {
//
//            addView(
//                root, FrameLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
//                )
//            )
////            root
//            setContentView(root)
//        }
//
//        if (boolTrans) {
//            wrapper.getContentView().setFitsSystemWindows(false)
//        } else {
//            wrapper.getContentView().setFitsSystemWindows(true)
//        }
//        super.setContentView(wrapper)
//    }

//    fun newSwipeBackLayout(view: View?):View{
//        var parent : View =  view!!.parent.
//        (view as ViewGroup).removeView(parent)
//        if (boolTrans) {
//            view!!.fitsSystemWindows = false
//        } else {
//            view!!.fitsSystemWindows = true
//        }
//        return view
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        QMUIStatusBarHelper.translucent(this)
        QMUIStatusBarHelper.setStatusBarLightMode(this)

        ARouter.getInstance().inject(this)

        initDataBindAndViewModel()
//        if (actionBar())
//            supportActionBar!!.hide()
        initView()
        initData()
    }

}