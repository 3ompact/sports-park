package com.mda.basics_lib.utils

import android.app.Application
import android.content.Context

/**
 * 获取手机相关信息
 */
class PhoneInfo {

    companion object{
        /**
         * 获取手机型号
         */
        fun getPhoneModel():String{
            return android.os.Build.MODEL
        }

        /**
         * 获取手机厂商
         */
        fun getPhoneFactory():String{
            return android.os.Build.BOARD
        }

        /**
         * 获取系统版本号
         */
        fun getSystemVersion():String{
            return android.os.Build.VERSION.RELEASE
        }

        /**
         * 获取手机像素宽度
         */
        fun getPhoneWidthAndHeightPx(application: Context):Array<Int>{
            var width = application.resources.displayMetrics.widthPixels
            var height = application.resources.displayMetrics.widthPixels
            return arrayOf(width,height)
        }

        /**
         * 获取手机像素密度
         */
        fun getPhonDensity(application: Context):Float{
            var density = application.resources.displayMetrics.density
            return density
        }


    }
}