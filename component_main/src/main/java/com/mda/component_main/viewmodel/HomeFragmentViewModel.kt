package com.mda.component_main.viewmodel

import com.mda.basics_lib.`interface`.OnResponseListener
import com.mda.common_network.Req
import com.mda.common_ui_base.base.BaseViewModel
import com.mda.component_main.ManApiS
import com.mda.component_main.bean.HomeFragmentEncapsulation

class HomeFragmentViewModel : BaseViewModel() {

    /**
     * 获取homefragment总览数据
     */
    fun getSummaryData(onResponseListener: OnResponseListener<HomeFragmentEncapsulation>){
        launchRep({ Req.getRequestUtilInstanceTest(ManApiS::class.java)!!.getHomeFragmentAllVenue()},onResponseListener)
    }
}