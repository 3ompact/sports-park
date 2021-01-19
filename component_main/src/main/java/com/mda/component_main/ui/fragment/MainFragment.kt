package com.mda.component_main.ui.fragment

import android.os.Bundle
import com.mda.common_ui_base.base.BaseVMDBFragment
import com.mda.component_main.R
import com.mda.component_main.databinding.FragmentMainCmBinding
import com.mda.component_main.viewmodel.MainFragmentViewModel

class MainFragment :BaseVMDBFragment<MainFragmentViewModel, FragmentMainCmBinding>() {
    override fun layoutId(): Int {
        return R.layout.fragment_main_cm
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun lazyLoadData() {
    }

    override fun showLoading(message: String) {
    }

    override fun dismissLoading() {
    }
}