package com.mda.common_network.api

import com.mda.basics_lib.bean.BaseResponse
import com.mda.common_network.BaseResp
import com.mda.common_network.bean.Test
import retrofit2.http.GET

interface ManApiService : BaseApiService {
    @GET
    suspend fun getADAD(): BaseResponse<String>

    @GET("getJoke?page=1&count=2&type=video")
    suspend fun getImageUrlSplash(): BaseResponse<List<Test>>
}