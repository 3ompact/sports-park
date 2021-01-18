package com.mda.common_network

import android.net.ParseException
import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

object ExceptionHandle {

    fun handleExcetion(e: Throwable?): AppNetException {
        val ex: AppNetException
        e?.let {
            when (it) {
                is HttpException -> {
                    ex = AppNetException(Error.NETWORK_ERROR, e)
                    return ex
                }
                is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> {
                    ex = AppNetException(Error.PARSE_ERROR, e)
                    return ex
                }
                is ConnectException -> {
                    ex = AppNetException(Error.NETWORK_ERROR, e)
                    return ex
                }
                is javax.net.ssl.SSLException -> {
                    ex = AppNetException(Error.SSL_ERROR, e)
                    return ex
                }

                is SocketTimeoutException -> {
                    ex = AppNetException(Error.TIMEOUT_ERROR, e)
                    return ex
                }
                is java.net.SocketTimeoutException -> {
                    ex = AppNetException(Error.TIMEOUT_ERROR, e)
                    return ex
                }
                is java.net.UnknownHostException -> {
                    ex = AppNetException(Error.TIMEOUT_ERROR, e)
                    return ex
                }
                is AppNetException -> return it

                else -> {
                    ex = AppNetException(Error.UNKNOW, e)
                    return ex
                }
            }
        }
        ex = AppNetException(Error.UNKNOW, e)
        return ex
    }
}