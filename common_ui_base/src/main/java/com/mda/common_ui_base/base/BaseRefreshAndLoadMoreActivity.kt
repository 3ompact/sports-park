package com.mda.common_ui_base.base

import androidx.recyclerview.widget.RecyclerView
import com.mda.common_ui_base.R
import com.mda.common_ui_base.databinding.ActivityBaseRefreshAndLoadMoreBinding
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.qmuiteam.qmui.widget.pullLayout.QMUIPullLayout

/**
 * 基础下拉上拉刷新Activity
 *
 * 使用 registerForActiivtyResult
 * 代替onActivityResult
 */
abstract class BaseRefreshAndLoadMoreActivity<VM : BaseViewModel, DB : ActivityBaseRefreshAndLoadMoreBinding> :
    BaseVMDBActivity<VM, DB>() {

    private lateinit var mTopBar: QMUITopBarLayout
    private lateinit var mPullLayout: QMUIPullLayout
    private lateinit var mRecyclerView: RecyclerView

    override fun layoutId(): Int {
        return R.layout.fragment_base_refresh_and_load_more
    }

    override fun initView() {
        mTopBar = mDataBinding.topbarBaseRefreshAndLoadMoreActivity
        mPullLayout = mDataBinding.pullLayoutBaseRefreshAndLoadMoreActivity
        mRecyclerView = mDataBinding.recyclerViewBaseRefreshAndLoadMoreActivity
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
//            mPullLayout.finishActionRun(pullAction)
        }
    }

    //设置recyclerview的adapter
    abstract fun setAdapter()

    fun getRecyclerView(): RecyclerView {
        return mRecyclerView
    }

    abstract fun doFinishActionRun(pullAction: QMUIPullLayout.PullAction)
    //设置刷新数据
    abstract fun onRefreshData(pullAction : QMUIPullLayout.PullAction)
    //设置加载更多
    abstract fun onLoadMore(pullAction : QMUIPullLayout.PullAction)

    abstract fun <T : RecyclerView.ViewHolder> onDataLoaded(adapter: RecyclerView.Adapter<T>)

    //加载dialog
    override fun showLoading(message: String) {
    }



    //关闭dialog
    override fun dismissLoading() {
    }

    //QMUIPULLLAYOUT刷新或是加载完成
    fun finishRefresh(pullAction : QMUIPullLayout.PullAction){
        mPullLayout.finishActionRun(pullAction)
    }



}