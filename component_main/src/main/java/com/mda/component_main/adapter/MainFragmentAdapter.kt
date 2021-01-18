package com.mda.component_main.adapter

import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.mda.common_ui_base.adapter.BaseMultiItemAdapter
import com.mda.common_ui_base.entity.MultiItemType

/**
 * MainFragment主页多类型适配器
 */
class MainFragmentAdapter(a: Any, res: Array<Int>, type: Array<Int>) :
    BaseMultiItemAdapter<MultiItemType>(res, type) {


    override fun convert(holder: BaseViewHolder, item: MultiItemType) {
        when (item.itemType) {
            MultiItemType.TEXT -> {
//                holder.setText()


            }
            MultiItemType.IMAGE -> {

            }
            MultiItemType.BANNER -> {

            }
            MultiItemType.GRID -> {

            }
        }
    }

}