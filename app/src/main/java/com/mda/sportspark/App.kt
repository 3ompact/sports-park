package com.mda.sportspark

//import com.mda.basics_lib.log.LogUtil
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
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

