package com.mda.common_ui_base.base

import com.mda.basics_lib.bean.BaseResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 *
 * 基础repository 用于请求本地数据和远程数据
 *
 *
 */
open class BaseRepository {

    /**
     * 获取远程数据
     * @param block coroutine 挂起函数
     * @param error  异常回调
     * @param succes 成功回调
     * @param isContinue 可取消
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