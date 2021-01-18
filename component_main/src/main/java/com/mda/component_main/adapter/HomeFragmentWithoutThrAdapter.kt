package com.mda.component_main.adapter

import android.R.attr.banner
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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


                return ViewHolderSearchBar(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_banner,
                        parent,
                        false
                    )
                )

            }
            MultiItemType.FIXED -> {
                return ViewHolderSearchBar(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_fixed,
                        parent,
                        false
                    )
                )

            }
            MultiItemType.QUCIKLY -> {
                return ViewHolderSearchBar(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_quickly,
                        parent,
                        false
                    )
                )

            }
            MultiItemType.LEFTTITLEANDRIGHTMORE -> {
                return ViewHolderSearchBar(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_title,
                        parent,
                        false
                    )
                )

            }
            MultiItemType.GRID -> {
                return ViewHolderSearchBar(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_sports_venues_grid,
                        parent,
                        false
                    )
                )

            }
            else ->{
                return ViewHolderSearchBar(
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
                (holder.itemView as ViewHolderSearchBar).etSB.setHint("搜索体育场馆")
            }
            MultiItemType.BANNER -> {
                var banner = (holder.itemView as ViewHolderBanner).banner
                banner.setAdapter(ImageNetAdapter(ArrayList<Any>()) as Nothing?)
                banner.setBannerRound(BannerUtils.dp2px(5f))
                banner.setIndicator(RoundLinesIndicator(context))
                banner.setIndicatorSelectedWidth(BannerUtils.dp2px(15f).toInt())
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
        return 10

    }

    override fun getItemViewType(position: Int): Int {
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
        var ivThrQ :ImageView = itemView.findViewById(R.id.iv_thr_fixed_item)
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
