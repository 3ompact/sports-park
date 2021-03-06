package com.mda.component_main.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.alibaba.android.arouter.launcher.ARouter
import com.mda.basics_lib.utils.DateAndTimeUtil
import com.mda.component_main.R
import com.mda.component_main.bean.VenueDetailData
import com.qmuiteam.qmui.layout.QMUIButton
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.tab.*
import me.zhanghai.android.materialratingbar.MaterialRatingBar


/**
 *
 * 场馆详情适配器
 */

class VenueDetailActivityAdapter(context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var context: Context
    val FIXED = 4

    var venueDatailData: VenueDetailData = VenueDetailData()

    init {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            TYPE.Detail.value -> {
                return ViewHolderDetail(
                    LayoutInflater.from(context)
                        .inflate(R.layout.item_venue_detail_desc, parent, false)
                )
            }
            TYPE.Category.value -> {
                return ViewHolderCategory(
                    LayoutInflater.from(context)
                        .inflate(R.layout.item_venue_detail_venue_category, parent, false)
                )
            }
            TYPE.Service.value -> {
                return ViewHolderService(
                    LayoutInflater.from(context)
                        .inflate(R.layout.item_venue_detail_venue_service, parent, false)
                )
            }
            TYPE.Title.value -> {
                return ViewHolderTitle(
                    LayoutInflater.from(context)
                        .inflate(R.layout.item_venue_detail_title, parent, false)
                )
            }
            TYPE.Valuation.value -> {
                return ViewHolderValuation(
                    LayoutInflater.from(context)
                        .inflate(R.layout.item_venue_detail_valuation, parent, false)
                )
            }
            else -> {
                return ViewHolderValuation(
                    LayoutInflater.from(context)
                        .inflate(R.layout.item_venue_detail_valuation, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE.Detail.value -> {

//                var tvDesc = itemView.findViewById<TextView>(R.id.tv_desc_venue_detail_item)
//                var tvSelfAccount = itemView.findViewById<TextView>(R.id.tv_self_account_venue_detail_item)
//                var ll = itemView.findViewById<LinearLayout>(R.id.ll_account_venue_detail_item)
//                var rb = itemView.findViewById<MaterialRatingBar>(R.id.mrb_account_venue_detail_item)
//                var tvEvalution =
//                    itemView.findViewById<TextView>(R.id.tv_evaluate_account_venue_detail_item)
//                var tvLoc = itemView.findViewById<TextView>(R.id.tv_location_account_venue_detail_item)

//                (holder as ViewHolderDetail).rb.numStars = 3
                (holder as ViewHolderDetail).rb.rating = 3.5f
                (holder as ViewHolderDetail).rb.isClickable = false
                (holder as ViewHolderDetail).rb.setIsIndicator(true)
                holder.tvDesc.setText(venueDatailData.sysStadium!!.stadiumName)
                venueDatailData.sysStadium!!.remark?.let {
                    holder.tvSelfAccount.setText(it)
                }
                holder.rb.setIsIndicator(true)
                holder.rb.rating = venueDatailData.sysStadium!!.averageScore.toFloat()
                holder.tvEvalution.setText(venueDatailData.sysStadium!!.evaluationNumber)
                holder.tvLoc.setText("\t" + venueDatailData.sysStadium!!.stadiumAdd)

                holder.ivCall.setOnClickListener {
                    val intent = Intent(Intent.ACTION_CALL)
                    val data: Uri = Uri.parse("tel:" + venueDatailData.sysStadium!!.stadiumPhone)
                    intent.data = data
                    context.startActivity(intent)
                }
            }
            TYPE.Category.value -> {

                var selectedItem = 0
                val tabBuilder = (holder as ViewHolderCategory).tabSag.tabBuilder()
                val space: Int = QMUIDisplayHelper.dp2px(context, 16)
                tabBuilder.setDynamicChangeIconColor(true)


                (holder as ViewHolderCategory).apply {
                    tabSag.reset()

                    for (i in 0 until venueDatailData.dateVO!!.size) {
                        tabSag.addTab(
                            tabBuilder.setText(venueDatailData.dateVO!!.get(i).projectName)
                                .build(context)
                        )
                    }
//                    tabSag.addTab(tabBuilder.setText("羽毛球").build(context))
//                    tabSag.addTab(tabBuilder.setText("乒乓球").build(context))
//                    tabSag.addTab(tabBuilder.setText("篮球").build(context))
//                    tabSag.addTab(tabBuilder.setText("足球").build(context))

                    tabSag.setIndicator(
                        QMUITabIndicator(
                            QMUIDisplayHelper.dp2px(context, 2),
                            false,
                            true
                        )
                    )
                    tabSag.mode = QMUITabSegment.MODE_SCROLLABLE
//                    tabSag.setItemSpaceInScrollMode(space)
//                    tabSag.setPadding(space, 0, space, 0)
                    tabSag.selectTab(0)
                    tabSag.notifyDataChanged()
                    //tab栏设置监听
                    tabSag.addOnTabSelectedListener(object :
                        QMUIBasicTabSegment.OnTabSelectedListener {
                        override fun onTabSelected(index: Int) {
                            selectedItem = index
                        }

                        override fun onTabUnselected(index: Int) {
                        }

                        override fun onTabReselected(index: Int) {
                        }

                        override fun onDoubleTap(index: Int) {
                        }
                    })
                }

                var l = LinearLayoutManager(context)
                l.orientation = LinearLayoutManager.HORIZONTAL
                (holder as ViewHolderCategory).rv.layoutManager = l
//                (holder as ViewHolderCategory).rv.addItemDecoration(
//                    VenueActivityRecyclerHorizontalViewDecoration()
//                )
                (holder as ViewHolderCategory).rv.adapter = object :
                    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
                    override fun onCreateViewHolder(
                        parent: ViewGroup,
                        viewType: Int
                    ): RecyclerView.ViewHolder {
                        return TVd(
                            LayoutInflater.from(context).inflate(
                                R.layout.item_date_exhibition,
                                parent,
                                false
                            )
                        )
                    }

                    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                        (holder as TVd).tvBook.setOnClickListener {
                            ARouter.getInstance().build("/cm/venueselectionactivity")
                                .withLong("id",venueDatailData.dateVO!!.get(selectedItem).projectId)
                                .withString("time",venueDatailData.dateVO!!.get(selectedItem).date[position])
                                .navigation()
                        }
                        val week = DateAndTimeUtil.getDay(
                            venueDatailData.dateVO!!.get(selectedItem).date.get(position)
                        )
                        val day = DateAndTimeUtil.getDayOfMonth(
                            venueDatailData.dateVO!!.get(selectedItem).date.get(position)
                        )
                        val Month = DateAndTimeUtil.getMonth(
                            venueDatailData.dateVO!!.get(selectedItem).date.get(position)
                        )

                        when(week){
                            1->{
                                (holder as TVd).tvWeek.setText("周一")
                            }
                            2->{
                                (holder as TVd).tvWeek.setText("周二")
                            }
                            3->{
                                (holder as TVd).tvWeek.setText("周三")
                            }
                            4->{
                                (holder as TVd).tvWeek.setText("周四")
                            }
                            5->{
                                (holder as TVd).tvWeek.setText("周五")
                            }
                            6->{
                                (holder as TVd).tvWeek.setText("周六")
                            }
                            7->{
                                (holder as TVd).tvWeek.setText("周天")
                            }

                        }
//                        (holder as TVd).tvWeek.setText(week.toString())

                        (holder as TVd).tvDate.setText(Month.toString() + "-" + day.toString())

                    }

                    override fun getItemCount(): Int {
                        return venueDatailData.dateVO!!.get(selectedItem).date.size
                    }

                    inner class TVd(itemView: View) : RecyclerView.ViewHolder(itemView) {
                        var tvWeek: TextView =
                            itemView.findViewById(R.id.tv_week_date_exhibition_item)
                        var tvDate: TextView =
                            itemView.findViewById(R.id.tv_date_date_exhibition_item)
                        var tvBook: QMUIButton =
                            itemView.findViewById(R.id.qbtn_book_date_date_exhibition_item)

                    }
                }

                (holder as ViewHolderCategory).tvBusinessTime.setText(venueDatailData.sysStadium!!.businessHours)

                (holder as ViewHolderCategory).tvTrafficInfo.setText(venueDatailData.sysStadium!!.addressNavigation)

            }
            TYPE.Service.value -> {

                (holder as ViewHolderService).tvEquRental.setText(venueDatailData.sysStadium!!.equipmentRental)
                (holder as ViewHolderService).tvBath.setText(venueDatailData.sysStadium!!.bathFacilities)
                (holder as ViewHolderService).tvFacil.setText(venueDatailData.sysStadium!!.facilities)
            }
            TYPE.Title.value -> {
            }
            TYPE.Valuation.value -> {

                (holder as ViewHolderValuation).rb.setIsIndicator(true)


                (holder as ViewHolderValuation).ivHeader.load(
                    venueDatailData.stadiumEvaluationVO!!.get(
                        position-FIXED
                    ).avatarName
                ) {
                    placeholder(R.mipmap.leak_canary_icon)

                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                (holder as ViewHolderValuation).tvNickName.setText(
                    venueDatailData.stadiumEvaluationVO!!.get(
                        position-FIXED
                    ).nickName
                )
                (holder as ViewHolderValuation).rb.rating =
                    venueDatailData.stadiumEvaluationVO!!.get(position-FIXED).score.toFloat()
                (holder as ViewHolderValuation).tvValua!!.setText(
                    venueDatailData.stadiumEvaluationVO!!.get(
                        position-FIXED
                    ).evaluationContent
                )
//                (holder as ViewHolderValuation).tvDate.setText(
//                    DateAndTimeUtil.getYearMonthDay(venueDatailData.stadiumEvaluationVO!!.get(position-FIXED).createDate.toString())
//                )
                (holder as ViewHolderValuation).tvDate.setText(
                    venueDatailData.stadiumEvaluationVO!!.get(position-FIXED).createDate.substring(0,11)
                )
                venueDatailData.stadiumEvaluationVO!!.get(position-FIXED).pictureAdd?.let {
                    if (it.size > 0) {
                        for (i in 0 until it.size) {
                            when (i) {
                                0 -> {
                                    (holder as ViewHolderValuation).ivQickOne.load(it[0]) {
                                        placeholder(R.mipmap.leak_canary_icon)
                                        crossfade(true)
                                        transformations(
                                            RoundedCornersTransformation(
                                                10f,
                                                10f,
                                                10f,
                                                10f
                                            )
                                        )
                                    }

                                }
                                1 -> {
                                    (holder as ViewHolderValuation).ivQickTwo.load(it[1]) {
                                        placeholder(R.mipmap.leak_canary_icon)
                                        crossfade(true)
                                        transformations(
                                            RoundedCornersTransformation(
                                                10f,
                                                10f,
                                                10f,
                                                10f
                                            )
                                        )
                                    }

                                }
                                2 -> {
                                    (holder as ViewHolderValuation).ivQickTHr.load(it[2]) {
                                        placeholder(R.mipmap.leak_canary_icon)
                                        crossfade(true)
                                        transformations(
                                            RoundedCornersTransformation(
                                                10f,
                                                10f,
                                                10f,
                                                10f
                                            )
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }
            else -> {

                (holder as ViewHolderValuation).rb.setIsIndicator(true)

                (holder as ViewHolderValuation).ivHeader.load(
                    venueDatailData.stadiumEvaluationVO!!.get(
                        position-FIXED
                    ).avatarName
                ) {
                    placeholder(R.mipmap.leak_canary_icon)

                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                (holder as ViewHolderValuation).tvNickName.setText(
                    venueDatailData.stadiumEvaluationVO!!.get(
                        position-FIXED
                    ).nickName
                )
                (holder as ViewHolderValuation).rb.rating =
                    venueDatailData.stadiumEvaluationVO!!.get(position-FIXED).score.toFloat()
                (holder as ViewHolderValuation).tvValua!!.setText(
                    venueDatailData.stadiumEvaluationVO!!.get(
                        position-FIXED
                    ).evaluationContent
                )
//                (holder as ViewHolderValuation).tvDate.setText(
//                    DateAndTimeUtil.getYearMonthDay(venueDatailData.stadiumEvaluationVO!!.get(position-FIXED).createDate.toString())
//                )
                (holder as ViewHolderValuation).tvDate.setText(
                   venueDatailData.stadiumEvaluationVO!!.get(position-FIXED).createDate.substring(0,11)
                )
                venueDatailData.stadiumEvaluationVO!!.get(position-FIXED).pictureAdd?.let {
                    if (it.size > 0) {
                        for (i in 0 until it.size) {
                            when (i) {
                                0 -> {
                                    (holder as ViewHolderValuation).ivQickOne.load(it[0]) {
                                        placeholder(R.mipmap.leak_canary_icon)
                                        crossfade(true)
                                        transformations(
                                            RoundedCornersTransformation(
                                                10f,
                                                10f,
                                                10f,
                                                10f
                                            )
                                        )
                                    }

                                }
                                1 -> {
                                    (holder as ViewHolderValuation).ivQickTwo.load(it[1]) {
                                        placeholder(R.mipmap.leak_canary_icon)
                                        crossfade(true)
                                        transformations(
                                            RoundedCornersTransformation(
                                                10f,
                                                10f,
                                                10f,
                                                10f
                                            )
                                        )
                                    }

                                }
                                2 -> {
                                    (holder as ViewHolderValuation).ivQickTHr.load(it[2]) {
                                        placeholder(R.mipmap.leak_canary_icon)
                                        crossfade(true)
                                        transformations(
                                            RoundedCornersTransformation(
                                                10f,
                                                10f,
                                                10f,
                                                10f
                                            )
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }

        }
    }

    override fun getItemCount(): Int {
        if(venueDatailData != null){
            if (venueDatailData.sysStadium!=null) {

                return venueDatailData.stadiumEvaluationVO!!.size + FIXED
            } else {
                return 0
            }
        }else{
            return 0
        }
    }

    /**
     *
     * 对适配器设置数据
     */
    fun setData(venueDatailData: VenueDetailData) {
        this.venueDatailData = venueDatailData
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {

        when (position) {
            0 -> {
                return TYPE.Detail.value
            }
            1 -> {
                return TYPE.Category.value
            }
            2 -> {
                return TYPE.Service.value
            }
            3 -> {
                return TYPE.Title.value
            }
            4 -> {
                return TYPE.Valuation.value
            }
            else -> {
                return TYPE.Valuation.value
            }
        }


    }


    class ViewHolderDetail(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDesc = itemView.findViewById<TextView>(R.id.tv_desc_venue_detail_item)
        var tvSelfAccount = itemView.findViewById<TextView>(R.id.tv_self_account_venue_detail_item)
        var ll = itemView.findViewById<LinearLayout>(R.id.ll_account_venue_detail_item)
        var rb = itemView.findViewById<MaterialRatingBar>(R.id.mrb_account_venue_detail_item)
        var tvEvalution =
            itemView.findViewById<TextView>(R.id.tv_evaluate_account_venue_detail_item)
        var tvLoc = itemView.findViewById<TextView>(R.id.tv_location_account_venue_detail_item)
        var ivCall = itemView.findViewById<ImageView>(R.id.iv_call_venue_detail_item)

    }

    class ViewHolderCategory(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tabSag =
            itemView.findViewById<QMUITabSegment2>(R.id.tabSegment_venue_detail_category_item)
        var rv = itemView.findViewById<RecyclerView>(R.id.rv_venue_detail_category_item)
        var tvBusinessTime =
            itemView.findViewById<TextView>(R.id.tv_business_hours_desc_venue_detail_category_item)
        var tvTrafficInfo =
            itemView.findViewById<TextView>(R.id.tv_traffic_info_desc_venue_detail_category_item)

    }

    class ViewHolderService(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvEquRental =
            itemView.findViewById<TextView>(R.id.tv_equipment_rental_desc_venue_detail_category_item)
        var tvBath =
            itemView.findViewById<TextView>(R.id.tv_bath_facilities_desc_venue_detail_category_item)
        var tvFacil =
            itemView.findViewById<TextView>(R.id.tv_venue_facilities_desc_venue_detail_category_item)

    }

    class ViewHolderTitle(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle = itemView.findViewById<TextView>(R.id.tv_titile_venue_detail_item)

    }

    class ViewHolderValuation(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivHeader = itemView.findViewById<ImageView>(R.id.iv_header_valuation_venue_detail_item)
        var tvNickName =
            itemView.findViewById<TextView>(R.id.tv_nick_name_valuation_venue_detail_item)
        var rb = itemView.findViewById<MaterialRatingBar>(R.id.mrb_valuation_venue_detail_item)
        var tvValua = itemView.findViewById<TextView>(R.id.tv_valuation_valuation_venue_detail_item)
        var tvDate = itemView.findViewById<TextView>(R.id.tv_date_valuation_venue_detail_item)
        var ivQickOne =
            itemView.findViewById<ImageView>(R.id.iv_pho_one_valuation_venue_detail_item)
        var ivQickTwo =
            itemView.findViewById<ImageView>(R.id.iv_pho_two_valuation_venue_detail_item)
        var ivQickTHr =
            itemView.findViewById<ImageView>(R.id.iv_pho_thr_valuation_venue_detail_item)

    }

    enum class TYPE(val value: Int) {
        Detail(1),
        Category(2),
        Service(3),
        Title(4),
        Valuation(5);

        fun getValues(): Int {
            return value
        }
    }
}