package com.mda.basics_lib.utils

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan


class SpannerableStringUtil {

    companion object {
//        fun genStrForBtnCommit(str:String,spanNum:Int,array: Array<String>,arrayIndex: Array<Int>):SpannableString{
//            var spannableString = SpannableString(str)
//            var sNum = array.size
//            for(i in array.indices){
//                var foregroundColorSpan = ForegroundColorSpan(Color.parseColor(array[i]))
//                spannableString.setSpan(foregroundColorSpan, 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
//                foregroundColorSpan = ForegroundColorSpan(Color.parseColor(array[i]))
//                spannableString.setSpan(foregroundColorSpan, 5, str.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
//            }
//        }

        fun genStrForBtnCommit(str: String): SpannableString {
            var spannableString = SpannableString(str)
            var foregroundColorSpan = ForegroundColorSpan(Color.parseColor("#333333"))
            spannableString.setSpan(foregroundColorSpan, 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            var foregroundColorSpan2 = ForegroundColorSpan(Color.parseColor("#fe634f"))
            spannableString.setSpan(
                foregroundColorSpan2,
                5,
                str.length,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )

            return spannableString

        }
    }
}