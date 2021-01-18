package com.mda.component_main.ui.activity

import android.util.Log
import android.widget.TextView
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.callback.NavCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.mda.common_ui_base.base.BaseVMDBActivity
import com.mda.common_ui_base.base.BaseViewModel
import com.mda.component_main.R
import com.mda.component_main.databinding.ActivityDBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


@Route(path = "/b/testactivity")
class Test1Activity: BaseVMDBActivity<BaseViewModel, ActivityDBinding>() {


    override fun layoutId(): Int {
        return R.layout.activity_d
    }

    override fun actionBar(): Boolean {
        return true
    }

    override fun initView() {
//        mDatabinding.ivAdActivityAd.load(R.drawable.ic_launcher_background)
//        mDatabinding.ivAdActivityAd.setOnClickListener {
//            startActivity(Intent(this,HomeActivity::class.java))
//        }

        ARouter.getInstance().inject(this)

        mDataBinding.tvJumpActivityD.setOnClickListener {

            ARouter.getInstance()
                .build("/a/testactivity")
                .navigation(this)
            (it as TextView).setText("ss"+mViewModel)

        }

    }

    override fun initData() {

        var v = MainScope()
//        v.launch {
//            var i = 0
//            withContext(Dispatchers.IO) {
//                while (i < 60) {
//                    delay(1000L)
//                    i++
//                    Log.i("3ompact", i.toString())
//                    withContext(Dispatchers.Main) {
//                        mDatabinding.tvJumpActivityAd.setText(i.toString() + "s")
//
//                    }
//                }
//            }
//
//        }

        //请求广告或是活动imageurl

//        GlobalScope.launch {
//            var g1 = GlobalScope.async {
//              var d =  NetUtil.apiService.getAdUri()
//
//                var ss :BaseResp<String> =  d.await()
//
//            }
//            g1.await()
//        }

//        CoroutineScope(Dispatchers.Main).launch {
//            Log.i("3ompact","coroutinescope "+Thread.currentThread().name)
//
//            async() {
//                Log.i("3ompact","async1 "+Thread.currentThread().name+System.currentTimeMillis())
//
//            }
//
//            async() {
//                Log.i("3ompact","async2 "+Thread.currentThread().name+System.currentTimeMillis())
//
//            }
//            withContext(Dispatchers.IO){
//                Log.i("3ompact","withContext "+Thread.currentThread().name)
//
//            }
//
//        }

//        CoroutineScope(Dispatchers.Main).launch{
//            flow {
//                emit(1)
//                Log.i("3ompact","emit"+Thread.currentThread().name)
//                withContext(Dispatchers.IO){
//                    Log.i("3ompact","IO"+Thread.currentThread().name)
//
//                }
//
//            }.flowOn(Dispatchers.IO).collect { value ->
//                Log.i("3ompact","collect"+Thread.currentThread().name)
//                Log.i("3ompact","collect"+Thread.currentThread().name)
//
//            }
//        }

        var i = 0
        var j = CoroutineScope(Dispatchers.Main).launch {
            channelFlow<Int> {
                send(1)
            }.flowOn(Dispatchers.IO).collect { value ->
                i = value
            }
        }

        Log.i("3ompact", "CoroutineScope" + i)
        j.cancel()

//
//        sequence { // 序列构建器
//            for (i in 1..3) {
//                Thread.sleep(100) // 假装我们正在计算
//                yield(i) // 产生下一个值
//                Log.i("3ompact","ssss"+Thread.currentThread().name)
//            }
//        }
//            .forEach {
//            Log.i("3ompact","sequence"+Thread.currentThread().name)
//
//        }


    }

    override fun showLoading(message: String) {

    }

    override fun dismissLoading() {

    }

    override fun createObserver() {

    }
}

