package com.mda.common_ui_base.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.qmuiteam.qmui.widget.pullLayout.QMUIPullLayout
import kotlinx.coroutines.*

/**
 * 基础上拉下拉刷新fragment
 *
 */
abstract class AbBaseRefreshAndLoadMoreFragment<VM : BaseViewModel, DB : ViewDataBinding> :
    BaseVMDBFragment<VM, DB>() {

    lateinit var mTopBar: QMUITopBarLayout
    lateinit var mPullLayout: QMUIPullLayout
    lateinit var mRecyclerView: RecyclerView

//    override fun layoutId(): Int {
//        return R.layout.fragment_base_refresh_and_load_more
//    }

    override fun initView(savedInstanceState: Bundle?) {


    }

    //设置上啦下拉监听  mPullLayout.finishActionRun(pullAction)暂时测试的结果是需要有时间间隔才能完成刷新动作
    fun setPullLayoutActionListener() {
        mPullLayout.setActionListener { pullAction ->
            if (pullAction.pullEdge == QMUIPullLayout.PULL_EDGE_TOP) {
//                onRefreshData(pullAction)


            } else if (pullAction.pullEdge == QMUIPullLayout.PULL_EDGE_BOTTOM) {
                onLoadMore(pullAction)
            }
            mPullLayout.finishActionRun(pullAction)

        }

//
//        mPullLayout.setActionListener { pullAction ->
//            mPullLayout.postDelayed({
//                if (pullAction.pullEdge == QMUIPullLayout.PULL_EDGE_TOP) {
////                    onRefreshData()
//                } else if (pullAction.pullEdge == QMUIPullLayout.PULL_EDGE_BOTTOM) {
////                    onLoadMore()
//                }
//                mPullLayout.finishActionRun(pullAction)
//            }, 0)
//        }
    }

    //设置recyclerview的adapter
    abstract fun setAdapter()

    fun getRecyclerView(): RecyclerView {
        return mRecyclerView
    }

    abstract fun doFinishActionRun(pullAction: QMUIPullLayout.PullAction)

    abstract fun onRefreshData(pullAction: QMUIPullLayout.PullAction)

    abstract fun onLoadMore(pullAction: QMUIPullLayout.PullAction)

    abstract fun <T : RecyclerView.ViewHolder> onDataLoaded(adapter: RecyclerView.Adapter<T>)

    override fun lazyLoadData() {
    }

    override fun showLoading(message: String) {
    }

    override fun dismissLoading() {
    }

    //QMUIPULLLAYOUT刷新或是加载完成
    fun finishRefresh(pullAction: QMUIPullLayout.PullAction) {
        mPullLayout.finishActionRun(pullAction)
    }
}