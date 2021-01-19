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


class ImageNetAdapter(mDatas: List<String>) :
    BannerAdapter<String, ImageNetAdapter.ImageHolder>(mDatas) {
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ImageHolder {
//        val imageView: ImageView = BannerUtils.getView(parent, R.layout.item_banner_imagview) as ImageView

        val imageView = ImageView(parent!!.context)
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.layoutParams = params
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        //通过裁剪实现圆角
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BannerUtils.setBannerRound(imageView, 20f)
        }
        return ImageHolder(imageView)
    }

    override fun onBindView(holder: ImageHolder?, data: String?, position: Int, size: Int) {
        //通过图片加载器实现圆角，你也可以自己使用圆角的imageview，实现圆角的方法很多，自己尝试哈
        holder!!.imageView.load(R.drawable.test)

    }

    class ImageHolder( view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView

        init {
            imageView = view as ImageView
        }
    }
}