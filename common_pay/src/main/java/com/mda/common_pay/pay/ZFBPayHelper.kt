package com.mda.common_pay.pay

import android.app.Activity
import com.alipay.sdk.app.AuthTask
import com.alipay.sdk.app.PayTask
import java.lang.StringBuilder

/**
 * 支付宝支付辅助类
 */
abstract class ZFBPayHelper {
    companion object {
        /**
         * 支付宝业务的入参app_id
         */
        val APPID: String = ""

        /**
         * 支付宝授权业务入参 pid
         */
        val PID: String = ""

        /**
         * 支付宝账户登录授权业务入参 target_id
         */
        val TARGET_ID: String = ""

    }


    suspend fun pay(activity: Activity, orderInfo: String) {
        var alipayTask: PayTask = PayTask(activity)
        var result: Map<String, String> = alipayTask.payV2(orderInfo, true)
    }

    /**
     * @param order 订单信息
     * @param sign 签名信息
     * @return 对订单信息和签名信息进行组合
     *
     */
    fun getOrderInfo(order: String, sign: String): String {
        var sb: StringBuilder = StringBuilder()
        return sb.append(order).append("&").append(sign).toString()
    }

    /**
     * 请求支付信息
     */
    abstract suspend fun getPayNecessaryInfo()


    /**
     * 请求支付信息
     */
    abstract suspend fun getAuthNecessaryInfo()
    /**
     * @param auth 订单信息
     * @param sign 签名信息
     * @return 对授权信息和签名信息进行组合
     *
     */
    fun getAuthInfo(auth: String, sign: String): String {
        var sb: StringBuilder = StringBuilder()
        return sb.append(auth).append("&").append(sign).toString()
    }

    /**
     * 进行授权请求
     */
    suspend fun auth(activity: Activity, authInfo: String) {
        var authTask: AuthTask = AuthTask(activity)
        var result: Map<String, String> = authTask.authV2(authInfo, true)
    }

    /**
     * 获取版本号信息
     */
    fun getSdkVersion(activity: Activity): String {
        return PayTask(activity).version
    }


}