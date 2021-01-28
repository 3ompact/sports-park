package com.mda.component_main.ui.activity

import android.Manifest
import android.content.res.Resources
import android.util.Log
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.alibaba.android.arouter.launcher.ARouter
import com.mda.basics_lib.`interface`.OnResponseListener
import com.mda.basics_lib.permission.PermissionUtil
import com.mda.basics_lib.utils.DrawableUtil
import com.mda.common_network.Req
import com.mda.common_ui_base.base.BaseVMDBActivity
import com.mda.common_ui_base.base.BaseViewModel
import com.mda.component_main.ManApiS
import com.mda.component_main.R
import com.mda.component_main.databinding.ActivityMainCmBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 首页activity
 */
class CMMainActivity : BaseVMDBActivity<BaseViewModel, ActivityMainCmBinding>() {

    lateinit var container: FrameLayout
    override fun layoutId(): Int {
        return R.layout.activity_main_cm
    }

    override fun actionBar(): Boolean {
        return true
    }

    override fun initView() {

        PermissionUtil().requestSinglePermission(
            this,
            Manifest.permission.INTERNET,
            object : PermissionUtil.PermissionListener {
                override fun onGranted() {
                    Log.i("3ompact", "onGranted")
                }

                override fun onDenied() {
                    Log.i("3ompact", "onDenied")
                }
            })
        DrawableUtil.setRBDrawableBounds(
            mDataBinding.rbHomeCmMainActivity,
            resources.getDrawable(R.drawable.selector_icon_home)
        )
        DrawableUtil.setRBDrawableBounds(
            mDataBinding.rbSupermarketCmMainActivity,
            resources.getDrawable(R.drawable.selector_icon_supermarket)
        )
        DrawableUtil.setRBDrawableBounds(
            mDataBinding.rbMineCmMainActivity,
            resources.getDrawable(R.drawable.selector_icon_mine)
        )
        container = mDataBinding.flCmMainActivity
        var fragment = ARouter.getInstance().build("/cm/homefragment").navigation() as Fragment
        var fm = supportFragmentManager
        var ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.fl_cm_main_activity, fragment)
        ft.commit()

        var v = MainScope()
        v.launch {
            var i = 0
//            withContext(Dispatchers.IO) {
            mViewModel.launchRep({
                Req.getRequestUtilInstanceTest(ManApiS::class.java)!!.getTest()
            }, object : OnResponseListener<List<String>> {
                override fun onResult(t: List<String>) {
//                        TODO("Not yet implemented")
                }

                override fun onError(msg: String) {
//                        TODO("Not yet implemented")
                }

                override fun onException(msg: String) {
//                        TODO("Not yet implemented")
                }

                override fun onMsg(msg: String) {
//                        TODO("Not yet implemented")
                    Log.i("3ompact", "测试接口" + msg)
                }
            })
//            }

        }


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