package com.mda.common_network

/**
 * 网络状态封装类
 */
sealed class LoadState(val msg: String) {
    class Loading(msg: String = "") : LoadState(msg)
    class Success(msg: String = "") : LoadState(msg)
    class Fail(msg: String) : LoadState(msg)
}
