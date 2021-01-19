package com.mda.basics_lib.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.RadioButton

class DrawableUtil {
    companion object{
        /**
         * 设置radiobutton的drawable的bounds
         */
        fun setRBDrawableBounds(rb :RadioButton,drawable: Drawable){
            var drawableSrc = drawable
            drawable.setBounds(0,0,36,36)
            rb.setCompoundDrawables(null,drawable,null,null)
        }

    }

}