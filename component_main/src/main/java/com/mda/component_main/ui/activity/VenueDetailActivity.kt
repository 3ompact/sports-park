package com.mda.component_main.ui.activity

import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.Color
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
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import coil.load
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.mda.basics_lib.`interface`.OnResponseListener
import com.mda.basics_lib.log.LogUtil
import com.mda.common_ui_base.base.BaseVMDBActivity
import com.mda.common_ui_base.base.BaseViewModel
import com.mda.component_main.R
import com.mda.component_main.adapter.VenueDetailActivityAdapter
import com.mda.component_main.bean.VenueDetailData
import com.mda.component_main.databinding.ActivityVenueDetailBinding
import com.mda.component_main.decoration.VenueActivityRecyclerViewDecoration
import com.mda.component_main.viewmodel.VenueDetailActivityModel
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.*
import com.qmuiteam.qmui.widget.QMUICollapsingTopBarLayout.GONE
import com.qmuiteam.qmui.widget.QMUICollapsingTopBarLayout.OnOffsetUpdateListener
import java.util.*
import kotlin.collections.ArrayList

/**
 * 场馆详情 activity
 */
@Route(path = "/cm/venuedetailactivity")
class VenueDetailActivity : BaseVMDBActivity<VenueDetailActivityModel, ActivityVenueDetailBinding>() {
    private val mItems: MutableList<String> = ArrayList()
    private lateinit var mViewPager: QMUIViewPager
    private lateinit var mTopBar: QMUITopBar
    private lateinit var mCollTopBarLayout: QMUICollapsingTopBarLayout
    private lateinit var rv: RecyclerView
    private lateinit var tvProgress: TextView
    private lateinit var appBarLayout: QMUIAppBarLayout

    private lateinit var emptyView: QMUIEmptyView
    private lateinit var adapter :VenueDetailActivityAdapter
    private val venuePicsList:ArrayList<String> = ArrayList()
    @JvmField
    @Autowired
    var id :Long = 0
    //    private lateinit var emptyView : QMUIEmptyView
    override fun layoutId(): Int {
        return R.layout.activity_venue_detail
    }

    override fun actionBar(): Boolean {
        return true
    }

