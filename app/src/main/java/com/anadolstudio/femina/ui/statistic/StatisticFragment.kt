package com.anadolstudio.femina.ui.statistic

import com.anadolstudio.femina.R
import com.anadolstudio.femina.ui.base.fragment.BaseFragment
import com.anadolstudio.femina.ui.profile.ProfileFragment

class StatisticFragment : BaseFragment(R.layout.fragment_statistic) {

    companion object{
        fun newInstance(): StatisticFragment = StatisticFragment()
    }

    override fun onBackPressedInternal(): Boolean {
        TODO("Not yet implemented")
    }
}