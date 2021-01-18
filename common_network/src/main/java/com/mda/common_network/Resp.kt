package com.mda.common_network

import com.mda.basics_lib.`interface`.OnResponseListener
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


/**
 *
 * Emitter 发射器 在发射器中会接收下游的订阅者，然后在发射器相应的方法吧数据传给订阅者。
 *
 * Consumer 俗称消费器 其实是observer的一种变体，Observer每个方法都会对应一个consumer，比如observer的onNext onError onComplete onSubscribe 都会对应一个consumer
 *
 * Disposable 释放器，通常有两种返回方式，一个是在observer的onSubscribe方法回调来，第二是在subscribe订阅方法传回consumer的时候返回
 *
 *
 *
 */

class Resp {
    private var isContinue: Boolean = true


    fun setIsContinue(contin: Boolean) {
        isContinue = contin
    }


    fun <T> launchRep(
        block: suspend () -> BaseResp<T>,
        onResponseListener: OnResponseListener<T>,
        coroutineScope: CoroutineScope
    ): Job? {

        var f: Flow<BaseResp<T>> = flow {
            emit(block())
        }.cancellable()

        var job = coroutineScope.launch {
            ensureActive()
            f.catch { e ->
                e.message?.let { onResponseListener.onException(it) }
            }.flowOn(Dispatchers.IO).collect { value ->
                if (!isContinue) {
                    cancel()
                }
                try {
                    onResponseListener.onResult(value.dataRevert())
                } catch (e: Exception) {
                    e.message?.let { onResponseListener.onError(it) }
                }
            }
        }
        return job
    }


    fun <T> launchReq( block:  suspend () ->  BaseResp<T>) {

    }


}