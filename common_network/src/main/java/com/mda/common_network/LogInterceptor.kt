package com.mda.common_network

import android.text.TextUtils
import android.util.Log
import com.mda.basics_lib.log.FormatPrinter
import com.mda.basics_lib.log.LogUtil
import com.mda.basics_lib.utils.ZipHelper.Companion.decompressForGzip
import com.mda.basics_lib.utils.ZipHelper.Companion.decompressToStringForZlib
import com.mda.common_network.utils.NetworkFormatPrinter
import com.mda.common_network.utils.UrlEncoderUtils.Companion.hasUrlEncoded
import okhttp3.*
import okio.Buffer
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.URLDecoder

import java.nio.charset.Charset
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * log拦截器
 */
class LogInterceptor : Interceptor {

    private val mPrinter: FormatPrinter = NetworkFormatPrinter()

    private val printLevel = Level.ALL

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val logRequest =
            printLevel == Level.ALL || printLevel != Level.NONE && printLevel == Level.REQUEST

        if (logRequest) {
            if (request.body != null && isParseable(request.body!!.contentType())){

                mPrinter.printJsonRequest(request, parseParams(request))

            }else{
                mPrinter.printFileRequest(request)
            }
        }

        val logResponse = printLevel == Level.ALL || printLevel != Level.NONE && printLevel == Level.RESPONSE

        val t1 = if(logResponse) System.nanoTime() else 0
        val originalResponse :Response
        originalResponse = try {
            chain.proceed(request)
        }catch (e:Exception){
            LogUtil.debugInfo("Http Error: %s", e.message.toString())
            throw e
        }

        val t2 = if (logResponse) System.nanoTime() else 0
        val responseBody = originalResponse.body

