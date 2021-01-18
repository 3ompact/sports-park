package com.mda.common_ui_base.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * 注意使用chad 的layout的layout-height不要使用match-parent
 * 基于第三方库的适配器
 *
 *
 * public class QuickAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
public QuickAdapter() {
super(R.layout.tweet, DataServer.getSampleData());
}

@Override
protected void convert(BaseViewHolder viewHolder, Status item) {
viewHolder.setText(R.id.tweetName, item.getUserName())
.setText(R.id.tweetText, item.getText())
.setText(R.id.tweetDate, item.getCreatedAt())
.setVisible(R.id.tweetRT, item.isRetweet())
.linkify(R.id.tweetText);
Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) viewHolder.getView(R.id.iv));
}
}
 *
 */

abstract class BaseAdapter<T>(layoutResId: Int, data: MutableList<T>?) : BaseQuickAdapter<T, BaseViewHolder>(layoutResId, data)