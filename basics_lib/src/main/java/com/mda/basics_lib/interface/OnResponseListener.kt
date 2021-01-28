package com.mda.basics_lib.`interface`

/**
 * 请求回调接口
 */
interface OnResponseListener<T> {
    fun onResult(t: T)
    fun onError(msg: String)
    fun onException(msg: String)
    fun onMsg(msg: String)
}