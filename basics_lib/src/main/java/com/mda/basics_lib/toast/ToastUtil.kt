package com.mda.basics_lib.toast

import android.content.Context
import android.widget.Toast

class ToastUtil {

    companion object {
        /**
         * 使用applicationcontext
         */
        fun showLengthLong(context: Context, char: CharSequence) {
            Toast.makeText(context, char, Toast.LENGTH_LONG).show()
        }
        /**
         * 使用applicationcontext
         */
        fun showLengthShort(context: Context, char: CharSequence) {
            Toast.makeText(context, char, Toast.LENGTH_SHORT).show()
        }


    }
}