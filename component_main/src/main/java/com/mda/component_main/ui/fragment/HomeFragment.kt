package com.mda.component_main.ui.fragment

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.mda.common_ui_base.base.AbBaseRefreshAndLoadMoreFragment

import com.mda.component_main.R
import com.mda.component_main.databinding.FragmentHomeBinding
import com.mda.component_main.viewmodel.MainFragmentViewModel
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.qmuiteam.qmui.widget.pullLayout.QMUIPullLayout

class HomeFragment : AbBaseRefreshAndLoadMoreFragment<MainFragmentViewModel, FragmentHomeBinding>() {
    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(savedInstanceState: Bundle?) {
          mTopBar = mDataBinding.topbarBaseRefreshAndLoadMoreCmHomeFragment
          mPullLayout=  mDataBinding.pullLayoutBaseRefreshAndLoadMoreCmHomeFragment
          mRecyclerView = mDataBinding.rvCmHomeFragment
    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {
    }

    override fun dismissLoading() {
    }

    override fun setAdapter() {
    }

    override fun doFinishActionRun(pullAction: QMUIPullLayout.PullAction) {
        finishRefresh(pullAction)
    }

    override fun onRefreshData(pullAction: QMUIPullLayout.PullAction) {
        finishRefresh(pullAction)

    }

    override fun onLoadMore(pullAction: QMUIPullLayout.PullAction) {
        finishRefresh(pullAction)

    }

    override fun <T : RecyclerView.ViewHolder> onDataLoaded(adapter: RecyclerView.Adapter<T>) {
    }
}