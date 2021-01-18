package com.mda.common_network

import com.mda.common_network.api.ManApiService


/**
 * 请求封装类
 */
class Req {

    companion object {


        fun getRequestUtil(): ManApiService {
            return  RequestUtil.INSTANCE
        }

        /**
         * @param clazz retrofit 的接口类
         */
        fun <T> getRequestUtilInstanceTest(clazz: Class<T>): T? {
            return  RequestUtil.getInstance(clazz)
        }
    }


}