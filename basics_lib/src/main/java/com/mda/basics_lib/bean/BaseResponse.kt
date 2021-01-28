package com.mda.basics_lib.bean

data class BaseResponse<T>(var code: Int, var message: String, var data: T)
