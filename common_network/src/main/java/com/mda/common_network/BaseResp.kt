package com.mda.common_network

data class BaseResp<T>(var code: Int, var msg: String, var data: T)