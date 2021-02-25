package com.mda.sportspark

//import com.mda.basics_lib.log.LogUtil
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.mda.basics_lib.broadcast.NetworkCallbackImp
import com.mda.basics_lib.toast.ToastUtil
import com.mda.common_network.NetCheckUtil
import com.mda.component_main.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager
import leakcanary.LeakCanary


class App : Application() {


    override fun onCreate() {
        super.onCreate()
        //log初始化
        Logger.addLogAdapter(AndroidLogAdapter())

        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
        LeakCanary.newLeakDisplayActivityIntent()
        QMUISwipeBackActivityManager.init(this)
        //toast初始化
        ToastUtil.init(applicationContext)


        //注册网络状态监听
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            NetCheckUtil(applicationContext).register(NetworkRequest.Builder().build(), NetworkCallbackImp(applicationContext))
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        //Multidex初始化
        MultiDex.install(this)

    }

    private fun getMetaChannel(): String {
        var appInfo =
            packageManager.getApplicationInfo(this.packageName, PackageManager.GET_META_DATA)
        var value = appInfo.metaData.getString("NAME_CHANNEL")
        return value!!
    }
}

