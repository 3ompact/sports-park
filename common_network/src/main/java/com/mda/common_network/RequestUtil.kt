package com.mda.common_network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mda.common_network.api.ManApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * 请求工具类
 */
class RequestUtil<T> {
    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ManApiService
//    private  lateinit var okHttpClient: OkHttpClient

    private var s: T? = null

//    private var timeout: Long = 120L

    private val baseUrl: String = "https://api.apiopen.top/"


    private constructor(okHttpClient: OkHttpClient) {
//        this.okHttpClient = okHttpClient
        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        apiService = retrofit.create(ManApiService::class.java)
    }

    private constructor(clazz: Class<T>,okHttpClient: OkHttpClient) {
//        this.okHttpClient = okHttpClient

        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        s = retrofit.create(clazz)

    }

    private object Holder {
        val instance = RequestUtil<ManApiService>(OkHttpSingleton.okhttpInstance).apiService

        fun <T> getInstance(clazz: Class<T>): RequestUtil<T> {
            return RequestUtil(clazz,OkHttpSingleton.okhttpInstance)
        }

    }

    companion object {

        val INSTANCE = Holder.instance
//        val INSTANCES = Holder.instance.apiService

        @JvmStatic
        fun <T> getInstance(clazz: Class<T>): T? {
            return Holder.getInstance(clazz).s
        }


    }


}