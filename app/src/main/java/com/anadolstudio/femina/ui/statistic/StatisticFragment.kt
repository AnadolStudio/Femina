package com.anadolstudio.femina.ui.statistic

import android.os.Bundle
import android.view.View
import com.anadolstudio.femina.R
import com.anadolstudio.femina.ui.base.fragment.BaseFragment
import com.anadolstudio.femina.utils.viewmodel.obtainViewModel

class StatisticFragment : BaseFragment(R.layout.fragment_statistic) {

    companion object {
        fun newInstance(): StatisticFragment = StatisticFragment()
    }

    private lateinit var viewModel: StatisticViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = obtainViewModel(StatisticViewModel.Factory())
    }

    override fun onBackPressedInternal(): Boolean = true.also { viewModel.exit() }
}
