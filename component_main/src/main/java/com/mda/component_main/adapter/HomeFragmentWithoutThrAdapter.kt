package com.mda.component_main.adapter

import android.R.attr.banner
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mda.basics_lib.log.LogUtil
import com.mda.common_ui_base.entity.MultiItemType
import com.mda.component_main.R
import com.youth.banner.Banner
import com.youth.banner.indicator.RoundLinesIndicator
import com.youth.banner.util.BannerUtils


/**
 * MainFragment主页多类型适配器
 */
class HomeFragmentWithoutThrAdapter(context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var context:Context
    init {
        this.context = context
        LogUtil.debugInfo("init")
        Log.i("3ompact","init")


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        LogUtil.debugInfo("ok")

        when (viewType) {
            MultiItemType.SEARCHBAR -> {
                return ViewHolderSearchBar(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_search_bar,
                        parent,
                        false
                    )
                )

                LogUtil.debugInfo("1")
            }
            MultiItemType.BANNER -> {


                return ViewHolderBanner(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_banner,
                        parent,
                        false
                    )
                )
                LogUtil.debugInfo("2")

            }
            MultiItemType.FIXED -> {
                return ViewHolderFixed(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_fixed,
                        parent,
                        false
                    )
                )
                LogUtil.debugInfo("4")

            }
            MultiItemType.QUCIKLY -> {
                return ViewHolderQuickly(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_quickly,
                        parent,
                        false
                    )
                )
                LogUtil.debugInfo("5")

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
                LogUtil.debugInfo("7")

            }
            else ->{
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
                var banner : Banner<*,*> = (holder as ViewHolderBanner).banner
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

            }
            MultiItemType.LEFTTITLEANDRIGHTMORE -> {

            }
            MultiItemType.GRID -> {

            }
        }
    }

    override fun getItemCount(): Int {
        LogUtil.debugInfo("getItemCount")

        return 20

    }

    override fun getItemViewType(position: Int): Int {
        LogUtil.debugInfo("getItemViewType")

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
            else ->{
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
        var ivOneQ :ImageView = itemView.findViewById(R.id.iv_one_quickly_item)
        var ivTwoQ :ImageView = itemView.findViewById(R.id.iv_two_quickly_item)
        var ivThrQ :ImageView = itemView.findViewById(R.id.iv_thr_quickly_item)
    }

    class ViewHolderLeftTitleAndMore(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvMore :TextView = itemView.findViewById(R.id.tv_more_title_item)
    }

    class ViewHolderGrid(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivOne :ImageView = itemView.findViewById(R.id.iv_one_sports_venues_item)
        var ivTwo :ImageView = itemView.findViewById(R.id.iv_two_sports_venues_item)
        var tvOneDesc :TextView = itemView.findViewById(R.id.tv_desc_one_sports_venues_item)
        var tvOneLocation :TextView = itemView.findViewById(R.id.tv_location_one_sports_venues_item)
        var tvTwoDesc :TextView = itemView.findViewById(R.id.tv_desc_two_sports_venues_item)
        var tvTwoLocation :TextView = itemView.findViewById(R.id.tv_location_two_sports_venues_item)
    }



}
