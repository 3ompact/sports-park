package com.mda.component_main.ui.activity

import android.content.res.Resources
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.alibaba.android.arouter.launcher.ARouter
import com.mda.basics_lib.utils.DrawableUtil
import com.mda.common_ui_base.base.BaseVMDBActivity
import com.mda.common_ui_base.base.BaseViewModel
import com.mda.component_main.R
import com.mda.component_main.databinding.ActivityMainCmBinding

/**
 * 首页activity
 */
class MainActivity : BaseVMDBActivity<BaseViewModel, ActivityMainCmBinding>() {

    lateinit var container :FrameLayout
    override fun layoutId(): Int {
        return R.layout.activity_main_cm
    }

    override fun actionBar(): Boolean {
        return true
    }

    override fun initView() {
        DrawableUtil.setRBDrawableBounds(mDataBinding.rbHomeCmMainActivity,resources.getDrawable(R.drawable.selector_icon_home))
        DrawableUtil.setRBDrawableBounds(mDataBinding.rbSupermarketCmMainActivity,resources.getDrawable(R.drawable.selector_icon_supermarket))
        DrawableUtil.setRBDrawableBounds(mDataBinding.rbMineCmMainActivity,resources.getDrawable(R.drawable.selector_icon_mine))
        container = mDataBinding.flCmMainActivity
        var fragment =  ARouter.getInstance().build("/cm/homefragment").navigation() as Fragment
        var fm = supportFragmentManager
        var ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.fl_cm_main_activity,fragment)
        ft.commit()

    }

    override fun initData() {
    }

    override fun showLoading(message: String) {
    }

    override fun dismissLoading() {
    }

    override fun createObserver() {
    }
}