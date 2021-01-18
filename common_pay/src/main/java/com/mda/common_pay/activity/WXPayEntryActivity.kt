package com.mda.common_pay.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.mda.common_pay.Constants
import com.mda.common_pay.R
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.tencent.mm.opensdk.openapi.WXAPIFactory


class WXPayEntryActivity : Activity(),IWXAPIEventHandler{



    private  val TAG :String = "MicroMsg.SDKSample.WXPayEntryActivity"



    private lateinit var api:IWXAPI


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pay_result)

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID)
        api.handleIntent(getIntent(), this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        api.handleIntent(intent, this)
    }

    override fun onReq(req: BaseReq?) {}

    override fun onResp(resp: BaseResp) {
        Log.d(TAG, "onPayFinish, errCode = " + resp.errCode)
        if (resp.type == ConstantsAPI.COMMAND_PAY_BY_WX) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.app_tip)
            builder.setMessage(getString(R.string.pay_result_callback_msg, resp.errCode.toString()))
            builder.show()
        }
    }
}