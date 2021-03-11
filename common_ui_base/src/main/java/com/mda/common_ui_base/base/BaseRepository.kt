package com.mda.common_ui_base.base

import com.mda.basics_lib.bean.BaseResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

open class BaseRepository {

    /**
     * 获取远程数据
     *
     */
    fun <T> getRemote(
        block: suspend () -> BaseResponse<T>,
        error: () -> Unit,
        succes: () -> Unit,
        isContinue: Boolean = false
    ): Job {
        var f: Flow<BaseResponse<T>> = flow {
            emit(block())
        }.cancellable()

        var job = CoroutineScope(Dispatchers.Main).launch {
            ensureActive()
            f.catch { e ->
                e.message?.let { error }
            }.flowOn(Dispatchers.IO).collect { value ->
                if (!isContinue) {
                    cancel()
                }
                succes
            }
        }
        return job
    }

    /**
     * 获取本地数据
     * 如数据库等
     */
    fun getLocal() {

    }

}