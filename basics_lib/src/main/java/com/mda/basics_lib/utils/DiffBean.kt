package com.mda.basics_lib.utils

import androidx.recyclerview.widget.DiffUtil
import com.mda.basics_lib.bean.BaseData

/**
 * 用于recycleview的数据刷新时进行数据的比对，增加刷新性能
 */


class DiffBean<T : BaseData> : DiffUtil.Callback {

    private lateinit var mOldDatas: List<T>
    private lateinit var mNewDatas: List<T>

    constructor(oldDatas: List<T>, newDatas: List<T>) : super() {
        this.mOldDatas = oldDatas
        this.mNewDatas = newDatas


    }


    override fun getOldListSize(): Int {
        if (mOldDatas != null) {
            return mOldDatas.size
        } else {
            return 0
        }
    }

    override fun getNewListSize(): Int {
        if (mNewDatas != null) {
            return mNewDatas.size
        } else {
            return 0
        }
    }


    /**
     * 被diffutil调用，用来判断两个对象是否是相同的Item
     *
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return mOldDatas.get(oldItemPosition).id.equals(mNewDatas.get(newItemPosition).id)
    }

    /**
     * 被diffutil调用用于检查两个item是否含有两个相同的数据
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return mOldDatas.get(oldItemPosition).id.equals(mNewDatas.get(newItemPosition).id)
    }
}