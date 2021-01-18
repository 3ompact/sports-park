package com.mda.common_ui_base.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkManager
import com.mda.basics_lib.`interface`.OnResponseListener
import com.mda.basics_lib.bean.BaseResponse
import com.mda.basics_lib.work.CustomWorker
import com.mda.basics_lib.work.WorkManagerType
import com.mda.basics_lib.work.WorkerUtil
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


open class BaseViewModel : ViewModel() {
    var isContinue: Boolean = true


    /**
     * 设置一个后台任务
     */
    fun launchOnWorkManager(
        wmt: WorkManagerType,
        application: Application,
        customWorker: Class<CustomWorker>,
        tag: String
    ) {
        when (wmt) {
            WorkManagerType.ONETIME -> {
                var one = WorkerUtil.OneTimeWork2(customWorker, tag)
                WorkManager.getInstance(application).enqueue(one)
            }
            WorkManagerType.PERIODIC -> {
                var repeat = WorkerUtil.periodicWork(customWorker, tag)
                WorkManager.getInstance(application).enqueue(repeat)
            }
        }

    }

    /**
     * 在IO协程中进行处理任务
     */
    fun launchOnIO(block: suspend () -> Unit) {
        viewModelScope.launch { withContext(Dispatchers.IO) { block() } }
    }

    fun <T : BaseResponse<T>> req(block: suspend () -> Deferred<BaseResponse<T>>): T {
        var br: BaseResponse<T>? = null
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                br = block().await()
            }
        }
        return br!!.result
    }

    /**
     * 数据请求并调用相应的接口,可以取消
     */
    fun <T> launchRep(
        block: suspend () -> BaseResponse<T>,
        onResponseListener: OnResponseListener<T>
    ): Job? {

        var f: Flow<BaseResponse<T>> = flow {
            emit(block())
        }.cancellable()

        var job = viewModelScope.launch {
            ensureActive()
            f.catch { e ->
                e.message?.let { onResponseListener.onError(it) }
            }.flowOn(Dispatchers.IO).collect { value ->
                if (!isContinue) {
                    cancel()
                }
                onResponseListener.onResult(value.result)
            }
        }
        return job
    }


    /**
     * 数据请求并调用相应的接口,可以取消
     */
    fun <T> launchRep(
        block: suspend () -> BaseResponse<T>,
        error: () -> Unit,
        succes: () -> Unit
    ): Job? {

        var f: Flow<BaseResponse<T>> = flow {
            emit(block())
        }.cancellable()

        var job = viewModelScope.launch {
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

}