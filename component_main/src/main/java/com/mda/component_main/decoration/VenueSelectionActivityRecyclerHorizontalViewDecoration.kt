package com.mda.component_main.decoration

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mda.basics_lib.utils.PhoneInfo

class VenueSelectionActivityRecyclerHorizontalViewDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        var position = parent.getChildAdapterPosition(view)
        var count = parent.adapter!!.itemCount

        var margin = 7 * PhoneInfo.getPhonDensity(parent.context.applicationContext)
        var margin1 = 15 * PhoneInfo.getPhonDensity(parent.context.applicationContext)

        if (position == 0) {
            outRect.left = margin1.toInt()
        } else if (position >= 1 && position < (count - 1)) {
            outRect.left = margin.toInt()
        }else{
            outRect.right = margin1.toInt()
        }
    }
}