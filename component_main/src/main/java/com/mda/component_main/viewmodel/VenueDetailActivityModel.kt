package com.mda.component_main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mda.basics_lib.`interface`.OnResponseListener
import com.mda.common_network.OkHttpSingleton
import com.mda.common_network.Req
import com.mda.common_network.RequestUtil
import com.mda.common_network.bean.Test
import com.mda.common_ui_base.base.BaseViewModel
import com.mda.component_main.ManApiS
import com.mda.component_main.bean.VenueDetailData

/**
 * 过渡页viewmodel
 *
 */

class VenueDetailActivityModel : BaseViewModel() {
    lateinit var imageUrl : MutableLiveData<String>


    fun getVenueDetail(id:Long ,onResponseListener: OnResponseListener<VenueDetailData>){
        launchRep({Req.getRequestUtilInstanceTest(ManApiS::class.java)!!.getAllInfoOfVenueDetail(id)},onResponseListener)

    }

    fun getImageUrl(onResponseListener: OnResponseListener<List<Test>>){
//        launchRep({Req.getRequestUtilInstance().getImageUrlSplash()},onResponseListener)

        launchRep({Req.getRequestUtilInstanceTest(ManApiS::class.java)!!.getImageUrlSplash()},onResponseListener)


//        launchRep({Req.getRequestUtilInstance().getImageUrlSplash()},onResponseListener)



        if(OkHttpSingleton.okhttpInstance === OkHttpSingleton.okhttpInstance){
            Log.i("3ompact","------" +RequestUtil.INSTANCE+"__INSTANCE__"+RequestUtil.INSTANCE)
        }

//        if(OkHttpSingleton.okhttpInstance == OkHttpSingleton.okhttpInstance){
//            Log.i("3ompact","------" +OkHttpSingleton.instance+"__OKHTTP__"+OkHttpSingleton.instance)
//            Log.i("3ompact","------" +RequestUtil.INSTANCE+"__INSTANCE__"+RequestUtil.INSTANCE)
//
//        }
        if(RequestUtil.INSTANCE === RequestUtil.INSTANCE){
            Log.i("3ompact","------" +OkHttpSingleton.instance+"__OKHTTP__"+OkHttpSingleton.instance)
            Log.i("3ompact","------" +RequestUtil.INSTANCE+"__INSTANCE__"+RequestUtil.INSTANCE)

        }
    }
}