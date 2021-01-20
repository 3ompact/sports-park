package com.mda.common_ui_base.base

import android.content.Intent
import androidx.databinding.ViewDataBinding
import com.qmuiteam.qmui.arch.QMUIActivity
import com.qmuiteam.qmui.util.QMUIDisplayHelper

open class BaseVDActivity<VM : BaseViewModel, DB : ViewDataBinding> : QMUIActivity(){
    lateinit var mDataBinding: DB
    lateinit var mViewModel: VM

    override fun backViewInitOffset(): Int {
        return QMUIDisplayHelper.dp2px(applicationContext, 100)
    }

    override fun onResume() {
        super.onResume()
//        QDUpgradeManager.getInstance(applicationContext).runUpgradeTipTaskIfExist(this)
    }

    override fun onLastActivityFinish(): Intent? {
        return null
    }
}