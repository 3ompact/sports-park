package com.mda.component_main.ui.fragment

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.mda.common_ui_base.base.AbBaseRefreshAndLoadMoreFragment
import com.mda.component_main.R
import com.mda.component_main.adapter.HomeFragmentWithoutThrAdapter
import com.mda.component_main.databinding.FragmentHomeBinding
import com.mda.component_main.viewmodel.MainFragmentViewModel
import com.qmuiteam.qmui.recyclerView.QMUIRVDraggableScrollBar
import com.qmuiteam.qmui.recyclerView.QMUIRVItemSwipeAction
import com.qmuiteam.qmui.widget.pullLayout.QMUIPullLayout

@Route(path = "/cm/homefragment")
class HomeFragment : AbBaseRefreshAndLoadMoreFragment<MainFragmentViewModel, FragmentHomeBinding>() {
    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(savedInstanceState: Bundle?) {
          mTopBar = mDataBinding.topbarBaseRefreshAndLoadMoreCmHomeFragment
          mPullLayout=  mDataBinding.pullLayoutBaseRefreshAndLoadMoreCmHomeFragment
          mRecyclerView = mDataBinding.rvCmHomeFragment

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
        swipeAction.attachToRecyclerView(mRecyclerView)

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
        setAdapter()
        setPullLayoutActionListener()
    }


    private fun initTopBar() {
        mDataBinding.topbarBaseRefreshAndLoadMoreCmHomeFragment.addLeftBackImageButton().setOnClickListener {  }
        mTopBar.setTitle("test")
    }
    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {
    }

    override fun dismissLoading() {
    }

    override fun setAdapter() {
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        var adapter = context?.let { HomeFragmentWithoutThrAdapter(it) }
        mRecyclerView.adapter = adapter
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