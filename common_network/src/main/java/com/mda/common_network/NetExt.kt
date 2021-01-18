package com.mda.common_network

import java.lang.reflect.ParameterizedType

fun <T> BaseResp<T>.dataRevert(): T {
    if (code == 200)
        return data
    throw AppNetException(code,msg)
}

fun <T> getVMClazz(any: Any) :T {
    return (any.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as T
}
