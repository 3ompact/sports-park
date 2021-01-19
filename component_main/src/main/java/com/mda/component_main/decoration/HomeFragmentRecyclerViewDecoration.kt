package com.mda.component_main.decoration

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mda.basics_lib.utils.PhoneInfo

class HomeFragmentRecyclerViewDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        var position = parent.getChildAdapterPosition(view)
        var count = parent.adapter!!.itemCount

        var margin = 12 * PhoneInfo.getPhonDensity(parent.context.applicationContext)

        if (position == (count - 1)) {
            outRect.top = margin.toInt()
            outRect.bottom = margin.toInt()
        } else if (position >= 6) {
            outRect.top = margin.toInt()
        }
    }
}