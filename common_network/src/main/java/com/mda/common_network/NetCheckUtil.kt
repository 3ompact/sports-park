package com.mda.common_network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi


/**
 *
 * 网络工具类
 */
class NetCheckUtil(context: Context) {

    val connectivityManager : ConnectivityManager by lazy {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }



    /**
     *  注册网络监听器
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun register(request: NetworkRequest, networkCallback: ConnectivityManager.NetworkCallback){
        connectivityManager.registerNetworkCallback(request,networkCallback)
    }
    /**
     *  注销网络监听器
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun unregister(networkCallback: ConnectivityManager.NetworkCallback){
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}