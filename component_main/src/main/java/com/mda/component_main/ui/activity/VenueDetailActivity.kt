package com.mda.component_main.ui.activity

import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.mda.common_ui_base.base.BaseVMDBActivity
import com.mda.common_ui_base.base.BaseViewModel
import com.mda.component_main.R
import com.mda.component_main.adapter.HomeFragmentWithoutThrAdapter
import com.mda.component_main.adapter.VenueDetailActivityAdapter
import com.mda.component_main.databinding.ActivityVenueDetailBinding
import com.mda.component_main.decoration.HomeFragmentRecyclerViewDecoration
import com.mda.component_main.decoration.VenueActivityRecyclerViewDecoration
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.QMUIPagerAdapter
import com.qmuiteam.qmui.widget.QMUIViewPager
import java.util.*

/**
 * 场馆详情 activity
 */
@Route(path = "/cm/venuedetailactivity")
class VenueDetailActivity : BaseVMDBActivity<BaseViewModel, ActivityVenueDetailBinding>() {
    private val mItems: MutableList<String> = ArrayList()
    private lateinit var mViewPager: QMUIViewPager

    override fun layoutId(): Int {
        return R.layout.activity_venue_detail
    }

    override fun actionBar(): Boolean {
        return true
    }

    override fun initView() {
        mViewPager = mDataBinding.pagerVenueDetailActivity

        var rv = mDataBinding.rvVenueDetailActivity

        rv.addItemDecoration(VenueActivityRecyclerViewDecoration())
        rv.layoutManager = LinearLayoutManager(this@VenueDetailActivity)
        var adapter = VenueDetailActivityAdapter(this@VenueDetailActivity)
        rv.adapter = adapter

        for (i in 0 until 5) {
            mItems.add(i.toString())
        }

        val pagerAdapter: QMUIPagerAdapter = object : QMUIPagerAdapter() {
            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view === `object`
            }

            override fun getCount(): Int {
                return mItems.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return mItems.get(position)
            }

            override fun hydrate(container: ViewGroup, position: Int): Any {
                return ItemView(this@VenueDetailActivity)
            }

            override fun populate(container: ViewGroup, item: Any, position: Int) {

                var itemView = item as (ItemView)
                itemView.setText(mItems[position])
                container.addView(itemView)

            }

            override fun destroy(container: ViewGroup, position: Int, `object`: Any) {
                container.removeView(`object` as View)
            }
        }

        //setPageTransformer默认采用ViewCompat.LAYER_TYPE_HARDWARE， 但它在某些4.x的国产机下会crash
        val canUseHardware = Build.VERSION.SDK_INT >= 21
        mViewPager.setPageTransformer(
            false, CardTransformer(),
            if (canUseHardware) ViewCompat.LAYER_TYPE_HARDWARE else ViewCompat.LAYER_TYPE_SOFTWARE
        )
        mViewPager.setInfiniteRatio(500)
        mViewPager.setEnableLoop(true)
        mViewPager.setAdapter(pagerAdapter)


    }

    override fun initData() {
    }

    override fun showLoading(message: String) {
    }

    override fun dismissLoading() {
    }

    override fun createObserver() {
    }

    internal class ItemView(context: Context?) : FrameLayout(context!!) {
        private val mTextView: TextView
        fun setText(text: CharSequence?) {
            mTextView.text = text
        }

        init {
            mTextView = TextView(context)
            mTextView.textSize = 20f
            mTextView.setTextColor(ContextCompat.getColor(context!!, R.color.purple_200))
            mTextView.gravity = Gravity.CENTER
            mTextView.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.qmui_config_color_white
                )
            )
            val size = QMUIDisplayHelper.dp2px(context, 300)
            val lp = LayoutParams(size, size)
            lp.gravity = Gravity.CENTER
            addView(mTextView, lp)
        }
    }

    inner class CardTransformer : ViewPager.PageTransformer{
        override fun transformPage(page: View, position: Float) {
            // 刷新数据notifyDataSetChange之后也会调用到transformPage，但此时的position可能不在[-1, 1]之间
            if (position <= -1 || position >= 1f) {
                page.rotation = 0f
            } else {
                page.rotation = position * 30
                page.pivotX = page.width * .5f
                page.pivotY = page.height * 1f
            }
        }
    }
}