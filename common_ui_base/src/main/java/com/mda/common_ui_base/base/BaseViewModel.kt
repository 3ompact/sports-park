package com.mda.common_ui_base.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkManager
import com.mda.basics_lib.`interface`.OnResponseListener
import com.mda.basics_lib.bean.BaseResponse
import com.mda.basics_lib.utils.DataStoreUtil
import com.mda.basics_lib.work.CustomWorker
import com.mda.basics_lib.work.WorkManagerType
import com.mda.basics_lib.work.WorkerUtil
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


open class BaseViewModel : ViewModel() {
    private var isContinue: Boolean = true


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
                val one = WorkerUtil.OneTimeWork2(customWorker, tag)
                WorkManager.getInstance(application).enqueue(one)
            }
            WorkManagerType.PERIODIC -> {
                val repeat = WorkerUtil.periodicWork(customWorker, tag)
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
        return br!!.data
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
                onResponseListener.onResult(value.data)
                onResponseListener.onMsg(value.message)

            }
        }
        return job
    }


    /**
     * 存储datastore数据 （存储的数据类型为string）
     * @param context 此处的context尽量使用applicationcontext
     */
    fun <T> saveDataStore(
        context: Context,
        key:String,
        value:String
    ): Job? {

        var job = viewModelScope.launch {
            ensureActive()
            withContext(Dispatchers.IO) {
                DataStoreUtil(context).saveValue(key,value)
            }
        }
        return job
    }

    /**
     * 读取datastore数据
     */
    fun <T> getDataStore(
        context: Context,
        key:String,
        listener: DataStoreUtil.DataStoreReadAndWirteListener
    ): Job? {

        var job = viewModelScope.launch {
            ensureActive()
            withContext(Dispatchers.IO) {
                DataStoreUtil(context).getValue(key,listener)
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