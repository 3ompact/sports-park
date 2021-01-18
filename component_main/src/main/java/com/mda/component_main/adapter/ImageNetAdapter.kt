package com.mda.component_main.adapter

import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mda.component_main.R
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.util.BannerUtils


class ImageNetAdapter(mDatas: List<Any?>?) :
    BannerAdapter<Any?, ImageNetAdapter.ImageHolder?>(mDatas) {
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val imageView: ImageView = BannerUtils.getView(parent, R.layout.item_banner_imagview) as ImageView
        //通过裁剪实现圆角
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BannerUtils.setBannerRound(imageView, 20f)
        }
        return ImageHolder(imageView)
    }

    override fun onBindView(holder: ImageHolder?, data: Any?, position: Int, size: Int) {
        //通过图片加载器实现圆角，你也可以自己使用圆角的imageview，实现圆角的方法很多，自己尝试哈
        holder!!.imageView.load(R.drawable.icon_home_check)

    }

    class ImageHolder( view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView

        init {
            imageView = view as ImageView
        }
    }
}