    /**
     * 控件初始化
     */
    override fun initView() {
        mViewPager = mDataBinding.pagerVenueDetailActivity
        mTopBar = mDataBinding.topbarVenueDetailActivity
        mCollTopBarLayout = mDataBinding.ctbVenueDetailActivity
        tvProgress = mDataBinding.tvProgressVenueDetailActivity
        appBarLayout = mDataBinding.appbarlayoutVenueDetailActivity
        emptyView = mDataBinding.emptyviewVenueDetailActivity
//        mCollTopBarLayout.setTitle(
//            "test"
//        )

        emptyView.hide()

        mTopBar.addLeftBackImageButton().setOnClickListener {
            finish()
        }

        mCollTopBarLayout.setScrimUpdateListener(AnimatorUpdateListener { animation ->
            val alpha = 1 - animation.animatedValue.toString().toFloat() / 255
            tvProgress.alpha = alpha
//            appBarLayout.alpha =  alpha

            if (animation.animatedValue.toString().toInt() > 250) {
                mViewPager.visibility = GONE
                mTopBar.removeAllLeftViews()
                mTopBar.addLeftBackImageButton()
                    .setImageDrawable(resources.getDrawable(R.drawable.icon_left_back))
            } else if (animation.animatedValue.toString().toInt() < 60) {
                mViewPager.visibility = VISIBLE
                mTopBar.removeAllLeftViews()
                mTopBar.addLeftBackImageButton()
            }


        })

        mCollTopBarLayout.addOnOffsetUpdateListener(OnOffsetUpdateListener { layout, offset, expandFraction ->

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


        rv.addItemDecoration(VenueActivityRecyclerViewDecoration())
        rv.layoutManager = LinearLayoutManager(this@VenueDetailActivity)
        adapter = VenueDetailActivityAdapter(this@VenueDetailActivity)
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
        //场馆图片地址数据
        venuePicsList.addAll(testUrl)
        //初始图片个数指示
        tvProgress.setText((1%venuePicsList.size).toString()+"/"+venuePicsList.size)

        val pagerAdapter: QMUIPagerAdapter = object : QMUIPagerAdapter() {
            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view === `object`
            }

            override fun getCount(): Int {
                return testUrl.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                LogUtil.debugInfo("position",position.toString())

                return testUrl.get(position)
            }

            override fun hydrate(container: ViewGroup, position: Int): Any {
                return ItemView(this@VenueDetailActivity)
            }

            override fun populate(container: ViewGroup, item: Any, position: Int) {

                var itemView = item as (ItemView)
                itemView.setTag(position)
                itemView.setImageUrl(testUrl.get(position),venuePicsList,tvProgress)
                container.addView(itemView)
//                itemView.sett

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
        mViewPager.setEnableLoop(false)
        mViewPager.setAdapter(pagerAdapter)
        mViewPager.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                LogUtil.debugInfo("debugInfo" + position)

            }

            override fun onPageSelected(position: Int) {
                //对当前图片选择进行显示
                LogUtil.debugInfo("debugInfo-onPageSelected" + position)
                if((position+1)%venuePicsList.size== 0){
                    if((position+1)/venuePicsList.size!=0){
                        tvProgress.setText(venuePicsList.size.toString()+"/"+venuePicsList.size)

                    }else{
                        tvProgress.setText((1%venuePicsList.size).toString()+"/"+venuePicsList.size)

                    }
                }else{
                    tvProgress.setText(((position+1)%venuePicsList.size).toString()+"/"+venuePicsList.size)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
//                LogUtil.debugInfo("debugInfo" + "test-success")

            }
        })
//        mViewPager.

    }

    /**
     * 初始化数据
     */
    override fun initData() {
        mViewModel.getVenueDetail(id,object:OnResponseListener<VenueDetailData>{
            override fun onResult(t: VenueDetailData) {
                adapter.setData(t)
                LogUtil.debugInfo("3ompact" + "test-success")

            }

            override fun onError(msg: String) {

                LogUtil.debugInfo("t.onError" + msg)

            }

            override fun onException(msg: String) {
                LogUtil.debugInfo("t.onException" + msg)

            }

            override fun onMsg(msg: String) {
                LogUtil.debugInfo("msg" + msg)

            }
        })

    }

    override fun showLoading(message: String) {
    }

    override fun dismissLoading() {
    }

    override fun createObserver() {
    }

    /**
     * 场馆总概览图相关辅助类
     */
    class ItemView(context: Context?) : FrameLayout(context!!) {
        private val iv: ImageView
        private var url:String = ""
        private val tv: TextView


        fun setImageUrl(url: String,venuePicsList: ArrayList<String>,tvProgress:TextView) {
            this.url = url
            LogUtil.debugInfo("position-url",url)

            iv.load(url)

            //对图片顺序进行排序
            for(i in 0 until venuePicsList.size){
                if(url.equals(venuePicsList.get(i))){
//                    tvProgress.setText((i+1).toString()+"/"+venuePicsList.size)
                    tv.setText((i+1).toString()+"/"+venuePicsList.size)
                    LogUtil.debugInfo("position-1",tag.toString())

                }
            }
        }

        fun getImageUrl():String {
            return url
        }
        init {
            iv = ImageView(context)
            tv = TextView(context)
            tv.setTextColor(Color.parseColor("#ffffff"))
            tv.setBackgroundResource(R.drawable.shape_backgorund_venue_detail_vp_textindicator)
            tv.gravity = Gravity.CENTER

            iv.scaleType = ImageView.ScaleType.CENTER_CROP
            val size = QMUIDisplayHelper.dp2px(context, 300)
            val lp = LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            val lpTV = LayoutParams(
                QMUIDisplayHelper.dp2px(context, 40),
                QMUIDisplayHelper.dp2px(context, 20)
            )
            //and  Gravity.RIGHT
            lpTV.gravity = Gravity.BOTTOM or Gravity.RIGHT
            lpTV.setMargins(0,0,QMUIDisplayHelper.dp2px(context, 15),QMUIDisplayHelper.dp2px(context, 30))
            addView(iv, lp)
            //去掉图片张数指示器
//            addView(tv, lpTV)

        }
    }

    //viewpager 过度模式
    class CardTransformer : ViewPager.PageTransformer {
        override fun transformPage(page: View, position: Float) {
            // 刷新数据notifyDataSetChange之后也会调用到transformPage，但此时的position可能不在[-1, 1]之间
            if (position <= -1 || position >= 1f) {
                page.rotation = 0f
            } else {
//                page.rotation = position * 30
                page.pivotX = page.width * .5f
                page.pivotY = page.height * 1f
            }
        }
    }
}