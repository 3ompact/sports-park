package com.mda.common_ui_base.base

import java.lang.reflect.ParameterizedType

fun <VM> getVMClazz(any: Any) :VM {
    return (any.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}
