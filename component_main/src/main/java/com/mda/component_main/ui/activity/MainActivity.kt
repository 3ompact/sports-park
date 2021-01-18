package com.mda.component_main.ui.activity

import android.content.res.Resources
import com.mda.basics_lib.utils.DrawableUtil
import com.mda.common_ui_base.base.BaseVMDBActivity
import com.mda.common_ui_base.base.BaseViewModel
import com.mda.component_main.R
import com.mda.component_main.databinding.ActivityMainCmBinding

/**
 * 首页activity
 */
class MainActivity : BaseVMDBActivity<BaseViewModel, ActivityMainCmBinding>() {
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