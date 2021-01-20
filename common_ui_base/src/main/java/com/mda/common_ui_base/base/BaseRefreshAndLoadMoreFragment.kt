package com.mda.common_ui_base.base

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.mda.common_ui_base.R
import com.mda.common_ui_base.databinding.FragmentBaseRefreshAndLoadMoreBaseUiBinding
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.qmuiteam.qmui.widget.pullLayout.QMUIPullLayout

/**
 * 基础上拉下拉刷新fragment
 *
 */
abstract class BaseRefreshAndLoadMoreFragment<VM : BaseViewModel, DB : FragmentBaseRefreshAndLoadMoreBaseUiBinding> :
    BaseVMDBFragment<VM, DB>() {

    private lateinit var mTopBar: QMUITopBarLayout
    private lateinit var mPullLayout: QMUIPullLayout
    private lateinit var mRecyclerView: RecyclerView

    override fun layoutId(): Int {
        return R.layout.fragment_base_refresh_and_load_more_base_ui
    }

    override fun initView(savedInstanceState: Bundle?) {
        mTopBar = mDataBinding.topbarBaseRefreshAndLoadMoreFragment
        mPullLayout = mDataBinding.pullLayoutBaseRefreshAndLoadMoreFragment
        mRecyclerView = mDataBinding.recyclerViewBaseRefreshAndLoadMoreFragment
        //设置上啦下拉监听
        setPullLayoutActionListener()
    }

    fun setPullLayoutActionListener() {
        mPullLayout.setActionListener { pullAction ->
            if (pullAction.pullEdge == QMUIPullLayout.PULL_EDGE_TOP) {
                onRefreshData(pullAction)
            } else if (pullAction.pullEdge == QMUIPullLayout.PULL_EDGE_BOTTOM) {
                onLoadMore(pullAction)
            }
            mPullLayout.finishActionRun(pullAction)
        }
    }

    //设置recyclerview的adapter
    abstract fun setAdapter()

    fun getRecyclerView(): RecyclerView {
        return mRecyclerView
    }

    abstract fun doFinishActionRun(pullAction: QMUIPullLayout.PullAction)

    abstract fun onRefreshData(pullAction : QMUIPullLayout.PullAction)

    abstract fun onLoadMore(pullAction : QMUIPullLayout.PullAction)

    abstract fun <T : RecyclerView.ViewHolder> onDataLoaded(adapter: RecyclerView.Adapter<T>)

    override fun lazyLoadData() {
    }

    override fun showLoading(message: String) {
    }

    override fun dismissLoading() {
    }

    //QMUIPULLLAYOUT刷新或是加载完成
    fun finishRefresh(pullAction : QMUIPullLayout.PullAction){
        mPullLayout.finishActionRun(pullAction)
    }
}