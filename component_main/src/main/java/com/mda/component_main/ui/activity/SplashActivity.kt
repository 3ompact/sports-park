package com.mda.component_main.ui.activity

import android.os.Handler
import android.os.Message
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.mda.common_ui_base.base.BaseVMDBActivity
import com.mda.common_ui_base.ui.widget.QRScanView
import com.mda.component_main.R
import com.mda.component_main.databinding.ActivitySplashCmBinding
import com.mda.component_main.viewmodel.SplashActivityModel

/**
 * 用户启动app时的首个界面
 */
@Route(path = "/main/splashactivity")
class SplashActivity : BaseVMDBActivity<SplashActivityModel, ActivitySplashCmBinding>() {

    var qr : QRScanView? = null
    override fun layoutId(): Int {
        return R.layout.activity_splash_cm
    }

    override fun actionBar(): Boolean {
        return true
    }

    override fun initView() {






//        TODO("Not yet implemented")
        qr = mDataBinding.test1
//        lifecycle.addObserver(qr!!)

//        mViewModel.getImageUrl(object :OnResponseListener<List<Test>>{
//            override fun onResult(t: List<Test>) {
//                mDataBinding.ivSplashActivity.load(t.get(0).header)
//                Log.i("3ompact","success")
//
//            }
//
//            override fun onError(msg: String) {
//
//            }
//
//            override fun onException(msg: String) {
//
//            }
//        })
//        mDataBinding.ivSplashActivity.load(s)
    }

    override fun initData() {
//        TODO("Not yet implemented")
    }

    override fun showLoading(message: String) {
//        TODO("Not yet implemented")
    }

    override fun dismissLoading() {
//        TODO("Not yet implemented")
    }

    override fun createObserver() {
//        TODO("Not yet implemented")
    }


    override fun onStop() {
        super.onStop()
        qr!!.stop()
        Log.i("3ompact", "stop")

    }

    override fun onDestroy() {
        super.onDestroy()
//        qr = null
        Log.i("3ompact", "onDestroy")

    }
}