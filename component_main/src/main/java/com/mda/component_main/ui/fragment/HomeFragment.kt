package com.mda.component_main.ui.fragment

import android.os.Bundle
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.mda.basics_lib.`interface`.OnResponseListener
import com.mda.basics_lib.log.LogUtil
import com.mda.common_ui_base.base.AbBaseRefreshAndLoadMoreFragment
import com.mda.component_main.R
import com.mda.component_main.adapter.HomeFragmentWithoutThrAdapter
import com.mda.component_main.bean.HomeFragmentEncapsulation
import com.mda.component_main.bean.VenueSummary
import com.mda.component_main.databinding.FragmentHomeCmBinding
import com.mda.component_main.decoration.HomeFragmentRecyclerViewDecoration
import com.mda.component_main.viewmodel.HomeFragmentViewModel
import com.qmuiteam.qmui.recyclerView.QMUIRVDraggableScrollBar
import com.qmuiteam.qmui.recyclerView.QMUIRVItemSwipeAction
import com.qmuiteam.qmui.widget.pullLayout.QMUIPullLayout

@Route(path = "/cm/homefragment")
class HomeFragment :
    AbBaseRefreshAndLoadMoreFragment<HomeFragmentViewModel, FragmentHomeCmBinding>() {
    var datasList: MutableList<VenueSummary> = mutableListOf()
    lateinit var adapter: HomeFragmentWithoutThrAdapter
    override fun layoutId(): Int {
        return R.layout.fragment_home_cm
    }

    override fun initView(savedInstanceState: Bundle?) {
        mTopBar = mDataBinding.topbarBaseRefreshAndLoadMoreCmHomeFragment
        mPullLayout = mDataBinding.pullLayoutBaseRefreshAndLoadMoreCmHomeFragment
        mRecyclerView = mDataBinding.rvCmHomeFragment


        initTopBar()
        setAdapter()
        setPullLayoutActionListener()


    }


    private fun initTopBar() {
        mDataBinding.topbarBaseRefreshAndLoadMoreCmHomeFragment.addLeftBackImageButton()
            .setOnClickListener { }

        mDataBinding.emptyviewBaseRefreshAndLoadMoreCmHomeFragment.hide()
        //进行topbar 相关设置
        mTopBar.setTitle("首页")
//        mTopBar.setBackgroundColor(Color.parseColor("#333333"))
        mTopBar.removeAllLeftViews()
        //设置分离器颜色
        mTopBar.updateBottomDivider(0, 0, 0, R.color.white_without_alpha)
        var button = mTopBar.addLeftTextButton("重庆市\t∨", R.id.location_home_fragemnt)
        button.textSize = 10f
        var lp = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        lp.leftMargin = 10
        lp.addRule(RelativeLayout.CENTER_VERTICAL)

        button.layoutParams = lp
        val scrollBar = QMUIRVDraggableScrollBar(0, 0, 0)
        scrollBar.isEnableScrollBarFadeInOut = true
        scrollBar.attachToRecyclerView(mRecyclerView)

        val swipeAction = QMUIRVItemSwipeAction(true, object : QMUIRVItemSwipeAction.Callback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                mAdapter.remove(viewHolder.adapterPosition)
            }

            override fun getSwipeDirection(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return QMUIRVItemSwipeAction.SWIPE_RIGHT
            }
        })
        //暂时去掉侧滑删除功能
//        swipeAction.attachToRecyclerView(mRecyclerView)

        mRecyclerView.layoutManager = object : LinearLayoutManager(
            context
        ) {
            override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
                return RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }
    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {
    }

    override fun dismissLoading() {
    }

    override fun setAdapter() {
        mRecyclerView.addItemDecoration(HomeFragmentRecyclerViewDecoration())
        adapter = context?.let { HomeFragmentWithoutThrAdapter(it) }!!
        mRecyclerView.adapter = adapter
    }

    override fun doFinishActionRun(pullAction: QMUIPullLayout.PullAction) {
        finishRefresh(pullAction)
    }

    //TODO刷新逻辑
    override fun onRefreshData(pullAction: QMUIPullLayout.PullAction) {
//        finishRefresh(pullAction)
        mPullLayout.postDelayed({ doFinishActionRun(pullAction) }, 1000)

    }

    //TODO加载逻辑
    override fun onLoadMore(pullAction: QMUIPullLayout.PullAction) {
//        finishRefresh(pullAction)
        mPullLayout.postDelayed({ doFinishActionRun(pullAction) }, 1000)

    }

    //初始化数据
    override fun initData() {
        super.initData()
        mViewModel.getSummaryData(object : OnResponseListener<HomeFragmentEncapsulation> {
            override fun onResult(t: HomeFragmentEncapsulation) {
                LogUtil.debugInfo("t.total" + t.total)
                adapter.setData(t.list)
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

    override fun <T : RecyclerView.ViewHolder> onDataLoaded(adapter: RecyclerView.Adapter<T>) {
    }
}