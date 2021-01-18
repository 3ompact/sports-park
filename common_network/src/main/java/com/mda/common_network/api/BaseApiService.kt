package com.mda.common_network.api

import com.mda.basics_lib.bean.BaseResponse
import com.mda.common_network.BaseResp
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface BaseApiService {
    @GET
    suspend fun getAdUri(): Deferred<BaseResponse<String>>

    @GET("user/lg/private_articles/{page}/json")
    suspend fun getAdUriTest(): BaseResp<String>

    @GET
    suspend fun getTest() : BaseResponse<String>
}