package com.mda.component_main

import com.mda.basics_lib.bean.BaseResponse
import com.mda.common_network.bean.Test
import com.mda.component_main.bean.*
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
//import com.mda.component_main.bean.VenueEvaluate
import retrofit2.http.GET
import retrofit2.http.POST

interface ManApiS {

    @GET("getJoke?page=1&count=2&type=video")
    suspend fun getImageUrlSplash(): BaseResponse<List<Test>>

    @GET("getJoke?page=1&count=2&type=video")
    suspend fun <T> getImageUrlSplashs(): BaseResponse<T>

    @GET("app/stadium-project-time/list")
    suspend fun getTest(): BaseResponse<List<String>>

    /**
     * 获取homefragment所有场馆信息
     */
    @GET("app/sys-stadium/queryAll")
    suspend fun getHomeFragmentAllVenue(): BaseResponse<HomeFragmentEncapsulation>



    /**
     * 获取venuedetail单个场馆详细信息
     */
    @POST("app/sys-stadium/queryStadiumInfo")
    @FormUrlEncoded
    suspend fun getAllInfoOfVenueDetail(@Field("stadiumId") stadiumId : Long): BaseResponse<VenueDetailData>

    /**
     * 获取venuedetail单个场馆详细信息
     */
    @POST("app/site-time-price/queryAll")
    @FormUrlEncoded
    suspend fun getAllTimeIntervalOfVenueSeleection(@Field("projectId") stadiumId : Long): BaseResponse<VenueSelectionData>



    /**
     * 获取venuedetail单个场馆详细信息
     */
    @GET("app/stadium-project/queryAll")
    suspend fun getSummaryOfVenueDetail(): BaseResponse<VenueSummary>


    /**
     * 获取venuedetail单个场馆下所有的项目集合
     */
    @GET("app/stadium-project/queryAll")
    suspend fun getAllProjectVenueDetail(): BaseResponse<VenueProjects>

    /**
     * 获取venuedetail单个场馆下所有的项目的评论集合
     */
//    @GET("app/stadium-project/queryAll")
//    suspend fun getAllEvalateVenueDetail(): BaseResponse<VenueEvaluate>
}