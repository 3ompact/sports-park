package com.mda.common_network

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class OkHttpSingleton {
    private var timeout: Long = 120L

    private  var okHttpClient: OkHttpClient

    private constructor(){
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .addInterceptor(LogInterceptor())
            .build()
    }


    companion object{
        val instance = Holder.holder
        val okhttpInstance = Holder.holder.okHttpClient
    }
    object Holder{
        val holder = OkHttpSingleton()
    }
}