package com.mda.component_main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mda.component_main.R

class VenueSelectionAdapter(context: Context, arrayTwoDiemn: Array<Array<Int>>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var context: Context
    private lateinit var arrayTwoDiemn: Array<Array<Int>>

    init {
        this.context = context
        this.arrayTwoDiemn = arrayTwoDiemn
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VH(
            LayoutInflater.from(context)
                .inflate(R.layout.item_venue_selection_selected_sessions, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        val col = arrayTwoDiemn.size
        val row = arrayTwoDiemn[0].size
        var count = 0
        for (i in arrayTwoDiemn) {
            for (j in i) {
                if (j == 2) {
                    count += 1
                }
            }
        }
        return count
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTime =
            itemView.findViewById<TextView>(R.id.tv_time_venue_selection_selected_sessions_item)
        val tvVenueNum =
            itemView.findViewById<TextView>(R.id.tv_session_venue_selection_selected_sessions_item)

    }
}