package com.mda.common_network

enum class Error(val code: Int, val err: String) {

    UNKNOW(10000, "请求失败，请稍后再试"),

    PARSE_ERROR(10001, "解析错误，请稍后再试"),

    NETWORK_ERROR(10002, "网络连接错误，请稍后重试"),

    SSL_ERROR(10003, "证书错误，请稍后再试"),

    TIMEOUT_ERROR(10004, "网络连接超时，请稍后重试");

    fun getValue(): String {
        return err
    }

    fun getCodeKey(): Int {
        return code
    }
}