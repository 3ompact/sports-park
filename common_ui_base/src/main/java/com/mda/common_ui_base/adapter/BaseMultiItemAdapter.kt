package com.mda.common_ui_base.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.mda.common_ui_base.entity.MultiItemType

/**
 * 基于第三方的 多类型适配器
 *
 * public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
public ExpandableItemAdapter(List<MultiItemEntity> data) {
super(data);
addItemType(TYPE_LEVEL_0, R.layout.item_expandable_lv0);
addItemType(TYPE_LEVEL_1, R.layout.item_expandable_lv1);
addItemType(TYPE_PERSON, R.layout.item_text_view);
}
@Override
protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
switch (holder.getItemViewType()) {
case TYPE_LEVEL_0:
....
//set view content
holder.itemView.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
int pos = holder.getAdapterPosition();
if (lv0.isExpanded()) {
collapse(pos);
} else {
expand(pos);
}
}});
break;
case TYPE_LEVEL_1:
// similar with level 0
break;
case TYPE_PERSON:
//just set the content
break;
}
}
 */

abstract class BaseMultiItemAdapter<T : MultiItemType>(res: Array<Int>, type: Array<Int>) :
    BaseMultiItemQuickAdapter<T, BaseViewHolder>() {

    init {
        for (i in 0 until res.size) {
            addItemType(type[i], res[i])
        }
    }

}