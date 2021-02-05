package com.mda.basics_lib.broadcast

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * 网络状态注册监听
 *  * connectivtymanager.registerNetworkCallback(new networkRequest.build.build,networkCallback)

 *
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class NetworkCallbackImp : ConnectivityManager.NetworkCallback() {
    private val TAG = "NetworkCallbackImpl"


    //网络连接
    override fun onAvailable(network: Network) {
        super.onAvailable(network)
    }
    //网络断开
    override fun onLost(network: Network) {
        super.onLost(network)

    }

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                //wifi已经连接
            } else {
                //移动网络=已经连接
            }
        }
    }

    /**
     * 网络状态回调接口
     *
     */
    interface NetworkStatusListenser{

        /**
         * 断开
         */
        fun onLost()

        /**
         * 已连接
         */
        fun onAvailable()

        /**
         * wifi已连接
         */
        fun onConnectWIFI()

        /**
         * 连接移动网络
         */
        fun onConnectIntener()
    }
}