package com.mda.basics_lib.utils

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

class DateAndTimeUtil {

    companion object{
        /**
         *  获取当前日期是周几
         *  DateTime(dateStr).dayOfWeek()
         */

        fun getDay(dateStr: String):Int {
            val fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

            return  fmt.parseDateTime(dateStr).dayOfWeek().get()
        }
        /**
         *  获取当前日期是几月份
         */

        fun getMonth(dateStr: String):Int {
            val fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

            return  fmt.parseDateTime(dateStr).monthOfYear().get()
        }
        /**
         * 获取当前日期是几号
         */

        fun getDayOfMonth(dateStr: String):Int {
            val fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

            return  fmt.parseDateTime(dateStr).dayOfMonth().get()
        }

//        fun getYearMonthDay(dateStr: String):String{
//            val fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
//
//            return  fmt.parseDateTime(dateStr).
////                .toString()
//        }

    }


}