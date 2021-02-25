package com.mda.basics_lib.broadcast

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.mda.basics_lib.log.LogUtil
import com.mda.basics_lib.toast.ToastUtil
import com.mda.basics_lib.utils.DataStoreUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 网络状态注册监听
 *  * connectivtymanager.registerNetworkCallback(new networkRequest.build.build,networkCallback)

 *
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class NetworkCallbackImp (val context: Context) : ConnectivityManager.NetworkCallback() {
    private val TAG = "NetworkCallbackImpl"


    //网络连接
    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        CoroutineScope(Dispatchers.IO).launch {
            DataStoreUtil(context).saveValue(DataStoreUtil.TYPE.NETSTATUS.type,DataStoreUtil.NETSTATUS.AVAILABLE.typeStatus)
        }
//        CoroutineScope(Dispatchers.Main).launch {
//            DataStoreUtil(context).getValue(DataStoreUtil.TYPE.NETSTATUS.type,object :
//                DataStoreUtil.DataStoreReadAndWirteListener{
//                override fun onReadEmptyString() {
////                    TODO("Not yet implemented")
//                }
//
//                override fun <T> onResult(t: T) {
////                    TODO("Not yet implemented")
//                    ToastUtil.showLengthShort(t as String)
//                }
//            })
//        }
    }

    //网络断开
    override fun onLost(network: Network) {
        super.onLost(network)
        CoroutineScope(Dispatchers.IO).launch {
            DataStoreUtil(context).saveValue(DataStoreUtil.TYPE.NETSTATUS.type,DataStoreUtil.NETSTATUS.LOST.typeStatus)
        }
    }

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                //wifi已经连接
                CoroutineScope(Dispatchers.IO).launch {
                    DataStoreUtil(context).saveValue(DataStoreUtil.TYPE.NETTYPE.type,DataStoreUtil.NETSTATUS.WIFI.typeStatus)
                }
            } else {
                //移动网络=已经连接
                CoroutineScope(Dispatchers.IO).launch {
                    DataStoreUtil(context).saveValue(DataStoreUtil.TYPE.NETTYPE.type,DataStoreUtil.NETSTATUS.CELLULAR.typeStatus)
                }
            }
        }
    }



    /**
     * 网络状态回调接口
     *
     */
    interface NetworkStatusListenser {

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