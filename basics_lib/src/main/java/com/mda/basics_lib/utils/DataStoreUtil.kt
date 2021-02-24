package com.mda.basics_lib.utils

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


/**
 * 使用官方datastore代替原有sharedpreferences
 */
class DataStoreUtil(val context: Context) {


    private val PREFERENCES_NAME = "SportParkDataStore"
    private val SHAREDPREFERENCES_NAME = "DataStore"
    val dataStore = context.createDataStore(PREFERENCES_NAME)

    /**
     * 存储数据
     */
    suspend fun saveValue(key: String, value: String) {
        dataStore.edit { it[preferencesKey<String>(key)] = value }
    }


    /**
     * 获取数据
     */
    suspend fun getValue(key: String, listener :DataStoreReadAndWirteListener) {

        dataStore.data.map { it[preferencesKey<String>(key)] ?: "" }.flowOn(Dispatchers.IO)
            .collect { value ->
                if (value.equals("")) {
                    //抛出异常(为空字符串是认为出现异常)
//                    throw Exception()
                    listener.onReadEmptyString()
                } else {
                    listener.onResult(value)

                }
            }
    }


    interface DataStoreReadAndWirteListener{
        //当读取数据为空字符串时的回调
        fun onReadEmptyString()
        //结果回调
        fun <T> onResult(t:T)
    }
}