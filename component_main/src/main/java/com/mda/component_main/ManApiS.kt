package com.mda.component_main

import com.mda.basics_lib.bean.BaseResponse
import com.mda.common_network.bean.Test
import retrofit2.http.GET

interface ManApiS {

    @GET("getJoke?page=1&count=2&type=video")
    suspend fun getImageUrlSplash(): BaseResponse<List<Test>>
    @GET("getJoke?page=1&count=2&type=video")
    suspend fun <T> getImageUrlSplashs(): BaseResponse<T>
    @GET("system/stadium-project-time/list")
    suspend fun  getTest():BaseResponse<List<String>>
}