package com.mda.component_main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mda.component_main.R
import com.mda.component_main.bean.SingleTimeInterval

class VenueSelectionAdapter(context: Context, arrayTwoDiemn: MutableList<SingleTimeInterval>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var context: Context
    private lateinit var arrayTwoDiemn: MutableList<SingleTimeInterval>

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
        (holder as VH).tvTime.setText(arrayTwoDiemn.get(position).startTime.subSequence(0,6).toString()+"-"+arrayTwoDiemn.get(position).endTime.subSequence(0,6))

        (holder as VH).tvVenueNum.setText(arrayTwoDiemn.get(position).siteName)


    }

    //进行局部数据更新
    fun updateData(newData :MutableList<SingleTimeInterval>){

        //假如就第一个数据不一样



    }

    //设置数据 或者全量更新
    fun setData(oriData:MutableList<SingleTimeInterval>){
        arrayTwoDiemn = oriData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
//        val col = arrayTwoDiemn.size
//        val row = arrayTwoDiemn[0].size
//        var count = 0
//        for (i in arrayTwoDiemn) {
//            for (j in i) {
//                if (j == 2) {
//                    count += 1
//                }
//            }
//        }
//        arrayTwoDiemn.size
        return arrayTwoDiemn.size
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTime =
            itemView.findViewById<TextView>(R.id.tv_time_venue_selection_selected_sessions_item)
        val tvVenueNum =
            itemView.findViewById<TextView>(R.id.tv_session_venue_selection_selected_sessions_item)

    }
}