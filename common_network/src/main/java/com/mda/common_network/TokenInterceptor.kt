package com.mda.common_network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * tonken 拦截器 添加header
 */
class TokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
//        val response = chain.proceed(request)
//        val responseBody = response.peekBody((1024*1024).toLong())
//        val responseStr = responseBody.string()
        val request = chain.request().newBuilder()
            .addHeader("", "")
            .addHeader("", "")
            .addHeader("", "")
            .addHeader("", "")
            .build()
        return chain.proceed(request)


    }
}