        //打印响应结果
        var bodyString :String? = null
        if (responseBody != null && isParseable(responseBody.contentType())) {
            bodyString = printResult(request, originalResponse, logResponse)
        }
        if (logResponse) {
            val segmentList =
                request.url.encodedPathSegments
            val header: String = if (originalResponse.networkResponse == null) {
                originalResponse.headers.toString()
            } else {
                originalResponse.networkResponse!!.request.headers.toString()
            }
            val code = originalResponse.code
            val isSuccessful = originalResponse.isSuccessful
            val message = originalResponse.message
            val url = originalResponse.request.url.toString()
            if (responseBody != null && isParseable(responseBody.contentType())) {
                mPrinter.printJsonResponse(
                    TimeUnit.NANOSECONDS.toMillis(t2 - t1), isSuccessful,
                    code, header, responseBody.contentType(), bodyString, segmentList, message, url
                )
            } else {
                mPrinter.printFileResponse(
                    TimeUnit.NANOSECONDS.toMillis(t2 - t1),
                    isSuccessful, code, header, segmentList, message, url
                )
            }
        }
        return originalResponse

    }



    /**
     * 打印响应结果
     *
     * @param request     [Request]
     * @param response    [Response]
     * @param logResponse 是否打印响应结果
     * @return 解析后的响应结果
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun printResult(
        request: Request,
        response: Response,
        logResponse: Boolean
    ): String? {
        return try {
            //读取服务器返回的结果
            val responseBody = response.newBuilder().build().body
            val source = responseBody!!.source()
            source.request(Long.MAX_VALUE) // Buffer the entire body.
            val buffer = source.buffer

            //获取content的压缩类型
            val encoding = response
                .headers["Content-Encoding"]
            val clone = buffer.clone()

            //解析response content
            parseContent(responseBody, encoding, clone)
        } catch (e: IOException) {
            e.printStackTrace()
            "{\"error\": \"" + e.message + "\"}"
        }
    }

    /**
     * 解析服务器响应的内容
     *
     * @param responseBody [ResponseBody]
     * @param encoding     编码类型
     * @param clone        克隆后的服务器响应内容
     * @return 解析后的响应结果
     */
    private fun parseContent(
        responseBody: ResponseBody?,
        encoding: String?,
        clone: Buffer
    ): String? {
        var charset = Charset.forName("UTF-8")
        val contentType = responseBody!!.contentType()
        if (contentType != null) {
            charset = contentType.charset(charset)
        }
        //content 使用 gzip 压缩
        return if ("gzip".equals(encoding, ignoreCase = true)) {
            //解压
            decompressForGzip(
                clone.readByteArray(),
                convertCharset(charset)
            )
        } else if ("zlib".equals(encoding, ignoreCase = true)) {
            //content 使用 zlib 压缩
            decompressToStringForZlib(
                clone.readByteArray(),
                convertCharset(charset)
            )
        } else {
            //content 没有被压缩, 或者使用其他未知压缩方式
            clone.readString(charset)
        }
    }




    companion object {


        fun parseParams(request:Request):String{
            return try{
                val body = request.newBuilder().build().body ?: return ""
                val requestbuffer = Buffer()
                body.writeTo(requestbuffer)
                var charset = Charset.forName("UTF-8")
                val contentType = body.contentType()
                if(contentType != null){
                    charset = contentType.charset(charset)
                }
                var json = requestbuffer.readString(charset)
                if(hasUrlEncoded(json!!)){
                    json = URLDecoder.decode(
                        json,
                        convertCharset(charset)
                    )
                }
                jsonFormat(json!!)
            }catch (e:IOException){
                e.printStackTrace()
                "{\"error\": \"" + e.message + "\"}"
            }
        }

        /**
         * json 格式化
         *
         * @param json
         * @return
         */
        @JvmStatic
        fun jsonFormat(json: String): String {
            var json = json
            if (TextUtils.isEmpty(json)) {
                return "Empty/Null json content"
            }
            var message: String
            try {
                json = json.trim { it <= ' ' }
                message = if (json.startsWith("{")) {
                    val jsonObject = JSONObject(json)
                    jsonObject.toString(4)
                } else if (json.startsWith("[")) {
                    val jsonArray = JSONArray(json)
                    jsonArray.toString(4)
                } else {
                    json
                }
            } catch (e: JSONException) {
                message = json
            } catch (error: OutOfMemoryError) {
                message = "Output omitted because of Object size"
            }
            return message
        }


        fun convertCharset(charset: Charset?): String {
            val s = charset.toString()
            val i = s.indexOf("[")
            return if (i == -1) {
                s
            } else s.substring(i + 1, s.length - 1)
        }


        fun isParseable(mediaType: MediaType?): Boolean {
            return if(mediaType?.type == null){
                false
            }else  isText(mediaType) || isPlain(mediaType) || isJson(mediaType) || isHtml(mediaType)|| isForm(mediaType)|| isXml(mediaType)
        }

        fun isText(mediaType: MediaType?): Boolean {
            return if (mediaType?.type == null) {
                false
            } else "text" == mediaType.type
        }

        fun isPlain(mediaType: MediaType?): Boolean {
            return if (mediaType?.subtype == null) {
                false
            } else mediaType.subtype
                .toLowerCase().contains("plain")
        }

        @JvmStatic
        fun isJson(mediaType: MediaType?): Boolean {
            return if (mediaType?.subtype == null) {
                false
            } else mediaType.subtype.toLowerCase(Locale.getDefault()).contains("json")
        }

        @JvmStatic
        fun isXml(mediaType: MediaType?): Boolean {
            return if (mediaType?.subtype == null) {
                false
            } else mediaType.subtype.toLowerCase(Locale.getDefault()).contains("xml")
        }

        fun isHtml(mediaType: MediaType?): Boolean {
            return if (mediaType?.subtype == null) {
                false
            } else mediaType.subtype.toLowerCase(Locale.getDefault()).contains("html")
        }

        fun isForm(mediaType: MediaType?): Boolean {
            return if (mediaType?.subtype == null) {
                false
            } else mediaType.subtype.toLowerCase(Locale.getDefault())
                .contains("x-www-form-urlencoded")
        }

    }

    enum class Level {
        /**
         * 不打印
         */
        NONE,

        /**
         * 答应请求内容
         */
        REQUEST,

        /**
         * 只打印响应内容
         */
        RESPONSE,

        /**
         * 答应所有内容
         */
        ALL

    }
}