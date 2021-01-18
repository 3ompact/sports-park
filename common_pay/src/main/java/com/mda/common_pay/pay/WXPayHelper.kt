package com.mda.common_pay.pay

import android.content.Context
import com.tencent.mm.opensdk.constants.Build
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import org.json.JSONObject

/**
 * 微信支付辅助类  需要在支付界面进行回调处理
 */
abstract class WXPayHelper {

    companion object {
        private lateinit var api: IWXAPI
        private val APPID: String = ""


        suspend fun pay(context: Context,jsonObject :JSONObject) {
            api = WXAPIFactory.createWXAPI(context, APPID)

            var req: PayReq = PayReq()
            req.appId			= jsonObject.getString("appid")
            req.partnerId		= jsonObject.getString("partnerid")
            req.prepayId		= jsonObject.getString("prepayid")
            req.nonceStr		= jsonObject.getString("noncestr")
            req.timeStamp		= jsonObject.getString("timestamp")
            req.packageValue	= jsonObject.getString("package")
            req.sign			= jsonObject.getString("sign")
            req.extData			= "app data" // optional
            api.sendReq(req)
        }

        /**
         * 从服务端获取相关信息
         */
        suspend fun getPayInfo(json: String): JSONObject {
            return JSONObject(json)
        }

        /**
         * 获取微信支付版本号
         * */
        fun getSupportVersion(): Boolean {
            return api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT
        }

    }
}