package com.mda.component_main.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mda.basics_lib.utils.SpannerableStringUtil
import com.mda.common_ui_base.entity.MultiItemType
import com.mda.component_main.R
import com.youth.banner.Banner


/**
 * MainFragment主页多类型适配器
 */
class HomeFragmentWithoutThrAdapter(context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var context: Context

    init {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            MultiItemType.SEARCHBAR -> {
                return ViewHolderSearchBar(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_search_bar,
                        parent,
                        false
                    )
                )

            }
            MultiItemType.BANNER -> {


                return ViewHolderBanner(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_banner,
                        parent,
                        false
                    )
                )

            }
            MultiItemType.FIXED -> {
                return ViewHolderFixed(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_fixed,
                        parent,
                        false
                    )
                )

            }
            MultiItemType.QUCIKLY -> {
                return ViewHolderQuickly(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_quickly,
                        parent,
                        false
                    )
                )

            }
            MultiItemType.LEFTTITLEANDRIGHTMORE -> {
                return ViewHolderLeftTitleAndMore(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_title,
                        parent,
                        false
                    )
                )

            }

            MultiItemType.GRID -> {
                return ViewHolderGrid(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_sports_venues_grid,
                        parent,
                        false
                    )
                )
            }

            else -> {
                return ViewHolderGrid(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_sports_venues_grid,
                        parent,
                        false
                    )
                )
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            MultiItemType.SEARCHBAR -> {
                (holder as ViewHolderSearchBar)
                    .etSB.setHint("搜索体育场馆")
            }
            MultiItemType.BANNER -> {
                var banner: Banner<*, *> = (holder as ViewHolderBanner).banner
                var list = ArrayList<String>()
                list.add("ss")
                var imageUrls = listOf(
                    "https://img.zcool.cn/community/011ad05e27a173a801216518a5c505.jpg",
                    "https://img.zcool.cn/community/0148fc5e27a173a8012165184aad81.jpg",
                    "https://img.zcool.cn/community/013c7d5e27a174a80121651816e521.jpg",
                    "https://img.zcool.cn/community/01b8ac5e27a173a80120a895be4d85.jpg",
                    "https://img.zcool.cn/community/01a85d5e27a174a80120a895111b2c.jpg",
                    "https://img.zcool.cn/community/01085d5e27a174a80120a8958791c4.jpg"
                )
                var adapter = ImageNetAdapter(imageUrls)

                banner?.let {
//                    it.addBannerLifecycleObserver(this)
//                    it.setIndicator(RoundLinesIndicator(this))
                    it.setBannerRound(20f)
                    it.adapter = adapter
                }

//                banner.setAdapter(ImageNetAdapter(imageUrls))
//                banner.setBannerRound(BannerUtils.dp2px(5f))
//                banner.setIndicator(RoundLinesIndicator(context))
//                banner.setIndicatorSelectedWidth(BannerUtils.dp2px(15f).toInt())
            }
            MultiItemType.FIXED -> {
//                (holder.itemView as ViewHolderFixed).etSB.setHint("搜索体育场馆")

            }
            MultiItemType.QUCIKLY -> {
                Log.d("3ompact", "start: ")


//                val mRequestListener = object: RequestListener<Drawable>{
//                    override fun onLoadFailed(
//                        e: GlideException?,
//                        model: Any?,
//                        target: Target<Drawable>?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        Log.d("3ompact", "onException: " + e.toString()+"  model:"+model+" isFirstResource: "+isFirstResource)
//                        return false
//                    }
//
//                    override fun onResourceReady(
//                        resource: Drawable?,
//                        model: Any?,
//                        target: Target<Drawable>?,
//                        dataSource: DataSource?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        Log.e("3ompact",  "model:"+model+" isFirstResource: "+isFirstResource);
//                        return false
//                    }
//
//                }
//
//                Glide.with(context).load(
//                    "https://ss1.bdsta" +
//                            "tic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1517417813,2367413112&fm=26&gp=0.jpg"
//                ).listener(mRequestListener).into((holder as ViewHolderQuickly).ivOneQ)

//                Log.d("3ompact", "end: ")
//
                (holder as ViewHolderQuickly).ivOneQ.load(
                    "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1517417813,2367413112&fm=26&gp=0.jpg"
                ) {

                }




            }
            MultiItemType.LEFTTITLEANDRIGHTMORE -> {

            }
            MultiItemType.GRID -> {

                (holder as ViewHolderGrid).cl.setOnClickListener {

                    ARouter.getInstance()
                        .build("/cm/venuedetailactivity")
                        .navigation(context, object : NavCallback() {

                            override fun onFound(postcard: Postcard?) {
                                Log.d("3ompact", "找到了")
                            }

                            override fun onLost(postcard: Postcard?) {
                                Log.d("3ompact", "没找到")
                            }

                            override fun onArrival(postcard: Postcard?) {
                                Log.d("3ompact", "跳转成功")
                            }

                            override fun onInterrupt(postcard: Postcard?) {
                                Log.d("3ompact", "被拦截了")
                            }

                        })

                }

                if (position == 19) {
//                    val params : ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//                    var bottom = 12*PhoneInfo.getPhonDensity(context.applicationContext)
//                    params.setMargins(0,0,0,bottom.toInt())
//                    (holder as ViewHolderGrid).cl.layoutParams = params
//                    LogUtil.debugInfo(bottom.toString())
//                    Log.i("3ompact",bottom.toString())
                }

            }
        }
    }

    override fun getItemCount(): Int {

        return 20

    }

    override fun getItemViewType(position: Int): Int {

//        return MultiItemType.GRID
        when (position) {
            0 -> {
                return MultiItemType.SEARCHBAR
            }
            1 -> {
                return MultiItemType.BANNER
            }
            2 -> {
                return MultiItemType.FIXED

            }
            3 -> {
                return MultiItemType.QUCIKLY

            }
            4 -> {
                return MultiItemType.LEFTTITLEANDRIGHTMORE

            }
            5 -> {
                return MultiItemType.GRID

            }
            else -> {
                return MultiItemType.GRID

            }
        }
    }


    class ViewHolderSearchBar(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var etSB = itemView.findViewById<EditText>(R.id.sb_searchbar_item)

    }

    class ViewHolderBanner(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var banner: Banner<*, *> = itemView.findViewById(R.id.banner_item)
    }


    class ViewHolderFixed(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivOne: ImageView = itemView.findViewById(R.id.iv_one_fixed_item)
        var ivTwo: ImageView = itemView.findViewById(R.id.iv_two_fixed_item)
        var ivThr: ImageView = itemView.findViewById(R.id.iv_thr_fixed_item)
        var ivFour: ImageView = itemView.findViewById(R.id.iv_four_fixed_item)
        var ivFive: ImageView = itemView.findViewById(R.id.iv_five_fixed_item)
        var ivSix: ImageView = itemView.findViewById(R.id.iv_six_fixed_item)
    }

    class ViewHolderQuickly(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivOneQ: ImageView = itemView.findViewById(R.id.iv_one_quickly_item)
        var ivTwoQ: ImageView = itemView.findViewById(R.id.iv_two_quickly_item)
        var ivThrQ: ImageView = itemView.findViewById(R.id.iv_thr_quickly_item)
    }

    class ViewHolderLeftTitleAndMore(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvMore: TextView = itemView.findViewById(R.id.tv_more_title_item)
    }

    class ViewHolderGrid(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cl: ConstraintLayout = itemView.findViewById(R.id.cl_sports_venues_item)
        var ivOne: ImageView = itemView.findViewById(R.id.iv_one_sports_venues_item)
        var ivTwo: ImageView = itemView.findViewById(R.id.iv_two_sports_venues_item)
        var tvOneDesc: TextView = itemView.findViewById(R.id.tv_desc_one_sports_venues_item)
        var tvOneLocation: TextView = itemView.findViewById(R.id.tv_location_one_sports_venues_item)
        var tvTwoDesc: TextView = itemView.findViewById(R.id.tv_desc_two_sports_venues_item)
        var tvTwoLocation: TextView = itemView.findViewById(R.id.tv_location_two_sports_venues_item)
    }


}
