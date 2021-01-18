package com.mda.common_ui_base.base

//fun <T> BaseViewModel.request(
//    block: suspend () -> BaseResp<T>,
//    success: (T) -> Unit,
//    erroe: (AppNetException) -> Unit = {},
//    isShowDialog: Boolean = false,
//    loadingMessage: String = "请求网络中..."
//): Job {
//    return viewModelScope.launch {
//        kotlin.runCatching {
//            if (isShowDialog) {
//
//            }
//            block()
//        }.onSuccess {
//            kotlin.runCatching {
//                //检查返回数据是否在正确
//                parseResponse(it) { t -> success(t) }
//
//            }.onFailure { e ->
//                Logger.e(e.message.toString())
////                erroe()
//            }
//
//        }.onFailure {
//            Logger.e(it.message.toString())
//        }
//    }
//
//}
//
//suspend fun <T> parseResponse(response: BaseResp<T>, success: suspend CoroutineScope.(T) -> Unit) {
//    coroutineScope {
//        if (response.code == 200) {
//            success(response.data)
//        } else {
//            throw AppNetException(response.code, response.msg)
//        }
//    }
//
//}