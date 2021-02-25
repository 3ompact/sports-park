package com.mda.basics_lib.toast

import android.app.Application
import android.content.Context
import android.widget.Toast

class ToastUtil {

    companion object {

        private lateinit var context: Context

        //初始化context
        fun init(context:Context){
            this.context = context
        }
        /**
         * 使用applicationcontext
         */
        fun showLengthLong(char: CharSequence) {
            Toast.makeText(context, char, Toast.LENGTH_LONG).show()
        }
        /**
         * 使用applicationcontext
         */
        fun showLengthShort(char: CharSequence) {
            Toast.makeText(context, char, Toast.LENGTH_SHORT).show()
        }


    }
}