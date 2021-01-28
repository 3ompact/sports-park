package com.mda.component_main.ui.activity

import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.opengl.Visibility
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import coil.load
import com.alibaba.android.arouter.facade.annotation.Route
import com.mda.common_ui_base.base.BaseVMDBActivity
import com.mda.common_ui_base.base.BaseViewModel
import com.mda.component_main.R
import com.mda.component_main.adapter.VenueDetailActivityAdapter
import com.mda.component_main.databinding.ActivityVenueDetailBinding
import com.mda.component_main.decoration.VenueActivityRecyclerViewDecoration
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.*
import com.qmuiteam.qmui.widget.QMUICollapsingTopBarLayout.GONE
import com.qmuiteam.qmui.widget.QMUICollapsingTopBarLayout.OnOffsetUpdateListener
import java.util.*

/**
 * 场馆详情 activity
 */
@Route(path = "/cm/venuedetailactivity")
class VenueDetailActivity : BaseVMDBActivity<BaseViewModel, ActivityVenueDetailBinding>() {
    private val mItems: MutableList<String> = ArrayList()
    private lateinit var mViewPager: QMUIViewPager
    private lateinit var mTopBar: QMUITopBar
    private lateinit var mCollTopBarLayout: QMUICollapsingTopBarLayout
    private lateinit var rv: RecyclerView
    private lateinit var tvProgress: TextView
    override fun layoutId(): Int {
        return R.layout.activity_venue_detail
    }

    override fun actionBar(): Boolean {
        return true
    }

    override fun initView() {

        mViewPager = mDataBinding.pagerVenueDetailActivity
        mTopBar = mDataBinding.topbarVenueDetailActivity
        mCollTopBarLayout = mDataBinding.ctbVenueDetailActivity
        tvProgress = mDataBinding.tvProgressVenueDetailActivity

        mCollTopBarLayout.setTitle(
            "test"
        )
        mTopBar.addLeftBackImageButton()

        mCollTopBarLayout.setScrimUpdateListener(AnimatorUpdateListener { animation ->

            tvProgress.alpha = 1 - animation.animatedValue.toString().toFloat() / 255

//            if (animation.animatedValue.toString().toInt() > 250) {
//
//                tvProgress.visibility = GONE
//            } else if (animation.animatedValue.toString().toInt() < 60) {
//                tvProgress.visibility = VISIBLE
//
//            }
            Log.i(
                "3ompact",
                "scrim: " + animation.animatedValue + "test" + animation.animatedValue.toString()
                    .toInt()
            )
        })

        mCollTopBarLayout.addOnOffsetUpdateListener(OnOffsetUpdateListener { layout, offset, expandFraction ->
            Log.i(
                "3ompact",
                "offset = $offset; expandFraction = $expandFraction"
            )
        })

        rv = mDataBinding.rvVenueDetailActivity

        var lp1 = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        lp1.rightMargin = 10
        lp1.leftMargin = 20

        lp1.addRule(RelativeLayout.CENTER_VERTICAL)
        lp1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)

        var lp2 = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        lp2.rightMargin = 20
        lp2.addRule(RelativeLayout.CENTER_VERTICAL)
        lp2.addRule(RelativeLayout.LEFT_OF, R.id.iv_share_venue_detail_activity)

//        mTopBar.setTitle(R.string.venue_detail)
        var ibStar = mTopBar.addRightImageButton(
            R.drawable.icon_star_40,
            R.id.iv_start_venue_detail_activity
        )
        var ibShare = mTopBar.addRightImageButton(
            R.drawable.icon_share_40,
            R.id.iv_share_venue_detail_activity
        )

        ibStar.layoutParams = lp2
        ibShare.layoutParams = lp1


        mTopBar.addLeftBackImageButton()

        rv.addItemDecoration(VenueActivityRecyclerViewDecoration())
        rv.layoutManager = LinearLayoutManager(this@VenueDetailActivity)
        var adapter = VenueDetailActivityAdapter(this@VenueDetailActivity)
        rv.adapter = adapter

        for (i in 0 until 5) {
            mItems.add(i.toString())
        }

        val testUrl = arrayOf(
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=114948211,1911580438&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1188367699,951642438&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2854078786,3290477872&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=358219555,1592757067&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=101138672,367522421&fm=26&gp=0.jpg"
        )

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
                itemView.setImageUrl(testUrl[position])
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

    class ItemView(context: Context?) : FrameLayout(context!!) {
        private val iv: ImageView

        fun setImageUrl(url: String) {
            iv.load(url)
        }

        init {
            iv = ImageView(context)
            iv.scaleType = ImageView.ScaleType.CENTER_CROP
            val size = QMUIDisplayHelper.dp2px(context, 300)
            val lp = LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            addView(iv, lp)
        }
    }

    class CardTransformer : ViewPager.PageTransformer {
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